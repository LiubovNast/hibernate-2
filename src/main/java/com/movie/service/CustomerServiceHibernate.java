package com.movie.service;

import com.movie.dao.*;
import com.movie.dto.CustomerInfo;
import com.movie.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomerServiceHibernate implements CustomerService {

    private final SessionFactory sessionFactory;
    private final CountryDAO countryDAO;
    private final CityDAO cityDAO;
    private final AddressDAO addressDAO;
    private final StoreDAO storeDAO;
    private final CustomerDAO customerDAO;
    private final RentalDAO rentalDAO;
    private final FilmDAO filmDAO;
    private final InventoryDAO inventoryDAO;
    private final StaffDAO staffDAO;
    private final PaymentDAO paymentDAO;


    public CustomerServiceHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        countryDAO = new CountryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
    }

    @Override
    public Customer createCustomer(CustomerInfo customerInfo) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Country country = countryDAO.findByName(customerInfo.getCountry());
            City city = cityDAO.findByNameAndCountry(customerInfo.getCity(), country.getId());

            Address address = getAddress(customerInfo.getAddress(),
                    customerInfo.getDistrict(), customerInfo.getPhone(),
                    customerInfo.getPostalCode(), city);
            Store store = storeDAO.findById((byte) 1);
            Customer customer = new Customer();
            customer.setFirstName(customerInfo.getFirstName());
            customer.setLastName(customerInfo.getLastName());
            customer.setStore(store);
            customer.setEmail(customerInfo.getEmail());
            customer.setAddress(address);
            customer.setActive(customerInfo.getActive());
            customerDAO.save(customer);
            session.getTransaction().commit();
            return customer;
        }

    }

    private Address getAddress(String name, String district, String phone, String postalCode, City city) {
        Address address = new Address();
        address.setAddress(name);
        address.setCode(postalCode);
        address.setDistrict(district);
        address.setPhone(phone);
        address.setCity(city);
        return addressDAO.save(address);
    }

    @Override
    public void customerReturnFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Rental rental = rentalDAO.rentalWithoutReturnDate();
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.update(rental);
            session.getTransaction().commit();
        }
    }

    @Override
    public void customerRentalInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Film film = filmDAO.getFilmForRental();
            Store store = storeDAO.findById((byte) 1);
            Staff staff = staffDAO.findByStore(store);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setRentalDate(LocalDateTime.now());
            rental.setStaff(staff);
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setRental(rental);
            payment.setStaff(staff);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setAmount(BigDecimal.valueOf(123));
            paymentDAO.save(payment);

            session.getTransaction().commit();
        }
    }
}
