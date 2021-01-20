package com.divakhnenko.analytics.analysis.impl;

import com.divakhnenko.analytics.analysis.StatisticalFunction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("avg")
public class Average implements StatisticalFunction {
    @Override
    public double calculate(List<Integer> inputData) {
        return inputData.stream()
                .mapToDouble(v -> v)
                .average()
                .orElse(0.0);
    }
}
