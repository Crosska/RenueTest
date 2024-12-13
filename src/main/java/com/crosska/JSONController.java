package com.crosska;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JSONController {

    public void writeResult(JSONObject jsonObject) {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(writer, jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String result = writer.toString();
        System.out.println(result);
    }

}
