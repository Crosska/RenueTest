package com.crosska;

public class ResultElem {

    private final String search;
    private final Integer[] result;
    private final long time;

    public ResultElem(String search, Integer[] result, long time) {
        this.search = search;
        this.result = result;
        this.time = time;
    }

    public String getSearch() {
        return search;
    }

    public Integer[] getResult() {
        return result;
    }

    public long getTime() {
        return time;
    }

}
