package com.practice.SpringBootCRUD.relationship_example.one_to_many_entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class LibraryOM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "libraryOM")
    @ToString.Exclude
    private List<Book> books;


}