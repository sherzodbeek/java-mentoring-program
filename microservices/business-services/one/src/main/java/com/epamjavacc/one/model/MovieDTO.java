package com.epamjavacc.one.model;

import com.epamjavacc.one.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.CollectionModel;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private CollectionModel<MovieReview> reviews;

    public MovieDTO(Movie movie, CollectionModel<MovieReview> reviews) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.reviews = reviews;
    }
}
