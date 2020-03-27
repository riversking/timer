package com.rivers.file.service;

import com.rivers.core.util.ExceptionUtil;
import com.rivers.file.config.WindowsorMac;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class UploadService {


    public String uploadFile(MultipartFile file) {
        try {
            //准备目标路径
            String filePath = WindowsorMac.pathName();
            String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString();
            File dest = new File(filePath, fileName + extName);
            file.transferTo(dest);
            //返回路径
            return filePath + fileName + extName;
        } catch (IOException e) {
            ExceptionUtil.throwBusinessException("301001", e);
        }
        return null;
    }
}
