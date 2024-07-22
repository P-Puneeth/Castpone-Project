package com.wipro.castpone.service;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.DoubleCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.common.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.castpone.repository.DataAggregatorService;


@Service
public class TelemetryService {
    private final Meter meter;
    private final DoubleCounter cpuUsageCounter;
    
    @Autowired
    private DataAggregatorService dataAggregator;


    public TelemetryService() {
        this.meter = GlobalOpenTelemetry.get().meterBuilder("example").build();
        this.cpuUsageCounter = meter
            .counterBuilder("cpu_usage")
            .setDescription("CPU Usage")
            .setUnit("percent")
            .ofDoubles()
            .build();
    }

    public double collectTelemetryData(String deviceId) {
        // Simulate collecting CPU usage data
        double cpuUsage = getCpuUsage();
        cpuUsageCounter.add(cpuUsage, Attributes.builder().put("device", deviceId).build());
        dataAggregator.aggregateData(deviceId, cpuUsage);
        return cpuUsage;
    }

    private double getCpuUsage() {
        return Math.random() * 100;
    }

}
