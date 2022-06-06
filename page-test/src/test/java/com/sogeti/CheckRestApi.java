package com.sogeti;

import com.sogeti.domain.CountryDto;
import com.sogeti.domain.PlaceItem;
import com.sogeti.repository.CountryRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class CheckRestApi {

    private static RequestSpecification spec;

    @Test
    public void testCase4_1() {
        String url = "http://api.zippopotam.us/de/bw/stuttgart";
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(url)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        Response response = RestAssured.given(spec).get();
        long responseTimeInMilliSeconds = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assert.assertEquals(ContentType.JSON.toString(),response.getContentType());
        Assert.assertTrue(responseTimeInMilliSeconds < 1000);
        Assert.assertEquals(200,response.getStatusCode());
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        String country = jsonPath.getString("country");
        Assert.assertEquals("Germany", country);
        String state = jsonPath.getString("state");
        Assert.assertEquals("Baden-Württemberg", state);
        List<PlaceItem> places =  jsonPath.getList("places", PlaceItem.class);
        List<String> filtred = places.stream().filter(p -> p.getPostCode().equals("70597")).map(p -> p.getPlaceName()).collect(Collectors.toList());
        Assert.assertTrue(filtred.contains("Stuttgart Degerloch"));
    }

    @Test
    public void testCase4_2() {
        String url = "http://api.zippopotam.us/";

        CountryRepository repository = new CountryRepository();
        List<CountryDto> countryDtos = repository.getList();
        countryDtos.forEach(c -> {
            String requestUrl = url + c.getCountryShort() + "/" + c.getPostalCode();
            spec = new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .setBaseUri(requestUrl)
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())
                    .build();
           Response response = RestAssured.given(spec).get();
           long responseTimeInMilliSeconds = response.getTimeIn(TimeUnit.MILLISECONDS);
           JsonPath jsonPath = new JsonPath(response.getBody().asString());
           Assert.assertEquals(ContentType.JSON.toString(), response.getContentType());
           List<String> placeName = jsonPath.getList("places.'place name'");
           Assert.assertEquals(c.getCityName(),placeName.get(0));
           Assert.assertTrue(responseTimeInMilliSeconds < 1000);
           Assert.assertEquals(200, response.getStatusCode());
        });

    }
}
