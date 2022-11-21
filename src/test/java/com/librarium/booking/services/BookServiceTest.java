package com.librarium.booking.services;

import com.librarium.booking.models.Book;
import com.librarium.booking.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    BookService service;

    @Test
    public void shouldSave() {
        // given
        Book book = new Book();
        book.setIsbn("123");
        book.setAuthor("Tolkien");
        book.setTitle("Lord of the Rings");
        book.setSummary("The trilogy");
        book.setReleaseDate(LocalDate.of(1954, 6, 29));

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // when
        service.save(book);

        // then
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    public void shouldNotSaveInvalidBook() {
        // given
        Book book = new Book();
        book.setIsbn(null);
        book.setAuthor("Tolkien");
        book.setTitle("Lord of the Rings");
        book.setSummary("The trilogy");
        book.setReleaseDate(LocalDate.of(1954, 6, 29));

        when(bookRepository.save(any(Book.class))).thenThrow(RuntimeException.class);

        // when
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(book));

        // then
        Assertions.assertEquals("Error trying to save, invalid book", exception.getMessage());
    }
}