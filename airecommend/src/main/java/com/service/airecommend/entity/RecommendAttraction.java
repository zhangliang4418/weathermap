package com.service.airecommend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendAttraction {

    @JsonProperty("name")
    private String name;

    @JsonProperty("title")
    private String title;

    @JsonProperty("city")
    private String city;

    @JsonProperty("city_location")
    private String cityLocation;

    @JsonProperty("intro")
    private String intro;

    @JsonProperty("intro_images")
    private List<String> introImages;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("desc_images")
    private List<String> descImages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<String> getIntroImages() {
        return introImages;
    }

    public void setIntroImages(List<String> introImages) {
        this.introImages = introImages;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getDescImages() {
        return descImages;
    }

    public void setDescImages(List<String> descImages) {
        this.descImages = descImages;
    }
}
