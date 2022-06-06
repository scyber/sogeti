package com.sogeti.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"place name", "longitude", "post code", "latitude"})
public class PlaceItem {
    @JsonProperty("place name")
    private String placeName;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("post code")
    private String postCode;
    @JsonProperty("latitude")
    private String latitude;

    public PlaceItem(){
        super();
    }
    public PlaceItem(String placeName, String longitude, String postCode, String latitude) {
        this.placeName = placeName;
        this.longitude = longitude;
        this.postCode = postCode;
        this.latitude = latitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
