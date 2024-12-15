package com.crosska;

import java.util.*;

public class SearchWorker {

    private final Map<Integer, String> columnMap;

    private long duration;

    public SearchWorker(Map<Integer, String> columnMap) {
        this.columnMap = columnMap;
    }

    public Integer[] doSearch(String search) {
        long startTime = System.currentTimeMillis();
        Map<Integer, String> answerMap = new HashMap<>();

        for (Map.Entry<Integer, String> entry : columnMap.entrySet()) {
            if (entry.getValue().startsWith(search)) {
                answerMap.put(entry.getKey(), entry.getValue());
                //System.out.println("RAW: " + entry.getKey() + " : " + entry.getValue());
            }
        }

        answerMap = sortByValues(answerMap);
        Integer[] lines = answerMap.keySet().toArray(Integer[]::new);

        duration = System.currentTimeMillis() - startTime;
        return lines;
    }

    @SuppressWarnings("rawtypes")
    private <K, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        // создаем список записей мапы и сортируем их по значениям
        List<Map.Entry<K, V>> mappings = new ArrayList<>(map.entrySet());
        mappings.sort(Map.Entry.comparingByValue());

        // создаем пустой `LinkedHashMap` с порядком вставки`
        Map<K, V> linkedHashMap = new LinkedHashMap<>();

        // для каждой записи карты в отсортированном списке вставляем пару ключ-значение в `LinkedHashMap`
        for (Map.Entry<K, V> entry : mappings) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
   }

    public long getSearchFileDuration() {
        return duration;
    }

}
