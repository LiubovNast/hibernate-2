package com.movie.dto;

import com.movie.entity.Rating;

import java.math.BigDecimal;

public class FilmInfo {
    private String title;
    private String description;
    private Byte duration;
    private BigDecimal rate;
    private BigDecimal cost;

    public FilmInfo(String title, String description, Byte duration, BigDecimal rate, BigDecimal cost) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.rate = rate;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Byte getDuration() {
        return duration;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
