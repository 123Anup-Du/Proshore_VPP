package com.vpp.powerplant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vpp.powerplant.Service.PowerPlantService;
import com.vpp.powerplant.entity.BatteryRequestBody;
import com.vpp.powerplant.entity.BatteryResponse;

@RestController
@RequestMapping("/batteries")
public class PowerController {
	
	@Autowired
	private PowerPlantService plantService;
	
	@PostMapping
	public ResponseEntity<String> saveBattery(@RequestBody List<BatteryRequestBody> requests){
		plantService.saveBatteries(requests);
		return ResponseEntity.ok("Saved");
	}
	
	@GetMapping
	public ResponseEntity<BatteryResponse> getBatteryDetails(
			@RequestParam("from") String from,
			@RequestParam("to") String to
			) {
		BatteryResponse response = plantService.getBatteriesByPostCodeRange(from, to);
		return ResponseEntity.ok(response);
	}

}
