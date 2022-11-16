package com.librarium.booking.services;

import com.librarium.booking.models.Book;
import com.librarium.booking.models.Rent;
import com.librarium.booking.models.User;
import com.librarium.booking.repositories.BookRepository;
import com.librarium.booking.repositories.RentRepository;
import com.librarium.booking.repositories.UserRepository;
import com.librarium.booking.services.exceptions.BookAlreadyReturnedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RentService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RentRepository rentRepository;

    public RentService(BookRepository bookRepository, UserRepository userRepository, RentRepository rentRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
    }

    public Rent rent(String legalDocument, String isbn) {

        final int daysUntilDeadline = 10;

        Rent rent = new Rent();

        Book book = this.bookRepository.findById(isbn)
                .orElseThrow(IllegalArgumentException::new);

        User user = this.userRepository.findById(legalDocument)
                .orElseThrow(IllegalArgumentException::new);

        this.rentRepository.findByUserCpfAndBookIsbn(legalDocument, isbn)
                .stream()
                .filter(x -> x.getReturnalDate() == null)
                .findAny()
                .ifPresent(x -> {
                    throw new IllegalArgumentException("User already rent this book");
                });

        rent.setBook(book);
        rent.setUser(user);
        rent.setDaysUntilDeadline(daysUntilDeadline);
        rent.setDate(LocalDateTime.now());

        return this.rentRepository.save(rent);
    }

    public Rent closeRent(Long id){

        Rent rent = this.rentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        if(rent.wasReturned()){
            throw new BookAlreadyReturnedException();
        }

        rent.returnBook();

        return this.rentRepository.save(rent);
    }

}
