package com.crosska;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JSONObject {

    private long initTime;

    private ArrayList<ResultElem> result;

    private String resultFilePath;

    public JSONObject(long initTime, String resultFilePath) {
        this.initTime = initTime;
        this.resultFilePath = resultFilePath;
        result = new ArrayList<>();
    }

    public void addNewResult(ResultElem elem) {
        result.add(elem);
    }

}
