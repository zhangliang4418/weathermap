package com.service.fusionweather.controller;

import com.service.fusionweather.entity.*;
import io.vertx.core.json.Json;
import org.apache.servicecomb.foundation.common.http.HttpStatus;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.swagger.invocation.exception.CommonExceptionData;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionFactory;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class FusionweatherImplDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(FusionweatherImplDelegate.class);

    private static final RestTemplate invoker = RestTemplateBuilder.create();

    @Value("${weathermap.image.bucket}")
    private String bucketEndpoint;

    @Value("${weathermap.image.suffix}")
    private String imageSuffix;

    public FusionWeatherSummary showFusionWeather(String city, String user) {
        FusionWeatherSummary summary = new FusionWeatherSummary();
        summary.setCurrentWeather(achieveCurrentWeatherSummary(city, user));
        summary.setForecastWeather(achieveForecastWeatherSummary(city));

        return summary;
    }

    public AttractionRecognition doRecommendAttractions(ImageTagParseRequest imageTagParseRequest) throws Exception {
        //final String url = "http://127.0.0.1:13096/airecommend/attractions";
        final String url = "cse://airecommend/airecommend/attractions";
        AttractionRecognition su;
        try {
            Object s = invoker.postForObject(url, imageTagParseRequest, ImageTagParseRequest.class, new Object());
            su = Json.decodeValue(Json.encode(s), AttractionRecognition.class);
        } catch (InvocationException e) {
            LOGGER.error("FusionweatherdataDelegate>> Failed to parse an image tag", e);
            if (e.getCause() != null) {
                throw ExceptionFactory.create(new HttpStatus(400, "Failed to parse an image tag."),
                        new CommonExceptionData(e.getCause().getMessage()));
            } else {
                throw ExceptionFactory.create(new HttpStatus(400, "Failed to parse an image tag."),
                        new CommonExceptionData(e.getCause().getMessage()));
            }
        } catch (Exception e) {
            LOGGER.error("FusionweatherdataDelegate>> Failed to parse an image tag", e);
            throw ExceptionFactory.create(new HttpStatus(400, "Failed to parse an image tag."),
                    new CommonExceptionData(e.getMessage()));
        }
        return su;
    }

    private CurrentWeatherSummary achieveCurrentWeatherSummary(String city, String user) {
        //        final String url = StringUtils.isNotBlank(user) && user.equalsIgnoreCase("beta") ?
        //                "http://127.0.0.1:13093/currentweatherdata/show?city=" + city :
        //                "http://127.0.0.1:13090/currentweatherdata/show?city=" + city;
        String url = "cse://weather/weather/show?city=" + city;
        if (!StringUtils.isEmpty(user)) {
            url = url + "&user=" + user;
        }
        CurrentWeatherSummary su;
        try {
            Object s = invoker.getForObject(url, Object.class, new Object());
            su = Json.decodeValue(Json.encode(s), CurrentWeatherSummary.class);
            su.setImageUrl(String.format("%s/%s.%s", bucketEndpoint, su.getImage(), imageSuffix));
        } catch (Exception e) {
            LOGGER.error("FusionweatherdataDelegate>> Failed to achieve the current weather summary", e);
            su = new CurrentWeatherSummary();
        }
        return su;
    }

    private ForecastWeatherSummary achieveForecastWeatherSummary(String city) {
        //final String url = "http://127.0.0.1:13091/forecastweatherdata/show?city=" + city;
        final String url = "cse://forecast/forecast/show?city=" + city;
        ForecastWeatherSummary su;
        try {
            Object s = invoker.getForObject(url, Object.class, new Object());
            su = Json.decodeValue(Json.encode(s), ForecastWeatherSummary.class);
            if (su.getDateList() != null) {
                su.getDateList().forEach(el -> {
                    el.setImageUrl(String.format("%s/%s.%s", bucketEndpoint, el.getImage(), imageSuffix));
                });
            }
        } catch (Exception e) {
            LOGGER.error("FusionweatherdataDelegate>> Failed to achieve the forecast weather summary", e);
            su = new ForecastWeatherSummary();
        }
        return su;
    }
}
