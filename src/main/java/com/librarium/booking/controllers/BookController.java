package com.librarium.booking.controllers;

import com.librarium.booking.models.Book;
import com.librarium.booking.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Book>> listBooks(@RequestParam int page){
        return ResponseEntity.ok(this.bookService.list(page));
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }

}
