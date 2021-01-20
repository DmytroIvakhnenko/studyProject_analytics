package com.divakhnenko.analytics.analysis.impl;

import com.divakhnenko.analytics.analysis.StatisticalFunction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("mod")
public class Mode implements StatisticalFunction {
    @Override
    public double calculate(List<Integer> inputData) {
        return getMode(inputData);
    }

    private int getMode(List<Integer> inputData) {
        Map<Integer, Long> data = inputData.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return data.entrySet().stream()
                .max((first, second) -> {
                    return (int) (first.getValue() - second.getValue());
                })
                .map(Map.Entry::getKey)
                .orElse(0);
    }
}
