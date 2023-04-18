package com.practice.SpringBootCRUD.relationship_example.repository;

import com.practice.SpringBootCRUD.relationship_example.many_2_many_entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> { }
