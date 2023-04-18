package com.practice.SpringBootCRUD.relationship_example.repository;

import com.practice.SpringBootCRUD.relationship_example.one_to_many_entity.LibraryOM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryOMRepository extends JpaRepository<LibraryOM, Long> {}

