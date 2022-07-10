package com.example.simplespringjava.model.rest;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AgifyRs {

    private String name;
    private int age;
    private int count;

}
