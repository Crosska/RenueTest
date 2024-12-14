package com.crosska;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchWorker {

    private Map<Integer, String> columnMap;

    private long duration;

    public SearchWorker(Map<Integer, String> columnMap) {
        this.columnMap = columnMap;
    }

    public ArrayList<Integer> doSearch(String search) {
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> lines = new ArrayList<>();
        columnMap = sortByValues(columnMap);
        for (Map.Entry<Integer, String> entry : columnMap.entrySet()) {
            if (entry.getValue().startsWith(search)) {
                lines.add(entry.getKey());
            }
        }
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
