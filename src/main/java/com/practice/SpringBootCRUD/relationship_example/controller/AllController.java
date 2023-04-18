package com.practice.SpringBootCRUD.relationship_example.controller;



import com.practice.SpringBootCRUD.relationship_example.many_2_many_entity.Author;
import com.practice.SpringBootCRUD.relationship_example.many_2_many_entity.BookMM;
import com.practice.SpringBootCRUD.relationship_example.one2one_entity.Address;
import com.practice.SpringBootCRUD.relationship_example.one2one_entity.Library;
import com.practice.SpringBootCRUD.relationship_example.one_to_many_entity.Book;
import com.practice.SpringBootCRUD.relationship_example.one_to_many_entity.LibraryOM;
import com.practice.SpringBootCRUD.relationship_example.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AllController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    LibraryOMRepository libraryOMRepository;

    @Autowired
    BookRepository bookRepository;


    @Autowired
    BookMMRepository bookMMRepository;

    @Autowired
    AuthorRepository authorRepository;


    //One to One

    @PostMapping(value = "/library")
    public Library saveLibrary(@RequestBody Library library){
        return libraryRepository.save(library);
    }

    /*
    http://localhost:8080/library

    {
    "name": "Test Library 2",
    "address": {
        "location": "Khulna"
    }
    }
    */

    @GetMapping(value = "/library")
    public List<Library> saveLibrary(){
        List<Library> libraryList = libraryRepository.findAll();

        libraryList.forEach((c) -> {
            if (c.getAddress()!=null){
                c.getAddress().setLibrary(null);
            }
        });

        return libraryList;
    }

    @PostMapping(value = "/address")
    public Address saveAddress(@RequestBody Address address){
        return addressRepository.save(address);
    }
    /*
    http://localhost:8081/address

    {
    "location": "Test Library 2",
    "library": {
        "id": 36
    }
}
    * */


    @GetMapping(value = "/address")
    public List<Address> saveAddress(){
        List<Address> addresses = (List<Address>) addressRepository.findAll();


        addresses.forEach((c) -> {
            if (c.getLibrary()!=null){
                c.getLibrary().setAddress(null);
            }
        });
//        addresses.forEach((c) -> c.getLibrary().setAddress(null));
        return addresses;
    }




    //One to many

    @PostMapping(value = "/libraryOM")
    public LibraryOM saveLibraryOM(@RequestBody LibraryOM libraryOM){
        LibraryOM libraryOM1 = new LibraryOM();
        BeanUtils.copyProperties(libraryOM, libraryOM1,"books");
        List<Book> books = new ArrayList<>();
        libraryOM.getBooks().forEach(book -> {
            if (book!=null){
                book.setLibraryOM(libraryOM1);
                books.add(book);
            }
        });
        libraryOM1.setBooks(books);

        libraryOM = libraryOMRepository.save(libraryOM1);

        libraryOM.getBooks().forEach(book -> {
            book.setLibraryOM(null);
        });
         
        return libraryOM;
    }

    /*
{
    "name": "saiful.alam",
    "books": [
        {
            "title": "ffff"
        },
        {
            "title": "AAAAA"
        }
    ]
}
    */


    public LibraryOM getOB(LibraryOM c){
        c.getBooks().forEach(book -> {
            book.setLibraryOM(null);
        });

        return c;
    }
    @GetMapping(value = "/libraryOM")
    public List<LibraryOM> saveLibraryOm(){
        List<LibraryOM> libraryList = libraryOMRepository.findAll();

        libraryList.forEach((c) -> {
           c.getBooks().forEach(book -> {
               book.setLibraryOM(null);
           });
        });

        return libraryList;
    }

    @PostMapping(value = "/book")
    public Book saveBookOM(@RequestBody Book book){
        return bookRepository.save(book);
    }

    /*
{
    "title": "ffff",
    "libraryOM": {
        "id": 8
    }
}
    */

    @GetMapping(value = "/book")
    public List<Book> saveBook(){
        List<Book> libraryList = bookRepository.findAll();
        libraryList.forEach((c) ->{
            if ( c.getLibraryOM()!=null){
                c.getLibraryOM().setBooks(null);
            }
        });
        return libraryList;
    }


    //Many to Many



    @PostMapping(value = "/bookMM")
    public BookMM saveBookMM(@RequestBody BookMM bookMM){
        return bookMMRepository.save(bookMM);
    }


    @GetMapping(value = "/bookMM")
    public List<BookMM> saveBookMM(){
        List<BookMM> bookMMList = bookMMRepository.findAll();
//        libraryList.forEach((c) -> c.getAddress().setLibrary(null));

        return bookMMList;
    }



    @PostMapping(value = "/author")
    public Author saveAuthor(@RequestBody Author author){
        return authorRepository.save(author);
    }

    /*
{
    "name": "saiful.alam",
    "books": [
        {
            "title": "ffff"
        },
        {
            "title": "AAAAA"
        }
    ]
}
    */

    @GetMapping(value = "/author")
    public List<Author> saveAuthor(){
        List<Author> authorList = authorRepository.findAll();
//        libraryList.forEach((c) -> c.getAddress().setLibrary(null));
        return authorList;
    }


}
