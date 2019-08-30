package com.service.airecommend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.airecommend.entity.RecommendAttraction;
import com.service.airecommend.entity.TagReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Cache
 */
@Component
public class PresetDataStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(PresetDataStorage.class);

    private static Map<String, TagReference> DataTagReferenceMap = new ConcurrentHashMap<String, TagReference>();

    private static Map<String, RecommendAttraction> DataRecommendAttractionMap = new ConcurrentHashMap<String, RecommendAttraction>();

    static {
        try {
            ClassPathResource resource = new ClassPathResource("data/tag_ref_zh.json");
            InputStream inputStream = resource.getInputStream();
            String data = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            DataTagReferenceMap = mapper.readValue(data, new TypeReference<ConcurrentHashMap<String, TagReference>>() {
            });
        } catch (IOException e) {
            LOGGER.error("Failed to get tag ref data.", e);
        }
        try {
            ClassPathResource resource = new ClassPathResource("data/attraction.json");
            InputStream inputStream = resource.getInputStream();
            String data = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            DataRecommendAttractionMap = mapper.readValue(data, new TypeReference<ConcurrentHashMap<String, RecommendAttraction>>() {
            });
        } catch (IOException e) {
            LOGGER.error("Failed to get attraction data.", e);
        }
    }

    public TagReference getDataTagRef(String tag) {
        return DataTagReferenceMap.get(tag);
    }

    public RecommendAttraction getDataRecommendAttraction(String attraction) {
        return DataRecommendAttractionMap.get(attraction);
    }

}
