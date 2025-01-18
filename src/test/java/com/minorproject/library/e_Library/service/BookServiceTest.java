package com.minorproject.library.e_Library.service;
import com.minorproject.library.e_Library.ELibraryApplication;
import com.minorproject.library.e_Library.entity.Book;
import com.minorproject.library.e_Library.enums.Category;
import com.minorproject.library.e_Library.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.*;

@SpringBootTest(classes = ELibraryApplication.class)
public class BookServiceTest {

    private final BookService bookService;

    @Autowired
    public BookServiceTest(BookService bookService) {
        this.bookService = bookService;
    }

    @MockitoBean
    private BookRepository bookRepository;

    static Book book = Book.builder()
            .id(UUID.randomUUID())
            .author("TestAuthor")
            .category(Category.FICTION)
            .description("TestDesc")
            .isbn("TestISBN")
            .name("TestName")
            .price(400D).build();

//    @Test
//    void getAllBooks_whenThereAreAListBooks_shouldReturnAListOfBooks() {
//        List<Book> bookList = Arrays.asList(book, book.withPrice(500D), book.withPrice(600D));
//
//        Mockito.when(this.bookRepository.findAll()).thenReturn(bookList);
//        List<Book> fetchedBookList = this.bookService.getALLBooks();
//
//        Assertions.assertEquals(bookList.size(), fetchedBookList.size());
//    }

//    @Test
//    void getAllBooks_whenThereAreNoBooks_shouldReturnAEmptyList() {
//        Mockito.when(this.bookRepository.findAll()).thenReturn(Collections.emptyList());
//        List<Book> fetchedBookList = this.bookService.getALLBooks();
//
//        Assertions.assertEquals(0, fetchedBookList.size());
//    }

    @Test
    void getBookById_whenTheBookExists_shouldReturnBook() {
        Mockito.when(this.bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Book book1 = this.bookService.getBookById(book.getId());
        Assertions.assertEquals(book, book1);
    }

    @Test
    void getBookById_whenTheBookDoesNotExist_shouldReturnNull() {
        UUID invalidId = UUID.randomUUID();
        Mockito.when(this.bookRepository.findById(invalidId)).thenReturn(Optional.empty());

        Book book1 = this.bookService.getBookById(invalidId);
        Assertions.assertNull(book1);
    }

//    @Test
//    void addBook_whenBookIsPassed_shouldReturnAddedBook() {
//        Mockito.when(this.bookRepository.save(book)).thenReturn(book);
//
//        Book book1 = this.bookService.addBook(book);
//        Assertions.assertEquals(book, book1);
//    }
}
