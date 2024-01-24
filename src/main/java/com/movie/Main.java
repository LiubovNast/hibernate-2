package com.movie;


import com.movie.config.ConfigurationHibernate;
import com.movie.dto.CustomerInfo;
import com.movie.dto.FilmInfo;
import com.movie.entity.Customer;
import com.movie.service.CustomerService;
import com.movie.service.CustomerServiceHibernate;
import com.movie.service.FilmService;
import com.movie.service.FilmServiceHibernate;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = ConfigurationHibernate.getSessionFactory();
        FilmService filmService = new FilmServiceHibernate(sessionFactory);
        filmService.createNewFilm(new FilmInfo("Monarh", "about film", (byte) 15, BigDecimal.valueOf(10), BigDecimal.valueOf(40)));
        CustomerService customerService = new CustomerServiceHibernate(sessionFactory);
        Customer customer = customerService.createCustomer(new CustomerInfo("Jack",
                "Down", "koo@list.com", "45 Hey", "Alberta",
                "17886", "140333300000", "Abha", "Saudi Arabia", true));
        customerService.customerReturnFilm();
        customerService.customerRentalInventory(customer);
    }
}
