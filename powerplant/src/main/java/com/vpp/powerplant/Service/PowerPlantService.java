package com.vpp.powerplant.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vpp.powerplant.Exception.NoRequestFoundException;
import com.vpp.powerplant.Repo.PowerPlantRepo;
import com.vpp.powerplant.entity.Battery;
import com.vpp.powerplant.entity.BatteryRequestBody;
import com.vpp.powerplant.entity.BatteryResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PowerPlantService {
	
	@Autowired
	private PowerPlantRepo plantRepo;
	
	@Async
	@CircuitBreaker(name="batteryService", fallbackMethod = "fallBackSaveBatteries")
	public void saveBatteries(List<BatteryRequestBody> request){
		if(request == null) {
			throw new NoRequestFoundException("No Request to save battery details");
		}
		List<Battery> batteries = request.stream()
				.map(r -> new Battery(null,r.getName(),r.getPostCode(),r.getWattCapacity()))
				.collect(Collectors.toList());
		log.info("Saved the battery details");
		plantRepo.saveAll(batteries);
		
	}
	
	public void fallBackSaveBatteries(List<BatteryRequestBody> request , Throwable ex) {
		System.out.println("ERROR: Couldnot save batteries " + ex.getMessage());
	}
	
	public BatteryResponse getBatteriesByPostCodeRange(String start, String end) {
		List<Battery> batteries = plantRepo.findByPostalCodeBetween(start, end);
		
		List<String> sortedNames = batteries.stream()
				.map(Battery::getName)
				.sorted()
				.collect(Collectors.toList());
		int total = batteries.stream()
				.mapToInt(Battery::getWattCapacity)
				.sum();
		
		double average = batteries.isEmpty() ? 0.0:
			batteries.stream()
			.mapToInt(Battery::getWattCapacity)
			.average()
			.orElse(0.0);
		
		return new BatteryResponse(sortedNames,total,average);
	}
	
	
 
}
