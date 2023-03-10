package com.springboot.awss3example.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.springboot.awss3example.serviceImpl.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("s3/file")
public class S3FileController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    AmazonS3 s3Client;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(fileUploadService.uploadFileToS3(file), HttpStatus.OK);
    }

    @PostMapping("/url/upload")
    public ResponseEntity<URL> uploadFileFromUrl(@RequestParam(value = "url") String url) throws Exception {
        return ResponseEntity.ok(fileUploadService.uploadFileFromUrl(url));
    }

    @PostMapping("/upload/pre-signed")
    public ResponseEntity<URL> generatePreSignedUrlForFileUpload(@RequestParam(value = "filename") String fileName) {
        return new ResponseEntity<>(fileUploadService.generatePreSignedUrlForFileUpload(fileName), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = fileUploadService.downloadFileFromS3(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @GetMapping("/pre-signed")
    public ResponseEntity<URL> getPreSigned(@RequestParam("s3url") String s3url) {
        return ResponseEntity.ok(fileUploadService.getPreSignedUrl(s3url));
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(fileUploadService.deleteFileFromS3(fileName), HttpStatus.OK);
    }

}
