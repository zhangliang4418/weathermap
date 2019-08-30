package com.service.fusionweather.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttractionRecognition {

    @JsonProperty("attractions")
    private List<RecommendAttraction> cities;

    @JsonProperty("tags")
    private List<ImageTagItem> tags;

    public AttractionRecognition() {
    }

    public List<RecommendAttraction> getCities() {
        return cities;
    }

    public void setCities(List<RecommendAttraction> cities) {
        this.cities = cities;
    }

    public List<ImageTagItem> getTags() {
        return tags;
    }

    public void setTags(List<ImageTagItem> tags) {
        this.tags = tags;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RecommendAttraction {

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


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageTagItem {
        /**
         * confidence : 85.17
         * tag : 肖像
         */
        @JsonProperty("confidence")
        private String confidence;

        @JsonProperty("tag")
        private String tag;

        public String getConfidence() {
            return confidence;
        }

        public void setConfidence(String confidence) {
            this.confidence = confidence;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

}
