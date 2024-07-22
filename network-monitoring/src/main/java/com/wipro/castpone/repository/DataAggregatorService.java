package com.wipro.castpone.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class DataAggregatorService {
    private final Map<String, Double> deviceMetrics = new HashMap<>();

    public void aggregateData(String deviceId, double metricValue) {
        deviceMetrics.put(deviceId, metricValue);
    }

    public Map<String, Double> getAggregatedData() {
        return new HashMap<>(deviceMetrics);
    }
}
