package com.clouddeer.account.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;

@Controller
public class ViewController {

    private static final String ERROR_PATH = "/error";

    private final ResourceLoader resourceLoader;

    public static final String ROOT = "E:/project/cloud-deer-account/account/src/main/resources/upload";


    @Autowired
    public ViewController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}",produces={MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
