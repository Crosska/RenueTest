package com.crosska;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JSONObject {

    private final long initTime;

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final ArrayList<ResultElem> result;

    @JsonIgnore
    private final String resultFilePath;

    @JsonIgnore
    public JSONObject(long initTime, String resultFilePath) {
        this.initTime = initTime;
        this.resultFilePath = resultFilePath;
        result = new ArrayList<>();
    }

    @JsonIgnore
    public String getOutputPath() {
        return resultFilePath;
    }

    @JsonIgnore
    public void addNewResult(ResultElem elem) {
        result.add(elem);
    }

}
