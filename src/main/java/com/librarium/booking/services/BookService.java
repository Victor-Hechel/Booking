package com.librarium.booking.services;

import com.librarium.booking.models.Book;
import com.librarium.booking.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> list(int page){
        PageRequest pageRequest = PageRequest.of(page, 3);
        Page<Book> pageBook = bookRepository.findAll(pageRequest);
        return pageBook.getContent();
    }

    public Book save(Book book) {
        try {
            return this.bookRepository.save(book);
        } catch(Exception e){
            throw new IllegalArgumentException("Error trying to save, invalid book");
        }
    }
}
