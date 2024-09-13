package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.model.Establishment;
import com.harrisson.parking_api.service.EstablishmentService;
import com.harrisson.parking_api.to.EstablishmentData;
import com.harrisson.parking_api.to.EstablishmentDataDetails;
import com.harrisson.parking_api.to.EstablishmentList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstablishmentController.class)
public class EstablishmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstablishmentService establishmentService;

    @Test
    public void testCreateEstablishment() throws Exception {
        Long id = 15L;
        EstablishmentData establishmentData = new EstablishmentData("Test Establishment", "123456789", null, "1234567890", 10, 20);
        EstablishmentDataDetails establishmentDataDetails = new EstablishmentDataDetails(id +1, "Test Establishment", "123456789", null, "1234567890", 10, 20, true);
        Establishment establishment = establishmentDataDetails.toEntity();
        Mockito.when(establishmentService.save(Mockito.any(EstablishmentData.class))).thenReturn(establishment);

        mockMvc.perform(post("/establishments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Establishment\", \"cnpj\":\"123456789\", \"phone\":\"1234567890\", \"motorcycleQuantity\":10, \"carQuantity\":20}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/establishments/" + (id+1)))
                .andExpect(jsonPath("$.name").value("Test Establishment"));
    }

    @Test
    public void testGetEstablishments() throws Exception {
        EstablishmentList establishmentList = new EstablishmentList();
        Page<EstablishmentList> page = new PageImpl<>(Collections.singletonList(establishmentList));
        Mockito.when(establishmentService.getEstablishments(Mockito.any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/establishments/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void testGetEstablishmentById() throws Exception {
        EstablishmentData establishmentData = new EstablishmentData("Test Establishment", "123456789", null, "1234567890", 10, 20);
        Mockito.when(establishmentService.getById(1L)).thenReturn(establishmentData.toEntity());

        mockMvc.perform(get("/establishments/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Establishment"));
    }

    @Test
    public void testUpdateEstablishment() throws Exception {
        EstablishmentDataDetails establishmentDataDetails = new EstablishmentDataDetails(1L, "Updated Establishment", "123456789", null, "1234567890", 10, 20, true);
        Establishment establishment = establishmentDataDetails.toEntity();
        Mockito.when(establishmentService.getById(1L)).thenReturn(establishment);
        Mockito.when(establishmentService.update(Mockito.any(EstablishmentDataDetails.class))).thenReturn(establishment);

        mockMvc.perform(put("/establishments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Establishment\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Establishment"));
    }

    @Test
    public void testDeleteEstablishment() throws Exception {
        mockMvc.perform(delete("/establishments/1"))
                .andExpect(status().isNoContent());
    }
}