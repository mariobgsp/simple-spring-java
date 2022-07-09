package com.example.simplespringjava.model.weather;

import java.util.List;

public class WeatherData {

    private List<String> city;
    private String condition;
    private int temperature;
    private double humidity;
    private int maxUvIndex;
    private double cloudCover;

    public WeatherData(List<String> city,String condition,int temperature,double humidity,int maxUvIndex,double cloudCover) {
        this.city = city;
        this.condition = condition;
        this.temperature = temperature;
        this.humidity = humidity;
        this.maxUvIndex = maxUvIndex;
        this.cloudCover = cloudCover;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getMaxUvIndex() {
        return maxUvIndex;
    }

    public void setMaxUvIndex(int maxUvIndex) {
        this.maxUvIndex = maxUvIndex;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }
}
