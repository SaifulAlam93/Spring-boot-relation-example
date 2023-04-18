package com.practice.SpringBootCRUD.relationship_example.many_2_many_entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class BookMM {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String title;


//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
//    @ToString.Exclude
////    @JsonBackReference
//    private List<Author> authors;

    public BookMM(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookMM bookMM = (BookMM) o;
        return id != null && Objects.equals(id, bookMM.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
