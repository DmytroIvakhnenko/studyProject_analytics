package com.divakhnenko.analytics.service.impl;

import com.divakhnenko.analytics.analysis.StatisticalFunction;
import com.divakhnenko.analytics.converter.Converter;
import com.divakhnenko.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAnalyticsService implements AnalyticsService {
    private final Converter<InputStream, List<Integer>> converter;
    @Autowired
    private final Map<String, StatisticalFunction> functionMap;

    @Override
    public double processData(MultipartFile multipartFile, String function) {
        List<Integer> data = Collections.emptyList();
        try {
            data = converter.convert(multipartFile.getInputStream());
        } catch (IOException e) {
            log.error("Exception happened while extracting inputstream from file {}", e);
        }
        return functionMap.computeIfAbsent(function, key -> {
            throw new RuntimeException("Function " + key + " was not found");
        }).calculate(data);
    }
}
