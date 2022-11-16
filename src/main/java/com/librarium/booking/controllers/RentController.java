package com.librarium.booking.controllers;

import com.librarium.booking.controllers.dto.RentRequest;
import com.librarium.booking.models.Rent;
import com.librarium.booking.services.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    public RentController(RentService rentService){
        this.rentService = rentService;
    }

    @PostMapping
    public ResponseEntity<Rent> rent(@RequestBody RentRequest request){
        return ResponseEntity.ok(this.rentService.rent(request.getLegalDocument(), request.getIsbn()));
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<Rent> returnBook(@PathVariable Long id){
        return ResponseEntity.ok(this.rentService.closeRent(id));
    }

}
