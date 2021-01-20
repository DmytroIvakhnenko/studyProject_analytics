package com.divakhnenko.analytics.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TextConverter implements Converter<InputStream, List<Integer>> {

    public List<String> splitLine(String line) {
        if (Objects.nonNull(line) && !line.isBlank()) {
            return Arrays.asList(line.split(";"));
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Integer> convert(InputStream inputStream) {
        List<Integer> content = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (reader.ready()) {
                List<String> line = splitLine(reader.readLine());
                content.addAll(line.stream().map(Integer::parseInt).collect(Collectors.toList()));
            }
        } catch (IOException e) {
            log.error("Exception happened during file conversion {}", e);
        }
        return content;
    }
}
