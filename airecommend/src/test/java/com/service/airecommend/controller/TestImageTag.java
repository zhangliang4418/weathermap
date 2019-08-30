package com.service.airecommend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.airecommend.entity.ImageTag;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class TestImageTag {


    @Before
    public void setUp() throws Exception {
        Log4jUtils.init();
    }

    @Test
    public void testMockData() throws Exception {
        System.out.println("unit test for the image tags mock data");

        ClassPathResource resource = new ClassPathResource("mock/tags.json");
        InputStream inputStream = resource.getInputStream();
        String data = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new ObjectMapper();
        ImageTag mockData = mapper.readValue(data, ImageTag.class);
        Assert.assertTrue(mockData != null);
        Assert.assertTrue(mockData.getResult() != null);
    }


}