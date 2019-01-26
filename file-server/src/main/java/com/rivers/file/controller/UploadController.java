package com.rivers.file.controller;

import com.rivers.core.util.ExceptionUtil;
import com.rivers.core.view.ResponseVo;
import com.rivers.file.config.WindowsorMac;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author rivers
 */
@RestController
@RequestMapping("file")
@Log4j2
public class UploadController {

    @PostMapping("/upload")
    @ResponseBody
    public ResponseVo upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        ResponseVo vo = ResponseVo.ok();
        if (file.isEmpty()) {
            return ResponseVo.fail("301001", "上传失败，请选择文件");
        }
        String fileName = UUID.randomUUID().toString();
        String filePath = WindowsorMac.pathName();
        File dest = new File(filePath + fileName + ".png");
        try {
            file.transferTo(dest);
            vo.setCode("0");
            vo.setRsp(fileName + ".png");
            vo.setMsg("上传成功");
            log.info("上传成功");
            return vo;
        } catch (IOException e) {
            log.error(e.toString(), e);
            ExceptionUtil.throwBusinessException("301001", e);
        }
        return ResponseVo.fail("301001", "上传失败，请选择文件");
    }

}
