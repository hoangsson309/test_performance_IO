package org.example.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InitData {
    public List<Map<String, Object>> getLargeData() {
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Name", "User" + i);
            row.put("Age", 20 + (i % 50));
            row.put("City", "City" + (i));
            data.add(row);
        }
        return data;
    }
}
