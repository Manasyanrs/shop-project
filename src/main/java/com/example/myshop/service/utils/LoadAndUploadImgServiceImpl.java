package com.example.myshop.service.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LoadAndUploadImgServiceImpl implements LoadAndUploadImgService {
    @Override
    public String uploadImg(String path, MultipartFile multipartFile) throws IOException {
        String uploadFileName = multipartFile.getOriginalFilename();
        String imgName = System.nanoTime() + "_" + uploadFileName;

        Path fileNameAndPath = Paths.get(path, imgName);
        Files.write(fileNameAndPath, multipartFile.getBytes());
        return imgName;
    }

    @Override
    public byte[] getBytes(String path, String imgName) throws IOException {
        File file = new File(path + imgName);
        FileInputStream fis;
        fis = new FileInputStream(file);
        return IOUtils.toByteArray(fis);
    }

}
