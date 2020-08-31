package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {

        return Stream.of(text)
                .flatMap(Pattern.compile("[^\\w]+")::splitAsStream)
                .mapToInt((w) -> w.length()).sum();
    }

    @Override
    public int countNumberOfWords(String text) {

        return Stream.of(text)
                .flatMap(Pattern.compile("[^\\w]+")::splitAsStream)
                .mapToInt((w) -> 1).sum();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {

        return Stream.of(text)
                .flatMap(Pattern.compile("[^\\w]+")::splitAsStream)
                .distinct()
                .mapToInt((w) -> 1).sum();
    }

    @Override
    public List<String> getWords(String text) {

        return Stream.of(text)
                .flatMap(Pattern.compile("[^\\w]+")::splitAsStream)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {

        return Stream.of(text)
                .flatMap(Pattern.compile("[^\\w]+")::splitAsStream)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {

        return Stream.of(text)
                .flatMap(Pattern.compile("[^\\w]+")::splitAsStream)
                .collect(Collectors.groupingBy((w) -> w, Collectors.summingInt(w -> 1)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {

        switch (direction) {
            case ASC:
                return Stream.of(text)
                        .flatMap(Pattern.compile("[^\\w]+")::splitAsStream)
                        .sorted((w1, w2) ->
                                ((Integer) ((String) w1).length()).compareTo(((String) w2).length()))
                        .collect(Collectors.toList());
            case DESC:
                return Stream.of(text)
                        .flatMap(Pattern.compile("[^\\w]+")::splitAsStream)
                        .sorted((w1, w2) ->
                                ((Integer) ((String) w2).length()).compareTo(((String) w1).length()))
                        .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
