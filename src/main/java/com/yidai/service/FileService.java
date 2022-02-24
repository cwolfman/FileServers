package com.yidai.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface FileService {

    // 依据uuid获取文件信息
    Map getFileData(String newName);

    // 上传文件
    Map uploadFile(MultipartFile file);

    // 依据uuid下载文件
    void downloadFile(String newName, HttpServletResponse rs);
}
