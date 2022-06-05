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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class testRestApi {

    private static RequestSpecification spec;

    @Test
    @DisplayName("Check Stuttgart API info")
    public void testCase4_1(){
        String url = "http://api.zippopotam.us/de/bw/stuttgart";
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(url)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        Response response = RestAssured.given(spec).get();
        long responseTimeInMilliSeconds = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assertions.assertEquals(ContentType.JSON.toString(),response.getContentType());
        Assertions.assertTrue(responseTimeInMilliSeconds < 1000);
        Assertions.assertEquals(200,response.getStatusCode());
        JsonPath jsonPath = new JsonPath(response.getBody().asString());

        String country = jsonPath.getString("country");
        Assertions.assertEquals("Germany", country);
        String state = jsonPath.getString("state");
        Assertions.assertEquals("Baden-Württemberg", state);

        List<PlaceItem> places =  jsonPath.getList("places", PlaceItem.class);

       List<String> filtred = places.stream().filter(p -> p.getPostCode().equals("70597")).map(p -> p.getPlaceName()).collect(Collectors.toList());
       Assertions.assertTrue(filtred.contains("Stuttgart Degerloch"));

    }

    @Test
    @DisplayName("Check countries API Info")
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
           Assertions.assertEquals(ContentType.JSON.toString(), response.getContentType());
           List<String> placeName = jsonPath.getList("places.'place name'");
           Assertions.assertEquals(c.getCityName(),placeName.get(0));
           Assertions.assertTrue(responseTimeInMilliSeconds < 1000);
           Assertions.assertEquals(200, response.getStatusCode());
        });

    }
}
