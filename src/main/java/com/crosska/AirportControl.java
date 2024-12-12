package com.crosska;

import java.util.ArrayList;
import java.util.Map;

public class AirportControl {

    public void find(int column) {
        FileWorker fileWorker = new FileWorker();
        Map<String, String> sortedMap = fileWorker.ReadFile(column);

        for (String line :sortedMap.keySet()) {
            System.out.println(line);
        }

        System.out.println("Найдено строк в файле = " + sortedMap.size());
        fileWorker.getSearchDuration();

    }

}
