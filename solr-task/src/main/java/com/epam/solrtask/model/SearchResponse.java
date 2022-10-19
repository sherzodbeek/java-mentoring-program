package com.epam.solrtask.model;

import com.epam.solrtask.document.BookSolr;

import java.util.List;

public class SearchResponse {
    private List<BookSolr> books;
    private List<Facet> facets;
    private Long numFound;

    public SearchResponse(List<BookSolr> books, List<Facet> facets, Long numFound) {
        this.books = books;
        this.facets = facets;
        this.numFound = numFound;
    }

    public SearchResponse() {
    }

    public List<BookSolr> getBooks() {
        return books;
    }

    public void setBooks(List<BookSolr> books) {
        this.books = books;
    }

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

    public Long getNumFound() {
        return numFound;
    }

    public void setNumFound(Long numFound) {
        this.numFound = numFound;
    }
}
