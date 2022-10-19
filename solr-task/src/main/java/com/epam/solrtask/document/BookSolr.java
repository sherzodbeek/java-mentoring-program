package com.epam.solrtask.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@SolrDocument(solrCoreName = "books")
public class BookSolr {

    @Id
    @Indexed
    private String id;

    @Indexed
    private String title;

    @Indexed
    private List<String> authors;

    @Indexed
    private String content;

    @Indexed
    private String language;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BookSolr(String id, String title, List<String> authors, String content, String language) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.content = content;
        this.language = language;
    }

    public BookSolr() {
    }
}
