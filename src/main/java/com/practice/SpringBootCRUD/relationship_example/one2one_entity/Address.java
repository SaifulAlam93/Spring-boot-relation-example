package com.practice.SpringBootCRUD.relationship_example.one2one_entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String location;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
//    @JsonIgnore
    private Library library;

    // standard constructor, getters, setters

}
