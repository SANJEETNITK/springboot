package com.springboot.awss3example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Service
public interface S3FileUploadService {

    String uploadFileToS3(MultipartFile multipartFile);

    byte[] downloadFileFromS3(String fileName);

    String deleteFileFromS3(String fileName);

    URL uploadFileFromUrl(String url) throws IOException;

    URL generatePreSignedUrlForFileUpload(String fileName);

    URL getPreSignedUrl(String s3url);
}
