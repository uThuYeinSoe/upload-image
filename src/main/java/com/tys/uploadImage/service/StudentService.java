package com.tys.uploadImage.service;

import com.tys.uploadImage.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StudentService {
    public Student saveStudent(Student student, MultipartFile file) throws IOException;
    public byte[] getImage(String imageName) throws IOException;
}
