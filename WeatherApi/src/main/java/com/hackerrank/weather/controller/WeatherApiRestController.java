package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.LocationService;
import com.hackerrank.weather.service.WeatherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class WeatherApiRestController {

    private LocationService locationService;
    private WeatherService weatherService;

    WeatherApiRestController(LocationService locationService,WeatherService weatherService){
        this.locationService = locationService;
        this.weatherService=weatherService;
    }

    @PostMapping("/weather")
    public ResponseEntity<Weather> addWeather(@RequestBody Weather weather) {

        Long id = weather.getId();
        if(weatherService.fetch(id))
            return new ResponseEntity<Weather>(HttpStatus.BAD_REQUEST);
        Weather responseWeather = weatherService.add(weather);
        ResponseEntity<Weather> responseEntity = new ResponseEntity<Weather>(responseWeather, HttpStatus.CREATED);
        return responseEntity;
    }

    //I had to modify three get API by something, else it was throwing error
    @GetMapping("/weather1")
    public ResponseEntity<List<Weather>> get(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                         Date date){
        List<Weather> responseWeatherList = weatherService.getWeather(date);
        return new ResponseEntity<List<Weather>>(responseWeatherList, HttpStatus.OK);
    }

    @GetMapping("/weather2")
    public ResponseEntity<List<Weather>> getByLatLon(@RequestParam("lat") String lat, @RequestParam("lon") String lon){
        List<Weather> responseWeatherList = weatherService.getByLatLon(lat,lon);
        return new ResponseEntity<List<Weather>>(responseWeatherList, HttpStatus.OK);
    }

    @GetMapping("/weather")
    public ResponseEntity<List<Weather>> getAll(){
        List<Weather> responseWeatherList = weatherService.getAllWeather();
        return new ResponseEntity<List<Weather>>(responseWeatherList, HttpStatus.OK);
    }

    @DeleteMapping("/erase")
    public ResponseEntity<List<Weather>> delete(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,//String start
                                                @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end,// String end,//
                                                @RequestParam("lat") String lat,
                                                @RequestParam("lon") String lon) throws ParseException {
        List<Weather> responseWeatherList = weatherService.delete(start,end,lat,lon);
        return new ResponseEntity<List<Weather>>(responseWeatherList, HttpStatus.OK);
    }
}
