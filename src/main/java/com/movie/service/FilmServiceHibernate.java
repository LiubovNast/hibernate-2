package com.movie.service;

import com.movie.dao.ActorDAO;
import com.movie.dao.CategoryDAO;
import com.movie.dao.FilmDAO;
import com.movie.dao.LanguageDAO;
import com.movie.dto.FilmInfo;
import com.movie.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class FilmServiceHibernate implements FilmService {
    private final SessionFactory sessionFactory;
    private final FilmDAO filmDAO;
    private final ActorDAO actorDAO;
    private final CategoryDAO categoryDAO;
    private final LanguageDAO languageDAO;


    public FilmServiceHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        filmDAO = new FilmDAO(sessionFactory);
        actorDAO = new ActorDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
    }

    @Override
    public Film createNewFilm(FilmInfo filmInfo) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = languageDAO.findById((byte) 1);
            Set<Category> categories = new HashSet<>();
            categories.add(categoryDAO.findById((byte) 1));
            Set<Actor> actors = new HashSet<>();
            actors.add(actorDAO.findById((short) 1));

            Film film = getFilm(actors, categories, language, filmInfo);
            filmDAO.save(film);

            session.getTransaction().commit();
        }
        return null;
    }

    private static Film getFilm(Set<Actor> actors, Set<Category> categories, Language language, FilmInfo filmInfo) {
        Film film = new Film();
        film.setTitle(filmInfo.getTitle());
        film.setActors(actors);
        film.setCategories(categories);
        film.setDescription(filmInfo.getDescription());
        film.setLanguage(language);
        film.setLength((short) 15);
        film.setRating(Rating.R);
        film.setRentalDuration((filmInfo.getDuration()));
        film.setRentalRate(filmInfo.getRate());
        film.setReplacementCost(filmInfo.getCost());
        film.setYear(Year.of(2023));
        return film;
    }
}
