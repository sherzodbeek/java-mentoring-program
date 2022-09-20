package com.epamjavacc.one.feign;

import com.epamjavacc.one.model.MovieReview;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-gateway", url = "http://apigateway:8765")
public interface ReviewsFeignClient {

    @GetMapping("/api/reviews/search/findAllByMovieId")
    CollectionModel<MovieReview> getMovieReviews(@RequestParam("movieId") Long movieId);
}