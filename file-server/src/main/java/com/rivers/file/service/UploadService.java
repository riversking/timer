package com.rivers.file.service;

import com.rivers.core.util.ExceptionUtil;
import com.rivers.file.config.WindowsOrMac;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class UploadService {


    public Map<String, String> uploadFile(MultipartFile file) {
        try {
            //准备目标路径
            String filePath = WindowsOrMac.pathName();
            String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString();
            File dest = new File(filePath, fileName + extName);
            file.transferTo(dest);
            Map<String, String> map = new HashMap<>();
            map.put("filepath", filePath);
            map.put("filename", fileName + extName);
            //返回路径
            return map;
        } catch (IOException e) {
            ExceptionUtil.throwBusinessException("301001", e);
        }
        return null;
    }
}
