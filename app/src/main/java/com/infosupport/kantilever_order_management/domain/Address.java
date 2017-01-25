package com.infosupport.kantilever_order_management.domain;

/**
 * Created by kdomi on 17-1-2017.
 */

public class Address {
    private String street, zip, city;

    public Address(String city, String street, String zip) {
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

    public String toString() {
        return city + ", " + street + ", " + zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
