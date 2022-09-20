package com.epam.javacc.two.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "movie_review")
public class MovieReview {
    @Id
    private Long id;
    private Long movieId;
    private String review;
    private String authorName;
}
