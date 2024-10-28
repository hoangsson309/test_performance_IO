package org.example;

import org.example.service.InitData;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        List<Map<String, Object>> dataList = new InitData().getLargeData();

        String outputFile = "large-data2.csv";
        int bufferSize = 8192;

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile), bufferSize)) {

            String header = "Name,Age,City";
            bos.write((header + "\n").getBytes());

            for (Map<String, Object> row : dataList) {
                StringBuilder line = new StringBuilder();
                line.append(row.get("Name")).append(",")
                        .append(row.get("Age")).append(",")
                        .append(row.get("City"));

                bos.write((line.toString() + "\n").getBytes());
            }
            bos.flush();
            System.out.println("End process!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
