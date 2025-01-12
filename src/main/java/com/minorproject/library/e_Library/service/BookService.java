package com.minorproject.library.e_Library.service;

import com.minorproject.library.e_Library.entity.Book;
import com.minorproject.library.e_Library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
//        log.info("Adding new book");
        Book savedBook= this.bookRepository.save(book);
//        log.info("New Book added with id {}",savedBook.getId());
        System.out.println("savedBook"+savedBook);
        return savedBook;
    }

    public List<Book> getALLBooks(){
        return this.bookRepository.findAll();
    }
}
