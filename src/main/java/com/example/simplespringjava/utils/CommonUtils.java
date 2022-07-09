package com.example.simplespringjava.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonUtils {

    public static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
}
