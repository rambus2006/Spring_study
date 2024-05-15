package com.example.mvc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KanyeQuote {
    @JsonProperty("quote")
    private String quote;

    @Override
    public String toString() {
        return "KanyeQuote{" +
                "quote='" + quote + '\'' +
                '}';
    }
}
