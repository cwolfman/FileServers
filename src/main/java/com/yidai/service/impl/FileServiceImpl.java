package com.yidai.service.impl;

import com.yidai.pojo.FileData;
import com.yidai.dao.FileMapper;
import com.yidai.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yidai
 * @date 2022年2月23日
 */
@Service
public class FileServiceImpl implements FileService {

    // 获取上传的文件存放的位置
    @Value("${files.path.upload}")
    private String uploadPath;

    @Autowired
    private FileMapper fileMapper;

    // 文件输入流
    FileOutputStream fos;
    // 文件输出流
    FileInputStream fis;
    // 输出流
    OutputStream os;

    // 根据uuid获取文件信息
    @Override
    public Map getFileData(String newName) {
        Map map = new HashMap();

        // 获取文件元信息
        FileData fileData = fileMapper.selectFileDataByNewNme(newName);

        map.put("fileData", fileData);

        return map;
    }

    // 上传文件
    @Override
    public Map uploadFile(MultipartFile file) {
        Map map = new HashMap();
        FileData fileData = new FileData();

        // 获取uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        map.put("uuid", uuid);

        // 获取文件初始名
        String initialName = file.getOriginalFilename();

        // 获取后缀
        String suffix = initialName.substring(initialName.lastIndexOf("."));

        // 重命名文件名
        String newName = uuid + suffix;


        // 目录格式
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        // 文件目录
        String list = uploadPath + "/" + simpleDateFormat.format(date) + "/";

        // 文件路径
        String filePath = list + newName;

        try {
            // 获取文件字节数组
            byte[] bytes = file.getBytes();
            // 如果目录不存在，则创建
            File dest = new File(list);
            if (!dest.exists()) {
                dest.mkdirs();
            }

            // 创建文件输出
            fos = new FileOutputStream(filePath);
            fos.write(bytes);

            map.put("msg", "文件上传成功");
        } catch (IOException e) {
            throw new RuntimeException("上传文件失败,服务器发生异常!", e);
        } finally {
            // 关闭流
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 将数据放入FileData中
        fileData.setSize(file.getSize());
        fileData.setType(file.getContentType());
        fileData.setinitialName(initialName);
        fileData.setNewName(uuid);
        fileData.setFilePath(filePath);

        // 存入数据库
        fileMapper.insertFileData(fileData);

        return map;
    }

    // 根据uuid下载文件
    @Override
    public void downloadFile(String newName, HttpServletResponse rs) {
        // 设置响应类型
        rs.setContentType("application/octet-stream");

        FileData fileData = fileMapper.selectFileDataByNewNme(newName);

        String initialName = fileData.getinitialName();
        String suffix = initialName.substring(initialName.lastIndexOf("."));

        rs.addHeader("Content-Disposition", "attachment;fileName=" + newName + suffix);
        if (fileData == null) {
            rs.setStatus(410);
            return;
        }

        // 获取文件
        File file = new File(fileData.getFilePath());

        // 判断文件存在与否,不存在返回410状态码
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                os = rs.getOutputStream();

                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
            } catch (IOException e) {
                throw new RuntimeException("读取文件失败", e);
            } finally {
                // 关闭流
                try {
                    fis.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            rs.setStatus(410);
        }
    }
}
