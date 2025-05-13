package com.vpp.powerplant.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryResponse {
	private List<String> name;
	private int totalWattCapacity;
	private double averageWattCapacity;

}
