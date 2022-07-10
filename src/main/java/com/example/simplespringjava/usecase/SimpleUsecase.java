package com.example.simplespringjava.usecase;

import com.example.simplespringjava.config.AppConstant;
import com.example.simplespringjava.config.AppProperties;
import com.example.simplespringjava.exception.definition.CommonException;
import com.example.simplespringjava.model.GeneralResponse;
import com.example.simplespringjava.model.Response;
import com.example.simplespringjava.model.rest.AgifyRs;
import com.example.simplespringjava.model.rest.QuotesRs;
import com.example.simplespringjava.service.SimpleServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class SimpleUsecase {

    @Autowired
    SimpleServices simpleServices;

    public GeneralResponse<Object> getQuotes(String category){
        GeneralResponse<Object> generalResponse = new GeneralResponse<>();
        Response<Object> apiRs = new Response<>();
        List<QuotesRs> quotes = new ArrayList<>();

        try {
            /*invoke services*/
            quotes = simpleServices.getQoutesSvc(category);
            /*construct success request*/
            apiRs.setData(quotes);
            apiRs.setMessage("Ok");
            apiRs.setStatus(AppConstant.COMPLETION_STATUS.SUCCESS);
            apiRs.setCode("00");
            generalResponse.setResponse(apiRs);
            generalResponse.setHttpStatus(HttpStatus.OK);
        }catch (CommonException e){
            /*construct failed request*/
            apiRs.setStatus(e.getStatus());
            apiRs.setCode(e.getCode());
            apiRs.setMessage(e.getDisplayMessage());
            generalResponse.setHttpStatus(e.getHttpStatus());
            generalResponse.setResponse(apiRs);
        }
        return generalResponse;
    }

    public GeneralResponse<Object> getMessage(String name, String category){
        GeneralResponse<Object> generalResponse = new GeneralResponse<>();
        RestTemplate restTemplate = new RestTemplate();
        Response<Object> apiRs = new Response<>();
        AgifyRs agifyRs = new AgifyRs();
        List<QuotesRs> quotes = new ArrayList<>();
        String day = "";
        String message = "";

        try {
            category = category.toLowerCase();
            agifyRs = simpleServices.getAgifySvc(name);
            quotes = simpleServices.getQoutesSvc(category);

            List<String> badQuotesList = Arrays.asList(AppProperties.BAD_QUOTES.split(","));
            List<String> goodQuotesList = Arrays.asList(AppProperties.GOOD_QUOTES.split(","));
            //decide good day or bad day
            if(badQuotesList.contains(category)){
                day = "bad";
            }else if(goodQuotesList.contains(category)){
                day = "good";
            }
            String quote = quotes.stream().findAny().get().getQuote();
            String author = quotes.stream().findAny().get().getAuthor();
            String age = String.valueOf(agifyRs.getAge());

            message = String.format("Hello %s what a %s day for you. Your quotes for today is about %s, \"%s\" it's by %s. Hope you enjoy your %s birthday. See You Next Time!", name, day, category, quote, author, age);
            /*construct success request*/
            apiRs.setData(message);
            apiRs.setMessage("Ok");
            apiRs.setStatus(AppConstant.COMPLETION_STATUS.SUCCESS);
            apiRs.setCode("00");
            generalResponse.setResponse(apiRs);
            generalResponse.setHttpStatus(HttpStatus.OK);
        }catch (CommonException e){
            /*construct failed request*/
            apiRs.setStatus(e.getStatus());
            apiRs.setCode(e.getCode());
            apiRs.setMessage(e.getDisplayMessage());
            generalResponse.setHttpStatus(e.getHttpStatus());
            generalResponse.setResponse(apiRs);
        }

        return generalResponse;
    }


}
