package com.example.redirectlink.controllers;

import com.example.redirectlink.database.enities.LinkEnity;
import com.example.redirectlink.database.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.example.redirectlink.services.IdBaseConverter;

import java.net.URI;
import java.util.*;

@RestController
public class LinkController {
    @Autowired
    private LinkRepository linkRepository;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/link/{link_id}")
    public ResponseEntity<Object> getLink(
            @PathVariable("link_id") UUID linkId
    ) {

         List<LinkEnity> foundLink = linkRepository.findByLinkId(linkId);

        if (!foundLink.isEmpty()) {

            URI location = URI.create(foundLink.get(0).getLink());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            return new ResponseEntity<Object>(headers, HttpStatus.FOUND);
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}


