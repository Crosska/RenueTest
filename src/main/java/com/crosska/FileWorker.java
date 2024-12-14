package com.crosska;

import java.io.*;
import java.util.*;

public class FileWorker {
    private static final Map<Integer, String> columnMap = new HashMap<>();

    public static List<String> readSearchQuery(String searchFilePath) {
        List<String> searches = new ArrayList<>();
        try {
            File file = new File(searchFilePath);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String search = reader.readLine();
            while (search != null) {
                if (!search.equals("") && !search.equals("\n")) searches.add(search);
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

    public static Map<Integer, String> readFile(int column, String dataFile) {
        // Map хранит в себе номер строки в файле как ключ и значение столбца по которому будет делаться сортировка
        try (Scanner scanner = new Scanner(new File(dataFile));) {
            String line;
            int lineNum = 1;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(!line.isEmpty()) { // Пропускаем если строка пуста
                    String[] s = line.split(","); // Делим столбцы, используя ',' как разделитель
                    for (int i = 0; i < s.length; i++) {
                        s[i] = s[i].strip(); // Убираем пробелы до кавычек
                        if (s[i].equals("\"\"")) { // Если значение в столбце это две кавычки, то меняем на пустоту
                            s[i] = "";
                        } else if (s[i].charAt(0) == '"') {
                            if (s[i].split("\"").length > 1) {
                                s[i] = s[i].split("\"")[1]; // Убираем кавычки в конце и в начале
                                s[i] = s[i].strip(); // Убираем пробелы после кавычек
                            }
                        }
                    }
                    columnMap.put(lineNum, s[column]);
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка. Файл с данными об аэропортах не найден");
            throw new RuntimeException(e);
        }
        return columnMap;
    }

}
