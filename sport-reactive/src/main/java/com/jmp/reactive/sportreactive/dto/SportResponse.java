package com.jmp.reactive.sportreactive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SportResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("attributes")
    private Attributes attributes;

    public Integer getId() {
        return id;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
