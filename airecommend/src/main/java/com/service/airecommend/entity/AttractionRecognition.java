package com.service.airecommend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttractionRecognition {

    @JsonProperty("attractions")
    private List<RecommendAttraction> attractions;

    @JsonProperty("tags")
    private List<ImageTag.ImageTagItem> tags;

    public AttractionRecognition() {
    }

    public List<RecommendAttraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<RecommendAttraction> attractions) {
        this.attractions = attractions;
    }

    public List<ImageTag.ImageTagItem> getTags() {
        return tags;
    }

    public void setTags(List<ImageTag.ImageTagItem> tags) {
        this.tags = tags;
    }
}
