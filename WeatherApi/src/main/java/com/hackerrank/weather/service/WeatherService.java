package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;

import java.util.Date;
import java.util.List;

/**
 * Created by Ritu on 6/1/18.
 */
public interface WeatherService {

    Weather add(Weather weather);

    boolean fetch(Long id);

    List<Weather> getWeather(Date date);

    List<Weather> getAllWeather();

    List<Weather> delete(Date start, Date end, String lat, String lon);

    List<Weather> getByLatLon(String lat, String lon);
}
