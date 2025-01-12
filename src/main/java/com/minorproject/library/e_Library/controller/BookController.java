package com.minorproject.library.e_Library.controller;

import com.minorproject.library.e_Library.entity.Book;
import com.minorproject.library.e_Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book savedBook= this.bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList= this.bookService.getALLBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
