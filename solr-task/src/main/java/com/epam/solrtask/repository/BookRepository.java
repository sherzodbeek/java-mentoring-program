package com.epam.solrtask.repository;

import com.epam.solrtask.document.BookSolr;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CustomRepository, SolrCrudRepository<BookSolr, String> {

}
