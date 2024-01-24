package com.movie.dao;

import com.movie.entity.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends GenericDAO<Category, Byte>{
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
