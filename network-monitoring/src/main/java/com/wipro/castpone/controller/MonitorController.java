package com.wipro.castpone.controller;


import com.wipro.castpone.repository.DataAggregatorService;
import com.wipro.castpone.service.SNMPService;
import com.wipro.castpone.service.TelemetryService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorController {

	@Autowired
	private TelemetryService telemetryService;

	@Autowired
	private SNMPService snmpService;
	
	@Autowired
    private DataAggregatorService dataAggregator;

	@GetMapping("/snmp")
	public String getSNMPData(@RequestParam String address, @RequestParam String community) {
		return snmpService.getSNMPData(address, community);
	}


	@GetMapping("/telemetry")
	public String getTelemetryData(@RequestParam String deviceId) {
		double cpuUsage = telemetryService.collectTelemetryData(deviceId);
		return "CPU Usage for device " + deviceId + ": " + cpuUsage;
	}

	@GetMapping("/aggregated")
	public Map<String, Double> getAggregatedTelemetryData() {
		return dataAggregator.getAggregatedData();
	}
}
