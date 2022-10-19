package com.epam.solrtask.repository;

import com.epam.solrtask.document.BookSolr;
import com.epam.solrtask.model.Facet;
import com.epam.solrtask.model.SearchRequest;
import com.epam.solrtask.model.SearchResponse;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;
import org.apache.solr.common.SolrDocumentList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomRepositoryImpl implements CustomRepository {

    private final SolrClient solrClient;

    public CustomRepositoryImpl() {
        this.solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/books").build();
    }

    @Override
    public SearchResponse search(SearchRequest request) {
        SolrQuery solrQuery = new SolrQuery();
        if (request.getField() != null && request.getValue() != null) {
            solrQuery.setQuery(request.getField() + ":" + request.getValue());
        }
        if (request.getFacetField() != null && request.getFacetField().length() != 0) {
            solrQuery.addFacetField(request.getFacetField());
        }
        if (request.getFullText()) {
            solrQuery.setQuery(request.getQ());
        }

        try {
            QueryResponse queryResponse = solrClient.query(solrQuery);
            return mapToSearchResponse(queryResponse, request.getFacetField());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SearchResponse();
    }

    @Override
    public List<String> getAutoSuggestions(String query) {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/suggest");
        solrQuery.set("suggest", true);
        solrQuery.set("suggest.build", true);
        solrQuery.set("suggest.dictionary", "titleSuggester", "authorSuggester");
        solrQuery.set("suggest.q", query);
        try {
            QueryResponse queryResponse = solrClient.query(solrQuery);
            SuggesterResponse suggesterResponse = queryResponse.getSuggesterResponse();
            Map<String, List<String>> suggestedTerms = suggesterResponse.getSuggestedTerms();
            List<String> titleSuggester = suggestedTerms.get("titleSuggester");
            List<String> authorSuggester = suggestedTerms.get("authorSuggester");
            return Stream.concat(titleSuggester.stream(), authorSuggester.stream())
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    private SearchResponse mapToSearchResponse(QueryResponse queryResponse, String facetField) {
        SolrDocumentList results = queryResponse.getResults();
        SearchResponse response = new SearchResponse();
        if (results.getNumFound() == 0) {
            return response;
        }
        response.setNumFound(results.getNumFound());
        List<BookSolr> books = new ArrayList<>();
        results.forEach(book -> {
            BookSolr bookSolr = new BookSolr();
            bookSolr.setId(book.getFieldValue("id").toString());
            bookSolr.setTitle(book.getFieldValue("title").toString());
            bookSolr.setContent(book.getFieldValue("content").toString());
            bookSolr.setLanguage(book.getFieldValue("language").toString());
            List<String> authors = book.getFieldValues("authors").stream().map(Object::toString).collect(Collectors.toList());
            bookSolr.setAuthors(authors);
            books.add(bookSolr);
        });
        response.setBooks(books);
        if (facetField != null) {
            List<FacetField.Count> values = queryResponse.getFacetField(facetField).getValues();
            List<Facet> facets = new ArrayList<>();
            for (FacetField.Count value : values) {
                Facet facet = new Facet();
                facet.setValueCount(value.getCount());
                facet.setValue(value.getName());
                Map<String, String> field = Map.of(facetField, value.getName());
                facet.setField(field);
                facets.add(facet);
            }
            response.setFacets(facets);
        }
        return response;
    }

}
