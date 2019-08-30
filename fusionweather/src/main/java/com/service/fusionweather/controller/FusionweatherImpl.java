package com.service.fusionweather.controller;

import com.service.fusionweather.entity.AttractionRecognition;
import com.service.fusionweather.entity.FusionWeatherSummary;
import com.service.fusionweather.entity.ImageTagParseRequest;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.CseSpringDemoCodegen", date = "2017-11-01T10:27:01.678+08:00")

@CrossOrigin
@RestSchema(schemaId = "fusionweather")
@RequestMapping(path = "/fusionweather", produces = MediaType.APPLICATION_JSON)
public class FusionweatherImpl
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FusionweatherImpl.class);

    @Autowired
    private FusionweatherImplDelegate userFusionweatherdataDelegate;

    @RequestMapping(value = "/show",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public FusionWeatherSummary show(@RequestParam(value = "city", required = true) String city, @RequestParam(value = "user", required = false) String user)
    {
        LOGGER.info("show() is called, city = [{}], user = [{}]", city, user);
        return userFusionweatherdataDelegate.showFusionWeather(city, user);
    }

    @RequestMapping(value = "/airecommend/attractions",
            produces = { "application/json" },
            method = RequestMethod.POST)
    public AttractionRecognition recommendAttractions(@RequestBody ImageTagParseRequest imageTagParseRequest) throws Exception
    {
        LOGGER.info("doRecommendAttractions() is called");
        return userFusionweatherdataDelegate.doRecommendAttractions(imageTagParseRequest);
    }
}
