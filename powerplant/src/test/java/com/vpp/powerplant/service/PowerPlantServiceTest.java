package com.vpp.powerplant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vpp.powerplant.Exception.NoRequestFoundException;
import com.vpp.powerplant.Repo.PowerPlantRepo;
import com.vpp.powerplant.Service.PowerPlantService;
import com.vpp.powerplant.entity.Battery;
import com.vpp.powerplant.entity.BatteryRequestBody;
import com.vpp.powerplant.entity.BatteryResponse;

@ExtendWith(MockitoExtension.class)
public class PowerPlantServiceTest {

	@Mock
	private PowerPlantRepo plantRepo;

	@InjectMocks
	private PowerPlantService plantService;

	@Test
	void testSaveBattery_Successfull() {
		List<BatteryRequestBody> batteries = Arrays.asList(new BatteryRequestBody("A", "2000", 100),
				new BatteryRequestBody("B", "2001", 200));

		List<Battery> entity = Arrays.asList(new Battery(null, "A", "2000", 100), new Battery(null, "B", "2001", 200));
		when(plantRepo.saveAll(anyList())).thenReturn(entity);

		plantService.saveBatteries(batteries);

		verify(plantRepo, times(1)).saveAll(anyList());
	}

	@Test
	void testSaveBattery_failure() {
		assertThrows(NoRequestFoundException.class, () -> plantService.saveBatteries(null));
	}

	@Test
	void testgetPostCode() {
		List<Battery> entity = Arrays.asList(new Battery(null, "A", "2000", 100), new Battery(null, "B", "2001", 200));
		when(plantRepo.findByPostalCodeBetween("2000", "2001")).thenReturn(entity);

		BatteryResponse batteryResponse = plantService.getBatteriesByPostCodeRange("2000", "2001");

		assertEquals(Arrays.asList("A", "B"), batteryResponse.getName());

	}

}
