package com.practice.SpringBootCRUD.repository;

import com.practice.SpringBootCRUD.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepo extends JpaRepository<FileUpload, Long> {
}
