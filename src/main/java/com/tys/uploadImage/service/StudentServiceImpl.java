package com.tys.uploadImage.service;

import com.tys.uploadImage.entity.Student;
import com.tys.uploadImage.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepo;
    private final String UPLOAD_DIR = "C:/Users/bankuser/Desktop/uploaded-images/";

    @Override
    @Transactional
    public Student saveStudent(Student student, MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR,fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,file.getBytes());

            student.setProfileImage(fileName);
        }

        return studentRepo.save(student);
    }

    public byte[] getImage(String imageName) throws IOException {
        Path imagePath = Paths.get(UPLOAD_DIR, imageName);
        return Files.readAllBytes(imagePath);
    }
}
