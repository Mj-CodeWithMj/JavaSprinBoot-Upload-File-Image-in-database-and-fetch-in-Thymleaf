package com.example.uploadfile.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.uploadfile.uploadfile.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
