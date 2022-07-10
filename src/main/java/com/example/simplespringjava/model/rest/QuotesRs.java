package com.example.simplespringjava.model.rest;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QuotesRs {

    private String quote;
    private String author;
    private String category;

}
