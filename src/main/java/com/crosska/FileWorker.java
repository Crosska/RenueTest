package com.crosska;

import java.io.*;
import java.util.*;

public class FileWorker {
    private static long duration;

    public static List<String> readSearchQuery(String searchFilePath) {
        List<String> searches = new ArrayList<>();
        try {
            File file = new File(searchFilePath);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String search = reader.readLine();
            while (search != null) {
                if(!search.equals("") && !search.equals("\n")) searches.add(search);
                search = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка. Указанный в параметрах файл c поисковыми запросами был не найден.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return searches;
    }

    public static Map<Integer, String> readFile(int column, String dataFile, String search) {
        // Map хранит в себе номер строки в файле как ключ и значение столбца по которому будет делаться сортировка
        Map<Integer, String> sortedMap = new HashMap<>();
        long startTime;
        try (FileReader fr = new FileReader(dataFile)) {
            BufferedReader input = new BufferedReader(fr);
            startTime = System.currentTimeMillis();
            String line;
            int lineNum = 1;
            while ((line = input.readLine()) != null && line.length() != 0) {
                String[] s = line.split(",");
                for (int i = 0; i < s.length; i++) {
                    if (s[i].equals("\"\"")) {
                        s[i] = "";
                    } else if (s[i].charAt(0) == '"') {
                        if (s[i].split("\"").length > 1) {
                            s[i] = s[i].split("\"")[1];
                        }
                    }
                }
                if (s[column].startsWith(search)) {
                    sortedMap.put(lineNum, s[column]);
                }
                lineNum++;
            }
            sortedMap = sortByValues(sortedMap);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка. Файл с данными об аэропортах не найден");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        return sortedMap;
    }

    private static <K, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
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

    public static long getSearchDuration() {
        System.out.println("Метод поиска длился: " + duration + " мс. (" + duration / 1000 + " с.)");
        return duration;
    }

}
