package com.librarium.booking.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Rent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="isbn", nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name="cpf", nullable = false)
    private User user;
    private LocalDateTime date;
    private int daysUntilDeadline;
    private LocalDateTime returnalDate;

    public void returnBook() {
        setReturnalDate(LocalDateTime.now());
    }

    public boolean wasReturned() {
        return this.returnalDate != null;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getDaysUntilDeadline() {
        return daysUntilDeadline;
    }

    public void setDaysUntilDeadline(int daysUntilDeadline) {
        this.daysUntilDeadline = daysUntilDeadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReturnalDate() {
        return returnalDate;
    }

    public void setReturnalDate(LocalDateTime returnalDate) {
        this.returnalDate = returnalDate;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", date=" + date +
                ", daysUntilDeadline=" + daysUntilDeadline +
                ", returnalDate=" + returnalDate +
                '}';
    }

}
