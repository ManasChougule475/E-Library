package com.minorproject.library.e_Library.service;

import com.minorproject.library.e_Library.entity.Book;
import com.minorproject.library.e_Library.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final RedisService redisService;
    private static final Logger log = LogManager.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository bookRepository, RedisService redisService){
        this.bookRepository = bookRepository;
        this.redisService = redisService;
    }

    public Book addBook(Book book){
        Book savedBook= this.bookRepository.save(book);

        List<Book> cachedBookList = (List<Book>) this.redisService.getCacheData("list");
        if(cachedBookList==null){
//            cachedBookList = new ArrayList<>();
//            cachedBookList.add(savedBook);
            cachedBookList = this.bookRepository.findAll();
            log.info("Added book to DB; Book list not found in cache. Fetched from database and added to cache");
        }else{
            cachedBookList.add(savedBook);
            log.info("Added book to DB; Updated existing book list in cache");
        }
        this.redisService.addToCache("list",cachedBookList);
        return savedBook;
    }


    public List<Book> getALLBooks() {
        Instant start = Instant.now();

        List<Book> cachedBookList = (List<Book>) this.redisService.getCacheData("list");

        if (cachedBookList == null) {
            cachedBookList = this.bookRepository.findAll();
            this.redisService.addToCache("list", cachedBookList);
            long timeTaken = Instant.now().toEpochMilli() - start.toEpochMilli();
            log.info("Book list not found in cache. Fetched from database and added to cache. Time taken: " + timeTaken + " ms");
        } else {
            long timeTaken = Instant.now().toEpochMilli() - start.toEpochMilli();
            log.info("Book list found in cache. Returning cached list. Time taken: " + timeTaken + " ms");
        }

        return cachedBookList;
    }


    public Book getBookById(UUID bookID){
        Optional<Book> bookOptional= this.bookRepository.findById(bookID);
        return bookOptional.orElse(null);
    }
}
