package com.epam.solrtask.repository;

import com.epam.solrtask.model.SearchRequest;
import com.epam.solrtask.model.SearchResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRepository {

    SearchResponse search(SearchRequest request);

    List<String> getAutoSuggestions(String query);

}
