package com.divakhnenko.analytics.analysis.impl;

import com.divakhnenko.analytics.analysis.StatisticalFunction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.DoubleStream;

@Component("med")
public class Median implements StatisticalFunction {
    @Override
    public double calculate(List<Integer> inputData) {
        return getMedian(inputData);
    }

    private double getMedian(List<Integer> inputData) {
        DoubleStream sortedData = inputData.stream().mapToDouble(v -> v).sorted();
        if (inputData.size() % 2 == 0) {
            return sortedData.skip(inputData.size()/2 - 1).limit(2).average().orElse(0.0);
        } else {
            return sortedData.skip(inputData.size()/2).findFirst().orElse(0.0);
        }
    }
}
