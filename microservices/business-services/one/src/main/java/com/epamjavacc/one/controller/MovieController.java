package com.epamjavacc.one.controller;

import com.epamjavacc.one.entity.Movie;
import com.epamjavacc.one.feign.ReviewsFeignClient;
import com.epamjavacc.one.model.MovieDTO;
import com.epamjavacc.one.model.MovieReview;
import com.epamjavacc.one.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@Slf4j
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final ReviewsFeignClient reviewsFeignClient;

    @Autowired
    public MovieController(MovieRepository movieRepository, ReviewsFeignClient reviewsFeignClient) {
        this.movieRepository = movieRepository;
        this.reviewsFeignClient = reviewsFeignClient;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable("movieId") Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        log.info("Sending request to review service");
        CollectionModel<MovieReview> movieReviews = reviewsFeignClient.getMovieReviews(movieId);
        return ResponseEntity.ok(new MovieDTO(movie, movieReviews));
    }

}
