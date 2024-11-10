package com.example.controller;

import com.google.cloud.storage.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private final String bucketName = "bucket-task-1";  // Replace with your actual GCS bucket name

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "No file selected for upload.";
        }

        try {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();

            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();

            storage.create(blobInfo, file.getBytes());

            return "File uploaded successfully: " + fileName;
        } catch (IOException e) {
            return "File upload failed: " + e.getMessage();
        }
    }

    // New test endpoint to verify if GCS is working
    @GetMapping("/test-upload")
    public String testUploadToGCS() {
        String testFileName = "test.txt";
        BlobId blobId = BlobId.of(bucketName, testFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        try {
            // Attempt to upload a simple text string as a file
            storage.create(blobInfo, "Hello, World!".getBytes(StandardCharsets.UTF_8));
            return "Test file uploaded successfully!";
        } catch (StorageException e) {
            return "Error uploading test file: " + e.getMessage();
        }
    }
}
