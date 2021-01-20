package com.divakhnenko.analytics.converter;

public interface Converter <I, O> {
    O convert(I input);
}
