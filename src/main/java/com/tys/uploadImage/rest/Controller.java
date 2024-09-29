package com.tys.uploadImage.rest;

import com.tys.uploadImage.entity.Student;
import com.tys.uploadImage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class Controller {
    @Autowired
    private StudentService studentService;


    @GetMapping("/testing")
    public String testing(){
        return "Hello World";
    }
    @PostMapping("/upload")
    public ResponseEntity<Student> saveStudent(
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("dob") String dob,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Student student = Student.builder()
                .name(name)
                .address(address)
                .dob(java.sql.Date.valueOf(dob))  // assuming `dob` is in YYYY-MM-DD format
                .build();
        Student savedStudent = studentService.saveStudent(student, file);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        byte[] imageBytes = studentService.getImage(imageName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"")
                .contentType(MediaType.IMAGE_JPEG)  // Set content type accordingly (e.g., image/png)
                .body(imageBytes);
    }
}
