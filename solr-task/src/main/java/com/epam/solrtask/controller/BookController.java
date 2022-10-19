package com.epam.solrtask.controller;

import com.epam.solrtask.document.BookSolr;
import com.epam.solrtask.model.SearchRequest;
import com.epam.solrtask.model.SearchResponse;
import com.epam.solrtask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookSolr> getBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> searchBooks(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(bookService.searchBooks(request));
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<String>> getAutoSuggestions(@RequestParam(name = "query") String query) {
        List<String> autoSuggestions = bookService.getAutoSuggestions(query);
        return ResponseEntity.ok(autoSuggestions);
    }

    @GetMapping("/start-indexing")
    public ResponseEntity<String> startIndexing() {
        bookService.startIndexing();
        return ResponseEntity.ok("Indexed");
    }
}
