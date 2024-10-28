package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.example.service.InitData;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        Sheet sheet = workbook.createSheet("Data");
        Row header = sheet.createRow(0);
        List<Map<String, Object>> data = new InitData().getLargeData();
        List<String> keys = new ArrayList<>(data.get(0).keySet());
        int totalKey = keys.size();
        for (int i = 0; i < totalKey; i++) {
            header.createCell(i).setCellValue(keys.get(i));
        }
        int dataSize = data.size();
        for (int i = 0; i < dataSize; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < totalKey; j++) {
                row.createCell(j).setCellValue(data.get(i).get(keys.get(j)).toString());
            }
            if (i % 10000 == 0) {
                ((SXSSFSheet) sheet).flushRows(10000);
            }
        }
        try (FileOutputStream fileOut = new FileOutputStream("large-data.xlsx")) {
            workbook.write(fileOut);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            bos.writeTo(fileOut);
            byte[] byteArray = bos.toByteArray();
            System.out.println(byteArray.toString());
            fileOut.close();
            bos.close();
            System.out.println("Export success!");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println("Export errors");
        } finally {
            workbook.dispose();
            workbook.close();
            System.out.println("Endxport process!");
        }
    }
}