package com.example.testsonar.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyController {

    @GetMapping(value="/1/{filePath}")
    public String get1(@PathVariable String filePath) throws IOException {
       String fileContent = Files.readString(Paths.get(filePath));
       return "Hello World 1 : " + filePath + " - " + fileContent;
    }

    @GetMapping(value="/2/{filePath}")
    public String get2(@PathVariable String filePath) {
        File fileUnsafe = new File(filePath);
        return "Hello World 2 : " + filePath + " - " + fileUnsafe.getAbsolutePath();
    }

    @GetMapping(value="/3/{filePath}")
    public String get3(@PathVariable String filePath) {
        File fileUnsafe = new File(filePath);
        try {
            FileUtils.forceDelete(fileUnsafe); // Noncompliant
        }
        catch(IOException ex){
            log.error(ex.getMessage());
        }
        return "Hello World 3 : " + filePath + " - " + fileUnsafe.getAbsolutePath();
    }

}
