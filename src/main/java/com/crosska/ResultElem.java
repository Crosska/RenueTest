package com.crosska;

public class ResultElem {

    private final String search;
    private final int[] result;
    private final long time;

    public ResultElem(String search, int[] result, long time) {
        this.search = search;
        this.result = result;
        this.time = time;
    }

    public String getSearch() {
        return search;
    }

    public int[] getResult() {
        return result;
    }

    public long getTime() {
        return time;
    }

}
