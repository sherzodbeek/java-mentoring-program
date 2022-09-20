package com.epamjavacc.one.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "movie")
public class Movie {

    @Id
    private Long id;
    private String title;
}
