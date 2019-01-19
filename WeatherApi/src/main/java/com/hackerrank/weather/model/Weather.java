package com.hackerrank.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Weather {
    @Id
    private Long id;

    @JsonProperty("date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateRecorded;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Location location;

    @Lob
    @Basic
    private Float[] temperature;

    public Weather() {
    }

    public Weather(Long id, Date dateRecorded, Location location, Float[] temperature) {
        this.id = id;
        this.dateRecorded = dateRecorded;
        this.location = location;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(Date dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Float[] getTemperature() {
        return temperature;
    }

    public void setTemperature(Float[] temperature) {
        this.temperature = temperature;
    }
}
