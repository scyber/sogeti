package com.sogeti.domain;


import com.opencsv.bean.CsvBindByPosition;


public class CountryDto {


    @CsvBindByPosition(position = 0)
    private String countryShort;

    @CsvBindByPosition(position = 1)
    private String postalCode;

    @CsvBindByPosition(position = 2)
    private String cityName;

    public CountryDto(){

    }

    public void setCountryShort(String countryShort) {
        this.countryShort = countryShort;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryShort() {
        return countryShort;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCityName() {
        return cityName;
    }




}
