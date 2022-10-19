package com.epam.solrtask.model;

import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Resource;

import java.util.List;

public class EpubBook {
    private String title;
    private List<Author> authors;
    private List<Resource> contents;
    private String language;

    public EpubBook(String title, List<Author> authors, List<Resource> contents, String language) {
        this.title = title;
        this.authors = authors;
        this.contents = contents;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Resource> getContents() {
        return contents;
    }

    public void setContents(List<Resource> contents) {
        this.contents = contents;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
