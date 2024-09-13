package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.model.Report;
import com.harrisson.parking_api.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Tag(name = "Reports", description = "Reports API")
@RestController
@RequestMapping("/reports")
@SecurityRequirement(name = "bearer-key")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Operation(summary = "Generate report", description = "Generate report")
    @GetMapping("/{establishmentId}")
    public ResponseEntity<Report> getReport(@PathVariable Long establishmentId) {
        Report report = reportService.generateReport(establishmentId);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Generate report by date", description = "Generate report by date")
    @GetMapping("/{establishmentId}/hourly")
    public ResponseEntity<Map<Integer, Long>> getVehicleCountByHour(
            @PathVariable Long establishmentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<Integer, Long> report = reportService.getVehicleCountByHour(establishmentId, startTime, endTime);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Generate report by day", description = "Generate report by day")
    @GetMapping("/{establishmentId}/daily")
    public ResponseEntity<Map<LocalDateTime, Long>> getVehicleCountByDay(@PathVariable Long establishmentId) {
        Map<LocalDateTime, Long> report = reportService.getVehicleCountByDay(establishmentId);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Generate report by type", description = "Generate report by type")
    @GetMapping("/{establishmentId}/type")
    public ResponseEntity<Map<String, Long>> getVehicleCountByType(@PathVariable Long establishmentId) {
        Map<String, Long> report = reportService.getVehicleCountByType(establishmentId);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Generate report by type and hour", description = "Generate report by type and hour")
    @GetMapping("/{establishmentId}/type-hourly")
    public ResponseEntity<Map<String, Map<Integer, Long>>> getVehicleCountByTypeAndHour(
            @PathVariable Long establishmentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<String, Map<Integer, Long>> report = reportService.getVehicleCountByTypeAndHour(establishmentId, startTime, endTime);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Generate report by type and day", description = "Generate report by type and day")
    @GetMapping("/{establishmentId}/entries")
    public ResponseEntity<Integer> getEntryCount(@PathVariable Long establishmentId) {
        int count = reportService.getEntryCount(establishmentId);
        return ResponseEntity.ok(count);
    }

    @Operation(summary = "Generate exits by type and day", description = "Generate exits by type and day")
    @GetMapping("/{establishmentId}/exits")
    public ResponseEntity<Integer> getExitCount(@PathVariable Long establishmentId) {
        int count = reportService.getExitCount(establishmentId);
        return ResponseEntity.ok(count);
    }

    @Operation(summary = "Generate report by entry hour", description = "Generate report by entry hour")
    @GetMapping("/{establishmentId}/entries/hourly")
    public ResponseEntity<Map<Integer, Long>> getEntryCountByHour(
            @PathVariable Long establishmentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<Integer, Long> report = reportService.getEntryCountByHour(establishmentId, startTime, endTime);
        return ResponseEntity.ok(report);
    }

@Operation(summary = "Generate report by exist entry hour", description = "Generate report by exist entry hour")
    @GetMapping("/{establishmentId}/exits/hourly")
    public ResponseEntity<Map<Integer, Long>> getExitCountByHour(
            @PathVariable Long establishmentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<Integer, Long> report = reportService.getExitCountByHour(establishmentId, startTime, endTime);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Generate Vehicle  report by month", description = "Generate Vehicle report by month")
    @GetMapping("/{establishmentId}/monthly")
    public ResponseEntity<Map<Integer, Long>> getVehicleCountByMonth(
            @PathVariable Long establishmentId,
            @RequestParam int year) {
        Map<Integer, Long> report = reportService.getVehicleCountByMonth(establishmentId, year);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Generate Vehicle  report by year", description = "Generate Vehicle report by year")
    @GetMapping("/{establishmentId}/yearly")
    public ResponseEntity<Map<Integer, Long>> getVehicleCountByYear(@PathVariable Long establishmentId) {
        Map<Integer, Long> report = reportService.getVehicleCountByYear(establishmentId);
        return ResponseEntity.ok(report);
    }
}