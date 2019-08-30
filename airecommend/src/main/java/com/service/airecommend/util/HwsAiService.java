package com.service.airecommend.util;

import com.huawei.ais.sdk.AisAccess;
import com.huawei.ais.sdk.util.HttpClientUtils;
import com.service.airecommend.entity.ImageTag;
import com.service.airecommend.entity.ImageTagDeclaration;
import io.vertx.core.json.Json;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HwsAiService {

    @Autowired
    @Qualifier("hwsAisAccess")
    private AisAccess hwsAisAccess;

    public ImageTag imageTagging(ImageTagDeclaration imageTagDeclaration) {
        ImageTag tag = new ImageTag();
        try {
            String uri = "/v1.0/image/tagging";
            StringEntity stringEntity = new StringEntity(Json.encode(imageTagDeclaration), "utf-8");
            HttpResponse response = hwsAisAccess.post(uri, stringEntity);
            String content = HttpClientUtils.convertStreamToString(response.getEntity().getContent());
            tag = Json.decodeValue(content, ImageTag.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }
}
