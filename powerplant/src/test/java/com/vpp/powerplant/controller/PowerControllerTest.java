package com.vpp.powerplant.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpp.powerplant.Service.PowerPlantService;
import com.vpp.powerplant.entity.Battery;
import com.vpp.powerplant.entity.BatteryRequestBody;
import com.vpp.powerplant.entity.BatteryResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PowerController.class)
class PowerControllerTest {
	
   @Autowired
   private MockMvc mockMvc;
   
   @MockBean
   private PowerPlantService plantService;
   
   @Test
    void testSaveAllBatteries_returnsOk() throws Exception {
	   List<BatteryRequestBody> batteryRequestList = List.of(
			   new BatteryRequestBody("Battery A", "12345", 500),
			   new BatteryRequestBody("Battery B", "12350", 700));
	   ObjectMapper mapper = new ObjectMapper();
	   String json = mapper.writeValueAsString(batteryRequestList);
       mockMvc.perform(post("/batteries")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk());
   }
   @Test
   void testGetBatteriesByPostcodeRange_returnsStats() throws Exception {
      BatteryResponse mockResponse = new BatteryResponse();
      mockResponse.setName(List.of("A","B"));
      mockResponse.setAverageWattCapacity(125.0);
      mockResponse.setTotalWattCapacity(250);
       Mockito.when(plantService.getBatteriesByPostCodeRange("1000", "2000"))
               .thenReturn(mockResponse);
       mockMvc.perform(get("/batteries")
               .param("from", "1000")
               .param("to", "2000"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").isArray())
               .andExpect(jsonPath("$.totalWattCapacity").value(250))
               .andExpect(jsonPath("$.averageWattCapacity").value(125.0));
   }
}
