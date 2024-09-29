package com.tys.uploadImage.repository;

import com.tys.uploadImage.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student , Long> {

}
