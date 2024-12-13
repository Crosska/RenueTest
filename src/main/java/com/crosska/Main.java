package com.crosska;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Options options = new Options();
        options.addOption("d", "data", true, "Path to the .csv text data file (including filename and extension)");
        options.addOption("c", "indexed-column-id", true, "Optional parameter which means search column number");
        options.addOption("i", "input-file", true, "Path to the text file with search lines (including filename and extension)");
        options.addOption("o", "output-file", true, "Path to the JSON file with results data (including filename and extension)");
        options.addOption("Xmx7m", "Xmx7m", false, "Skip-able parameter by parser");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("data") && cmd.hasOption("input-file") && cmd.hasOption("output-file")) {
                int columnId;
                if (cmd.hasOption("indexed-column-id")) {
                    columnId = Integer.parseInt(cmd.getOptionValue("indexed-column-id"));
                    if(columnId >= 0 && columnId <= 13) {
                        proceedSearch(columnId, cmd.getOptionValue("input-file"), cmd.getOptionValue("data"), cmd.getOptionValue("output-file"), startTime);
                    } else {
                        System.err.println("Ошибка. Параметр indexed-column-id (c) должен быть в пределах 0 <= c >= 13");
                    }
                } else {
                    Parameters parameters = new Parameters();
                    Map<String, Object> map = parameters.getParameters();
                    columnId = (Integer) map.get("defaultColumn");
                    proceedSearch(columnId, cmd.getOptionValue("input-file"), cmd.getOptionValue("data"), cmd.getOptionValue("output-file"), startTime);

                }
            } else {
                System.err.println("Ошибка. Необходимые параметры для запуска:\ndata (d) $путь\ninput-file (i) $путь\noutput-file (o) $путь");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("airports-search.jar", options);
        }

    }

    private static void proceedSearch(int columnId, String inputFile, String dataFile, String outputFile, long startTime) {
        List<String> list = FileWorker.readSearchQuery(inputFile);

        List<ResultElem> resArray = new ArrayList<>();
        long initTime = System.currentTimeMillis() - startTime;
        for (String search : list) {
            System.out.println("\n -- Поиск по запросу \"" + search + "\"\n");
            Map<Integer, String> sortedMap = FileWorker.readFile(columnId, dataFile, search);
            for (Integer lineNum :sortedMap.keySet()) {
                System.out.println(lineNum);
            }
            ResultElem result = new ResultElem(search, sortedMap.keySet().toArray(Integer[]::new), FileWorker.getSearchDuration());
            resArray.add(result);
        }
        JSONObject jsonObject = new JSONObject(initTime, outputFile);

        for(ResultElem resultElem : resArray) {
            jsonObject.addNewResult(resultElem);
        }

        JSONWriter jsonWriter = new JSONWriter();
        jsonWriter.writeResult(jsonObject);

    }

}