package com.crosska;

import java.io.*;
import java.util.*;

public class FileWorker {

    // Map хранит в себе строку из файла как ключ и значение столбца по которому будет делаться сортировка
    private Map<String, String> sortedMap = new HashMap<>();
    private long duration;

    public Map<String, String> ReadFile(int column) {
        Scanner scanner = new Scanner(System.in);
        String search = "";
        long startTime = 0;
        try (InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream("/airports.csv")))) {
            BufferedReader input = new BufferedReader(inputStreamReader);
            System.out.print("Мы ищем: ");
            search = scanner.nextLine();
            startTime = System.currentTimeMillis();
            String line;
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
                    sortedMap.put(line, s[column]);
                }
            }
            sortedMap = sortByValues(sortedMap);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка, файл не найден");
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        return sortedMap;
    }

    private <K, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        // создаем список записей карты и сортируем их по значениям
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

    public void getSearchDuration() {
        System.out.println("Метод поиска длился: " + duration + " мс. (" + duration / 1000 + " с.)");
    }

    public void preSortFile() {

    }

}
