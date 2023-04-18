package com.practice.SpringBootCRUD.relationship_example.repository;

import com.practice.SpringBootCRUD.relationship_example.one_to_many_entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> { }

