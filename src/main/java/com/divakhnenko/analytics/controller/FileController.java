package com.divakhnenko.analytics.controller;

import com.divakhnenko.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class FileController {
    private final AnalyticsService analyticsService;

    @PostMapping("/upload")
    public HttpEntity<String> processFile(@RequestParam("file") MultipartFile file, @RequestParam("function") String function)  {
        return new HttpEntity<String>(String.valueOf(analyticsService.processData(file, function)));
    }

    @GetMapping("/")
    public String homeView(Model model) {
        return "index";
    }
}