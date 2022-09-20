package com.epamjavacc.one.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieReview {
    private Long id;
    private Long movieId;
    private String review;
    private String authorName;
}
