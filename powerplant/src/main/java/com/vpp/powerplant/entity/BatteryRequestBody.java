package com.vpp.powerplant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryRequestBody {
	private String name;
	private String postCode;
	private int wattCapacity; 

}
