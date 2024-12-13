package com.crosska;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class JSONWriter {

    public void writeResult(JSONObject jsonObject) {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(writer, jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter filewriter = new FileWriter(jsonObject.getOutputPath(), false)) {
            filewriter.write(writer.toString());
            System.out.println(writer);
            filewriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
