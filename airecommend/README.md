# Swagger generated server

CSE Spring MVC Server

## Overview
The code of this module is automatically generated based on the swagger.yaml file provided by the user。The generated code can generally be divided into following categories：

1，`main Method` in class `com.service.airecommend.AiRecommendApplication.java`。

2，`AiRecommendImpl.java` intercepts user's requests, analyze inputs parameters, and delegate handlers to the class `AiRecommendImplDelegate.java` through `springmvc annotation`.

3，`AiRecommendImplDelegate.java` is an implementation class of `handler` where user can implement their own business logic.

5，`com.service.airecommend.entity`. All classes in this package are the definitions defined in swagger.yaml, it's similar to the `MODEL` in `SpringMVC`

6，`resource` directory defines some configuration files, including log4j configuration, microservice configuration and swagger protocol files.

> **NOTE**:  **make sure the configuration in microservce.yaml is correct.**