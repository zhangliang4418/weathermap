package com.service.airecommend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageTag {

    /**
     * result : {"tags":[{"confidence":"85.17","tag":"肖像"},{"confidence":"83.36","tag":"墙"},{"confidence":"83.14","tag":"成年人"}]}
     */

    @JsonProperty("result")
    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResultBean {
        @JsonProperty("tags")
        private List<ImageTagItem> tags;

        public List<ImageTagItem> getTags() {
            return tags;
        }

        public void setTags(List<ImageTagItem> tags) {
            this.tags = tags;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageTagItem {
        /**
         * confidence : 85.17
         * tag : 肖像
         */
        @JsonProperty("confidence")
        private float confidence;

        @JsonProperty("tag")
        private String tag;

        public float getConfidence() {
            return confidence;
        }

        public void setConfidence(float confidence) {
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
