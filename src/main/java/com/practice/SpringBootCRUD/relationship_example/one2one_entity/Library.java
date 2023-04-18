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
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    @RestResource(path = "libraryAddress", rel="address")
    private Address address;


}