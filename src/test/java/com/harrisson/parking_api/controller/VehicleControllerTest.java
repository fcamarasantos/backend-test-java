package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.enums.Type;
import com.harrisson.parking_api.model.Vehicle;
import com.harrisson.parking_api.service.VehicleService;
import com.harrisson.parking_api.to.VehicleData;
import com.harrisson.parking_api.to.VehicleDataDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    private Vehicle vehicle;
    private VehicleData vehicleData;
    private VehicleDataDetails vehicleDataDetails;

    @BeforeEach
    public void setup() {
        vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setPlate("ABC-1234");
        vehicle.setModel("Model X");
        vehicle.setBrand("Tesla");
        vehicle.setType(Type.CAR);

        vehicleData = new VehicleData(vehicle);
        vehicleDataDetails = new VehicleDataDetails(vehicle);
    }

    @Test
    public void testCreateVehicle() throws Exception {
        doAnswer(invocation -> {
            Vehicle vehicle = invocation.getArgument(0);
            vehicle.setId(1L); // Set the ID to simulate the save operation
            return vehicle;
        }).when(vehicleService).save(any(Vehicle.class));

        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"plate\":\"ABC-1234\",\"model\":\"Model X\",\"brand\":\"Tesla\",\"type\":\"CAR\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/vehicles/getById/1"))
                .andExpect(jsonPath("$.plate").value("ABC-1234"));
    }

    @Test
    public void testGetVehicles() throws Exception {
        when(vehicleService.getVehicles(any(Pageable.class))).thenReturn(Page.empty());

        mockMvc.perform(get("/vehicles/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetVehicleById() throws Exception {
        when(vehicleService.getById(anyLong())).thenReturn(vehicle);

        mockMvc.perform(get("/vehicles/getById/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.plate").value("ABC-1234"));
    }

    @Test
    public void testUpdateVehicle() throws Exception {
        when(vehicleService.getById(anyLong())).thenReturn(vehicle);
        doNothing().when(vehicleService).save(any(Vehicle.class));

        mockMvc.perform(put("/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"plate\":\"ABC-1234\",\"model\":\"Model X\",\"manufacturer\":\"Tesla\",\"year\":2020}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plate").value("ABC-1234"));
    }

    @Test
    public void testDeleteVehicle() throws Exception {
        doNothing().when(vehicleService).deleteVehicle(anyLong());

        mockMvc.perform(delete("/vehicles/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testSearchByPlate() throws Exception {
        when(vehicleService.getByPlate(any(String.class))).thenReturn(vehicle);

        mockMvc.perform(get("/vehicles/searchByPlate")
                        .param("plate", "ABC-1234"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.plate").value("ABC-1234"));
    }
}