package com.example.testeredis.controllers;

import com.example.testeredis.models.Book;
import com.example.testeredis.models.Category;
import com.example.testeredis.repositories.BookRepository;
import com.example.testeredis.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<Map<String,Object>> all(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Pageable paging = PageRequest.of(page,size);
        Page<Book> pagedResult = bookRepository.findAll(paging);
        List<Book> books = pagedResult.hasContent() ? pagedResult.getContent() : Collections.emptyList();

        Map<String,Object> response = new HashMap<>();

        response.put("books", books);
        response.put("page", pagedResult.getNumber());
        response.put("pages", pagedResult.getTotalPages());
        response.put("total", pagedResult.getTotalElements());

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/categories")
    public Iterable<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/{isbn}")
    public Book categoryById(@PathVariable String isbn){
        return bookRepository.findById(isbn).get();
    }


}
