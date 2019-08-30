package com.service.airecommend.controller;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.service.airecommend.entity.AttractionRecognition;
import com.service.airecommend.entity.ImageTagDeclaration;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2019-08-19T10:16:52.801+08:00")

@RestSchema(schemaId = "airecommend")
@RequestMapping(path = "/airecommend", produces = MediaType.APPLICATION_JSON)
public class AiRecommendImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(AiRecommendImpl.class);

    @Autowired
    private AiRecommendImplDelegate AIRecommendImplDelegate;

    private int latencyTime = 0;

    @PostConstruct
    public void init() {
        LOGGER.info("Init success");
        DynamicIntProperty latency = DynamicPropertyFactory.getInstance().getIntProperty("latency", 0);
        latency.addCallback(() -> {
            latencyTime = latency.get();
            LOGGER.info("Latency time change to {}", latencyTime);
        });
        latencyTime = latency.get();
    }


    @CrossOrigin
    @RequestMapping(value = "/attractions",
            produces = {"application/json"},
            method = RequestMethod.POST)
    public AttractionRecognition recommendAttractions(@RequestBody ImageTagDeclaration imageTagDeclaration) {
        LOGGER.info("getImageTags() is called, body = [{}]", imageTagDeclaration);
        if (latencyTime > 0) {
            try {
                Thread.sleep(latencyTime);
            } catch (Exception e) {

            }
        }
        return AIRecommendImplDelegate.doRecommendAttractions(imageTagDeclaration);
    }
}
