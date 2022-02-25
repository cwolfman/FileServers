package com.yidai.controller;

import com.yidai.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yidai
 * @date 2022年2月23日
 */
@CrossOrigin
@RequestMapping("file")
@RestController
@Api(tags = "文件管理")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 根据uuid获取文件信息
     *
     * @param newName 文件uuid
     * @return 文件元数据
     */
    @ApiOperation(value = "根据uuid获取文件信息")
    @GetMapping("get/{uuid}")
    public Map getFileData(@PathVariable("uuid") String newName) {
        Map map = new HashMap();

        map = fileService.getFileData(newName);

        return map;
    }

    /**
     * 上传文件
     *
     * @param file 上传的文件
     * @return 返回uuid
     */
    @ApiOperation(value = "上传文件")
    @PostMapping("upload")
    public Map uploadFile(MultipartFile file) {
        Map map = new HashMap();
        if (file == null) {
            System.out.println("空");
        }

        map = fileService.uploadFile(file);

        return map;
    }

    /**
     * 根据uuid下载文件
     *
     * @param newName
     * @return
     */
    @ApiOperation(value = "根据uuid下载文件")
    @GetMapping("download/{uuid}")
    public void downloadFile(@PathVariable("uuid") String newName, HttpServletResponse rs) {
        fileService.downloadFile(newName, rs);
    }
}
