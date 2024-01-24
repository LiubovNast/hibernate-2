package com.movie.dto;

public class CustomerInfo {
    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String district;

    private String postalCode;

    private String phone;

    private String city;

    private String country;

    private Boolean active;

    public CustomerInfo(String firstName, String lastName, String email, String address, String district,
                        String postalCode, String phone, String city, String country, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.district = district;
        this.postalCode = postalCode;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Boolean getActive() {
        return active;
    }
}
