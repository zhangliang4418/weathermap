package com.service.airecommend.util;

import com.huawei.ais.common.AuthInfo;
import com.huawei.ais.common.ProxyHostInfo;
import com.huawei.ais.sdk.AisAccess;
import com.huawei.ais.sdk.AisAccessWithProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RestConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfiguration.class);

    private static final int connectionTimeout = 10000;

    private static final int connectionRequestTimeout = 5000;

    private static final int socketTimeout = 20000;

    private static final int retryTimes = 3;

    private static final Map<String, String> endponitMap = new HashMap<String, String>() {{
        /*  图像识别服务的区域和终端节点信息可以从如下地址查询
         *  http://developer.huaweicloud.com/dev/endpoint
         * */
        put("cn-north-1", "https://image.cn-north-1.myhuaweicloud.com");
        put("cn-north-4", "https://image.cn-north-4.myhuaweicloud.com");
        put("ap-southeast-1", "https://image.ap-southeast-1.myhuaweicloud.com");
    }};

    @Value("${ais.region}")
    private String region;

    @Value("${ais.ak}")
    private String ak;

    @Value("${ais.sk}")
    private String sk;

    @Value("${proxy.enabled}")
    private boolean proxyEnable;

    @Value("${proxy.host}")
    private String proxyHost;

    @Value("${proxy.port}")
    private int proxyPort;

    @Value("${proxy.user}")
    private String proxyUser;

    @Value("${proxy.password}")
    private String proxyPassworld;

    @Value("${rest.readtimeout}")
    private int restReadTimeout;

    @Value("${rest.connecttimeout}")
    private int restConnectionTimeout;

    @Bean("hwsAisAccess")
    public AisAccess getHwsAisAccess() {
        if (proxyEnable) {
            ProxyHostInfo proxyHostInfo = new ProxyHostInfo(proxyHost, proxyPort, proxyUser, proxyPassworld);
            return new AisAccessWithProxy(new AuthInfo(endponitMap.get(region), region, ak, sk), proxyHostInfo,
                    connectionTimeout, connectionRequestTimeout, socketTimeout, retryTimes);
        } else {
            return new AisAccess(new AuthInfo(endponitMap.get(region), region, ak, sk),
                    connectionTimeout, connectionRequestTimeout, socketTimeout, retryTimes);
        }
    }
}