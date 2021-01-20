package com.divakhnenko.analytics.service;

import org.springframework.web.multipart.MultipartFile;

public interface AnalyticsService {
    double processData(MultipartFile multipartFile, String function);
}
