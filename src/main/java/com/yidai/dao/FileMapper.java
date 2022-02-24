package com.yidai.dao;

import com.yidai.pojo.FileData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper {

    // 将文件信息注入到数据库
    int insertFileData(FileData fileData);

    // 通过uuid查询文件信息
    FileData selectFileDataByNewNme(@Param("newName") String newName);
}
