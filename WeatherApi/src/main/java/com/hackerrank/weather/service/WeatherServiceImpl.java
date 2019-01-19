package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

/**
 * Created by Ritu on 6/1/18.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private WeatherRepository repository;

    public WeatherServiceImpl(WeatherRepository repository){
        this.repository = repository;
    }

    @Override
    public Weather add(Weather weather) {
        return repository.save(weather);
    }

    @Override
    public boolean fetch(Long id) {
        boolean res = repository.exists(id);
        return res;
    }

    @Override
        public List<Weather> getWeather(Date date) {
        List<Weather> weatherList = (List<Weather>) repository.findAll();
        List<Weather> responseWeatherList = new ArrayList<>();
        for(Weather weather: weatherList) {
            Date fetchedDate = weather.getDateRecorded();
            if (date.equals(fetchedDate))
                responseWeatherList.add(weather);
        }
        return responseWeatherList;
    }

    @Override
    public List<Weather> getAllWeather() {
        return (List<Weather>) repository.findAll();
    }

    @Override
    public List<Weather> delete(Date start, Date end, String lat, String lon) {
        List<Weather> weatherList = (List<Weather>) repository.findAll();


        List<Weather> responseWeatherList = new ArrayList<>();
        for(Weather weather: weatherList) {

            String latitude = weather.getLocation().getLatitude().toString();
            String longitude = weather.getLocation().getLongitude().toString();
            Date fetchedDate = weather.getDateRecorded();

            if(isAfterOrOn(fetchedDate,start)&&isBeforeOrSame(fetchedDate,end))
                if(lat.equals(latitude)&&lon.equals(longitude))
                        responseWeatherList.add(weather);
        }
        return responseWeatherList;
    }

    @Override
    public List<Weather> getByLatLon(String lat, String lon) {
        List<Weather> weatherList = (List<Weather>) repository.findAll();


        List<Weather> responseWeatherList = new ArrayList<>();
        for (Weather weather : weatherList) {

            String latitude = weather.getLocation().getLatitude().toString();
            String longitude = weather.getLocation().getLongitude().toString();
            if (lat.equals(latitude) && lon.equals(longitude))
                responseWeatherList.add(weather);

        }
        return responseWeatherList;
    }

    //helper func
    public boolean isBeforeOrSame (Date date1, Date date2){
        if(date1.equals(date2))
            return true;
        int year1 = date1.getYear();
        int year2 = date2.getYear();

        int month1 = date1.getMonth();
        int month2 = date2.getMonth();

        int dateInMonth1 = date1.getDate();
        int dateInMonth2 = date2.getDate();
        if(year1<year2)
            return true;
        if(month1<month2)
            return true;
        if(dateInMonth1<dateInMonth2)
            return true;
        return false;


    }
    //helper func
    public boolean isAfterOrOn (Date date1, Date date2){
        if(date1.equals(date2))
            return true;
        return !isBeforeOrSame(date1,date2);
    }

}
