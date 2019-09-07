package com.service.airecommend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttractionRecognition {

    @JsonProperty("attractions")
    private List<RecommendAttraction> attractions;

    @JsonProperty("tags")
    private List<RecognitionTag> tags;

    public AttractionRecognition() {
    }

    public List<RecommendAttraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<RecommendAttraction> attractions) {
        this.attractions = attractions;
    }

    public List<RecognitionTag> getTags() {
        return tags;
    }

    public void setTags(List<RecognitionTag> tags) {
        this.tags = tags;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RecognitionTag {

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
