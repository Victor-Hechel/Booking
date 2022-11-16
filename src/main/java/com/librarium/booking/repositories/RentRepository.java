package com.librarium.booking.repositories;

import com.librarium.booking.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByUser(String legalDocument);

    List<Rent> findByUserCpfAndBookIsbn(String legalDocument, String isbn);
}
