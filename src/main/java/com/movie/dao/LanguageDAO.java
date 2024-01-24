package com.movie.dao;

import com.movie.entity.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends GenericDAO<Language, Byte> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
