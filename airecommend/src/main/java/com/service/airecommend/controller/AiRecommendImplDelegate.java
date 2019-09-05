package com.service.airecommend.controller;

import com.service.airecommend.entity.AttractionRecognition;
import com.service.airecommend.entity.ImageTag;
import com.service.airecommend.entity.ImageTagDeclaration;
import com.service.airecommend.entity.RecommendAttraction;
import com.service.airecommend.util.HwsAiService;
import com.service.airecommend.util.PresetDataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AiRecommendImplDelegate {

    @Autowired
    private HwsAiService hwsAiService;

    @Autowired
    private PresetDataStorage presetDataStorage;

    public AttractionRecognition doRecommendAttractions(ImageTagDeclaration imageTagDeclaration) {
        AttractionRecognition recognition = new AttractionRecognition();

        ImageTag tag = hwsAiService.imageTagging(imageTagDeclaration);

        if (tag.getResult() != null && tag.getResult().getTags() != null) {
            List<RecommendAttraction> rcSet = tag.getResult().getTags()
                    .stream()
                    .map(el -> presetDataStorage.getDataTagRef(el.getTag()))
                    .filter(el -> el != null && el.getAttractions() != null)
                    .flatMap(el -> el.getAttractions().stream())
                    .distinct()
                    .map(presetDataStorage::getDataRecommendAttraction)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            recognition.setAttractions(rcSet);
            recognition.setTags(tag.getResult().getTags());
        }

        return recognition;
    }
}
