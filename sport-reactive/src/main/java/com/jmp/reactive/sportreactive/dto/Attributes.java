package com.jmp.reactive.sportreactive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attributes {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}
