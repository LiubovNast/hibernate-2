package com.movie.entity;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED("Deleted Scenes"),
    BEHIND("Behind the Scenes");

    private String value;

    Feature(String value) {
        this.value = value;
    }
}
