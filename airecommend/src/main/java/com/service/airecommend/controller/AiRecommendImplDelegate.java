package com.service.airecommend.controller;

import com.service.airecommend.entity.*;
import com.service.airecommend.util.HwsAiService;
import com.service.airecommend.util.PresetDataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class AiRecommendImplDelegate {

    @Autowired
    private HwsAiService hwsAiService;

    @Autowired
    private PresetDataStorage presetDataStorage;

    public AttractionRecognition doRecommendAttractions(ImageTagDeclaration imageTagDeclaration) {
        ImageTag tag = hwsAiService.imageTagging(imageTagDeclaration);
        AttractionRecognition recognition = new AttractionRecognition();
        if (tag.getResult() != null && tag.getResult().getTags() != null) {
            Set<RecommendAttraction> rcSet = new HashSet<RecommendAttraction>();
            for (ImageTag.ImageTagItem itItem : tag.getResult().getTags()) {
                TagReference tagReference = presetDataStorage.getDataTagRef(itItem.getTag());
                if (tagReference == null) {
                    continue;
                }
                RecommendAttraction rc = presetDataStorage.getDataRecommendAttraction(tagReference.getAttraction());
                if (rc == null) {
                    continue;
                }
                rcSet.add(rc);
            }

            recognition.setAttractions(new ArrayList<RecommendAttraction>(rcSet));
            recognition.setTags(tag.getResult().getTags());
        }
        return recognition;
    }
}
