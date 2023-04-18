package com.practice.SpringBootCRUD.relationship_example.one_to_many_entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable=false)
    private String title;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name="LIBRARYOM_ID", referencedColumnName = "id", nullable = true)
    private LibraryOM libraryOM;

    // standard constructor, getter, setter
}
