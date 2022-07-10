package com.example.simplespringjava.service;

import com.example.simplespringjava.config.AppConstant;
import com.example.simplespringjava.config.AppProperties;
import com.example.simplespringjava.exception.definition.RestApiException;
import com.example.simplespringjava.model.rest.AgifyRs;
import com.example.simplespringjava.model.rest.QuotesRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SimpleServices {

    public HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Api-Key", AppProperties.API_KEY_APININJA);
        return headers;
    }

    public List<QuotesRs> getQoutesSvc(String category) throws RestApiException {
        List<QuotesRs> quotes = new ArrayList<>();
        String url = AppProperties.URL_QUOTESAPI.replace("{category}", category);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<QuotesRs>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(null,getHttpHeaders()),
                new ParameterizedTypeReference<List<QuotesRs>>() {});

        if(response.getBody()==null){
            log.error("[Couldn't invoke bored api server][Error][Failed to invoke Available API]");
            throw new RestApiException(AppConstant.COMPLETION_STATUS.SYSTEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, "99", "Failed to invoke Available API");
        }else{
            if(response.getStatusCode().is2xxSuccessful()){
                quotes = response.getBody();
            }else{
                throw new RestApiException(AppConstant.COMPLETION_STATUS.SYSTEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, "99", "Failed to invoke Available API");
            }
        }
        return quotes;
    }

    public AgifyRs getAgifySvc (String name) throws RestApiException {
        AgifyRs agifyRs = new AgifyRs();
        RestTemplate restTemplate = new RestTemplate();
        String url = AppProperties.URL_AGIFY.replace("{name}", name);
        ResponseEntity<AgifyRs> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(null,new HttpHeaders()),
                new ParameterizedTypeReference<AgifyRs>() {});

        if(response.getBody()==null){
            log.error("[Couldn't invoke bored api server][Error][Failed to invoke Available API]");
            throw new RestApiException(AppConstant.COMPLETION_STATUS.SYSTEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, "99", "Failed to invoke Available API");
        }else{
            if(response.getStatusCode().is2xxSuccessful()){
                agifyRs = response.getBody();
            }else{
                throw new RestApiException(AppConstant.COMPLETION_STATUS.SYSTEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, "99", "Failed to invoke Available API");
            }
        }
        return agifyRs;
    }

}
