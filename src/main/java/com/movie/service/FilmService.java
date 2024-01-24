package com.movie.service;

import com.movie.dto.FilmInfo;
import com.movie.entity.Film;

public interface FilmService {

    Film createNewFilm(FilmInfo filmInfo);
}
