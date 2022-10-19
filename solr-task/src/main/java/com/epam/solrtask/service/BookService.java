package com.epam.solrtask.service;

import com.epam.solrtask.document.BookSolr;
import com.epam.solrtask.model.EpubBook;
import com.epam.solrtask.model.SearchRequest;
import com.epam.solrtask.model.SearchResponse;
import com.epam.solrtask.repository.BookRepository;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookSolr getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public SearchResponse searchBooks(SearchRequest request) {
        return bookRepository.search(request);
    }

    public List<String> getAutoSuggestions(String query) {
        return bookRepository.getAutoSuggestions(query);
    }

    public void startIndexing() {
        List<String> filePaths = List.of(
                "books/atom.epub",
                "books/forrest.epub",
                "books/four.epub",
                "books/ring.epub",
                "books/robin.epub");
        List<BookSolr> bookSolrs = filePaths.stream().map(this::mapToEpubBook).collect(Collectors.toList())
                .stream().map(this::mapToBookSolrFromEpubBook).collect(Collectors.toList());
        bookRepository.saveAll(bookSolrs);
    }

    private EpubBook mapToEpubBook(String filePath) {
        try {
            EpubReader epubReader = new EpubReader();
            Book book = epubReader.readEpub(new FileInputStream(filePath));
            return new EpubBook(
                    book.getTitle(),
                    book.getMetadata().getAuthors(),
                    book.getContents(),
                    book.getMetadata().getLanguage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private BookSolr mapToBookSolrFromEpubBook(EpubBook epubBook) {
        BookSolr bookSolr = new BookSolr();
        bookSolr.setTitle(epubBook.getTitle());
        List<String> authors = new ArrayList<>();
        epubBook.getAuthors().forEach(author -> authors.add(author.getFirstname() + " " + author.getLastname()));
        bookSolr.setAuthors(authors);
        bookSolr.setLanguage(epubBook.getLanguage());
        StringBuilder contentBuilder = new StringBuilder();
        epubBook.getContents().forEach(resource -> {
            try {
                contentBuilder.append(new String(resource.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bookSolr.setContent(contentBuilder.toString());
        return bookSolr;
    }
}
