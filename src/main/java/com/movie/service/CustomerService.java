package com.movie.service;

import com.movie.dto.CustomerInfo;
import com.movie.entity.Customer;

public interface CustomerService {

Customer createCustomer(CustomerInfo customerInfo);

void customerReturnFilm();

void customerRentalInventory(Customer customer);
}
