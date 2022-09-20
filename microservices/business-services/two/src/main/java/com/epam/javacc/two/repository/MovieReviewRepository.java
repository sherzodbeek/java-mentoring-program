package com.epam.javacc.two.repository;

import com.epam.javacc.two.entity.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "reviews", collectionResourceRel = "reviews")
public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

    List<MovieReview> findAllByMovieId(Long movieId);


}
