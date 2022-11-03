package com.jmp.reactive.sportreactive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data {
    @JsonProperty("data")
    private List<SportResponse> data;

    public List<SportResponse> getData() {
        return data;
    }
}
