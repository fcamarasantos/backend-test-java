package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.model.Report;
import com.harrisson.parking_api.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    public void testGetVehicleCountByHour() throws Exception {
        Map<Integer, Long> report = new HashMap<>();
        report.put(1, 10L);
        when(reportService.getVehicleCountByHour(eq(1L), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(report);

        mockMvc.perform(get("/reports/1/hourly")
                .param("startTime", "2023-01-01T00:00:00")
                .param("endTime", "2023-01-01T23:59:59"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.1").value(10L));
    }

    @Test
    public void testGetVehicleCountByDay() throws Exception {
        Map<LocalDateTime, Long> report = new HashMap<>();
        report.put(LocalDateTime.of(2023, 1, 1, 0, 0), 10L);
        when(reportService.getVehicleCountByDay(1L)).thenReturn(report);

        mockMvc.perform(get("/reports/1/daily"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['2023-01-01T00:00:00']").value(10L));
    }

    @Test
    public void testGetVehicleCountByType() throws Exception {
        Map<String, Long> report = new HashMap<>();
        report.put("Car", 10L);
        when(reportService.getVehicleCountByType(1L)).thenReturn(report);

        mockMvc.perform(get("/reports/1/type"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.Car").value(10L));
    }

    @Test
    public void testGetVehicleCountByTypeAndHour() throws Exception {
        Map<String, Map<Integer, Long>> report = new HashMap<>();
        Map<Integer, Long> hourlyReport = new HashMap<>();
        hourlyReport.put(1, 10L);
        report.put("Car", hourlyReport);
        when(reportService.getVehicleCountByTypeAndHour(eq(1L), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(report);

        mockMvc.perform(get("/reports/1/type-hourly")
                .param("startTime", "2023-01-01T00:00:00")
                .param("endTime", "2023-01-01T23:59:59"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.Car.1").value(10L));
    }

    @Test
    public void testGetEntryCount() throws Exception {
        when(reportService.getEntryCount(1L)).thenReturn(10);

        mockMvc.perform(get("/reports/1/entries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(10));
    }

    @Test
    public void testGetExitCount() throws Exception {
        when(reportService.getExitCount(1L)).thenReturn(10);

        mockMvc.perform(get("/reports/1/exits"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(10));
    }

    @Test
    public void testGetEntryCountByHour() throws Exception {
        Map<Integer, Long> report = new HashMap<>();
        report.put(1, 10L);
        when(reportService.getEntryCountByHour(eq(1L), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(report);

        mockMvc.perform(get("/reports/1/entries/hourly")
                .param("startTime", "2023-01-01T00:00:00")
                .param("endTime", "2023-01-01T23:59:59"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.1").value(10L));
    }

    @Test
    public void testGetExitCountByHour() throws Exception {
        Map<Integer, Long> report = new HashMap<>();
        report.put(1, 10L);
        when(reportService.getExitCountByHour(eq(1L), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(report);

        mockMvc.perform(get("/reports/1/exits/hourly")
                .param("startTime", "2023-01-01T00:00:00")
                .param("endTime", "2023-01-01T23:59:59"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.1").value(10L));
    }

    @Test
    public void testGetVehicleCountByMonth() throws Exception {
        Map<Integer, Long> report = new HashMap<>();
        report.put(1, 10L);
        when(reportService.getVehicleCountByMonth(1L, 2023)).thenReturn(report);

        mockMvc.perform(get("/reports/1/monthly")
                .param("year", "2023"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.1").value(10L));
    }

    @Test
    public void testGetVehicleCountByYear() throws Exception {
        Map<Integer, Long> report = new HashMap<>();
        report.put(2023, 10L);
        when(reportService.getVehicleCountByYear(1L)).thenReturn(report);

        mockMvc.perform(get("/reports/1/yearly"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.2023").value(10L));
    }
}