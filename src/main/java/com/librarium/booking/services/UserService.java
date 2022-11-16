package com.librarium.booking.services;

import com.librarium.booking.models.User;
import com.librarium.booking.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list(int page) {
        PageRequest pageRequest = PageRequest.of(page, 3);
        Page<User> pageFound =this.userRepository.findAll(pageRequest);
        return pageFound.getContent();
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }
}
