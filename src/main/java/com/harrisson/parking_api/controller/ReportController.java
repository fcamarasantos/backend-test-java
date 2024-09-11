package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.model.Report;
import com.harrisson.parking_api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{establishmentId}")
    public ResponseEntity<Report> getReport(@PathVariable Long establishmentId) {
        Report report = reportService.generateReport(establishmentId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{establishmentId}/hourly")
    public ResponseEntity<Map<Integer, Long>> getVehicleCountByHour(
            @PathVariable Long establishmentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<Integer, Long> report = reportService.getVehicleCountByHour(establishmentId, startTime, endTime);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{establishmentId}/daily")
    public ResponseEntity<Map<LocalDateTime, Long>> getVehicleCountByDay(@PathVariable Long establishmentId) {
        Map<LocalDateTime, Long> report = reportService.getVehicleCountByDay(establishmentId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{establishmentId}/type")
    public ResponseEntity<Map<String, Long>> getVehicleCountByType(@PathVariable Long establishmentId) {
        Map<String, Long> report = reportService.getVehicleCountByType(establishmentId);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{establishmentId}/type-hourly")
    public ResponseEntity<Map<String, Map<Integer, Long>>> getVehicleCountByTypeAndHour(
            @PathVariable Long establishmentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<String, Map<Integer, Long>> report = reportService.getVehicleCountByTypeAndHour(establishmentId, startTime, endTime);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{establishmentId}/entries")
    public ResponseEntity<Integer> getEntryCount(@PathVariable Long establishmentId) {
        int count = reportService.getEntryCount(establishmentId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{establishmentId}/exits")
    public ResponseEntity<Integer> getExitCount(@PathVariable Long establishmentId) {
        int count = reportService.getExitCount(establishmentId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{establishmentId}/entries/hourly")
    public ResponseEntity<Map<Integer, Long>> getEntryCountByHour(
            @PathVariable Long establishmentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<Integer, Long> report = reportService.getEntryCountByHour(establishmentId, startTime, endTime);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{establishmentId}/exits/hourly")
    public ResponseEntity<Map<Integer, Long>> getExitCountByHour(
            @PathVariable Long establishmentId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<Integer, Long> report = reportService.getExitCountByHour(establishmentId, startTime, endTime);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{establishmentId}/monthly")
    public ResponseEntity<Map<Integer, Long>> getVehicleCountByMonth(
            @PathVariable Long establishmentId,
            @RequestParam int year) {
        Map<Integer, Long> report = reportService.getVehicleCountByMonth(establishmentId, year);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/{establishmentId}/yearly")
    public ResponseEntity<Map<Integer, Long>> getVehicleCountByYear(@PathVariable Long establishmentId) {
        Map<Integer, Long> report = reportService.getVehicleCountByYear(establishmentId);
        return ResponseEntity.ok(report);
    }

}