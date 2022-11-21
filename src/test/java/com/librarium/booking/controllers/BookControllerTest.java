package com.librarium.booking.controllers;

import com.librarium.booking.models.Book;
import com.librarium.booking.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {BookController.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Test
    public void saveBook() throws Exception {

        String bookJson = "{\n" +
                "\t\"isbn\": \"9788533613409\",\n" +
                "\t\"title\": \"Lord Of The Rings\",\n" +
                "\t\"summary\": \"The entire trilogy\",\n" +
                "\t\"author\": \"J.R.R. Tolkien\",\n" +
                "\t\"releaseDate\": \"2001-01-01\"\n" +
                "}";

        Book book = new Book();
        book.setIsbn("9788533613409");
        book.setAuthor("J.R.R. Tolkien");
        book.setTitle("Lord Of The Rings");
        book.setSummary("The entire trilogy");
        book.setReleaseDate(LocalDate.of(2001, 1, 1));

        when(service.save(any())).thenReturn(book);

        mockMvc.perform(post("/book")
                        .content(bookJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(bookJson));
    }
}