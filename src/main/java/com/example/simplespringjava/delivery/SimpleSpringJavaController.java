package com.example.simplespringjava.delivery;

import com.example.simplespringjava.config.AppConstant;
import com.example.simplespringjava.data.DataSample;
import com.example.simplespringjava.exception.definition.CommonException;
import com.example.simplespringjava.exception.definition.NotFound;
import com.example.simplespringjava.model.GeneralResponse;
import com.example.simplespringjava.model.Response;
import com.example.simplespringjava.model.test.ResponseData;
import com.example.simplespringjava.model.test.ResponseInfo;
import com.example.simplespringjava.model.weather.WeatherData;
import com.example.simplespringjava.usecase.SimpleUsecase;
import com.example.simplespringjava.utils.CommonUtils;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping("/demo/api")
@Slf4j
public class SimpleSpringJavaController {

    @Autowired
    SimpleUsecase simpleUsecase;

    @GetMapping("/test/{name}")
    public ResponseEntity<?> get(@PathVariable("name") String name){
        log.info("[Received request by {}][START][{}]", name, new Date());
        /* construct response */
        ResponseData responseData = new ResponseData("Congratulation this is your first demo api", name);
        ResponseInfo response = new ResponseInfo("Ok", ""+new Date(), "Accept", responseData);
        log.info("[Response result][{}]", response);
        /* return response */
        log.info("[Request fulfilled][END][{}]", new Date());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/request-weather-forecast")
    public ResponseEntity<?> getWeatherForecast(
            @RequestHeader(value = "X-Request-Id") String requestId
            , @RequestHeader(value = "X-Channel", defaultValue = "W") String channel
            , @RequestParam(value = "city_name") String cityName){

        String requestFrom = "";
        if(channel.equalsIgnoreCase("W")){
            requestFrom = "WEB_BROWSER";
        }else if(channel.equalsIgnoreCase("M")){
            requestFrom = "MOBILE_BROWSER";
        }
        /* initiate payload response */
        GeneralResponse<Object> generalResponse = new GeneralResponse<>();
        Response<Object> response = new Response<>();

        /* set request id & request at */
        generalResponse.setRequestAt(getCurrentTime());
        generalResponse.setRequestId(requestId);

        log.info("[Request Received - city name: {}][requestFrom: {}][requestId: {}]", cityName, requestFrom, requestId);
        try{
            WeatherData weatherData = getWeatherData(cityName.toUpperCase());

            if(weatherData!=null){
                response.setStatus(AppConstant.COMPLETION_STATUS.SUCCESS);
                response.setMessage("Request Successfully Executed");
                response.setCode("00");
                response.setData(weatherData);
                /* set general response */
                generalResponse.setHttpStatus(HttpStatus.OK);
                generalResponse.setResponse(response);
            }
        }catch (CommonException e){
            log.error("[Request Error - city name: {}][requestFrom: {}][requestId: {}][cause: {}]", cityName, requestFrom, requestId, e.getDisplayMessage());
            response.setStatus(e.getStatus());
            response.setCode(e.getCode());
            response.setMessage(e.getDisplayMessage());
            generalResponse.setHttpStatus(e.getHttpStatus());
            generalResponse.setResponse(response);
        }
        log.info("[Request End - city name: {}][requestFrom: {}][requestId: {}][response: {}]", cityName, requestFrom, requestId,generalResponse.getResponse());
        return new ResponseEntity<>(generalResponse,generalResponse.getHttpStatus());
    }

    @GetMapping("/request-quotes")
    public ResponseEntity<?> getQuotes(
            @RequestHeader(value = "X-Channel", defaultValue = "W", required = false) String channel,
            @RequestHeader(value = "X-Request-Id", required = false) String requestId,
            @RequestParam(value = "category") String category){
        if(requestId==null){
            requestId = AppConstant.APP_NAME+"-"+UUID.randomUUID();
        }
        /* initiate payload response */
        GeneralResponse<Object> generalResponse = simpleUsecase.getQuotes(category);
        /* set request id & request at */
        generalResponse.setRequestAt(getCurrentTime());
        generalResponse.setRequestId(requestId);
        return new ResponseEntity<>(generalResponse,generalResponse.getHttpStatus());
    }

    @GetMapping("/request-for-today")
    public ResponseEntity<?> getToday(
            @RequestHeader(value = "X-Channel", defaultValue = "W", required = false) String channel,
            @RequestHeader(value = "X-Request-Id", required = false) String requestId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category") String category){
        if(requestId==null){
            requestId = AppConstant.APP_NAME+"-"+UUID.randomUUID();
        }
        /* initiate payload response */
        GeneralResponse<Object> generalResponse = simpleUsecase.getMessage(name, category);
        /* set request id & request at */
        generalResponse.setRequestAt(getCurrentTime());
        generalResponse.setRequestId(requestId);
        return new ResponseEntity<>(generalResponse,generalResponse.getHttpStatus());
    }


    public WeatherData getWeatherData(String city) throws NotFound {
        Type typeOfT = new TypeToken<List<WeatherData>>() {}.getType();
        List<WeatherData> configList = CommonUtils.gson.fromJson(DataSample.CITY_DATA, typeOfT);

        return configList.stream()
                .filter(c -> c.getCity().contains(city))
                .findFirst()
                .orElseThrow(() -> new NotFound(String.format("City %s is not found", city)));
    }

    public String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat(AppConstant.DATE_TIME_ISO_FORMAT);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        formatter.setTimeZone(tz);
        Date date = new Date();
        return formatter.format(date);
    }


}
