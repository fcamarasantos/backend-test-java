package com.fcamara.parking.api.controllers;

import com.fcamara.parking.api.services.ReportParkingService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ReportController {

    private final ReportParkingService reportParkingService;

    @GetMapping("/parking/{establishmentId}")
    public ResponseEntity<Object> exportParkingPdfReport(@PathVariable(value = "establishmentId") Long establishmentId,
                                                         @RequestParam(value = "dateTimeInitial", required = false) String dateTimeInitial,
                                                         @RequestParam(value = "dateTimeFinal", required = false) String dateTimeFinal) {

        try {
            InputStreamResource resource = reportParkingService.report(establishmentId, dateTimeInitial, dateTimeFinal);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"parking_report.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/parking/summary")
    public ResponseEntity<Map<String, Long>> getSummaryOfEntriesAndExits() {
        Map<String, Long> summary = reportParkingService.getSummaryOfEntriesAndExits();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/parking/summary-by-hour")
    public ResponseEntity<Map<String, Long>> getSummaryOfEntriesAndExitsByHour() {
        Map<String, Long> summaryByHour = reportParkingService.getSummaryOfEntriesAndExitsByHour();
        return ResponseEntity.ok(summaryByHour);
    }

    @GetMapping("/parking/summary/establishment/{establishmentId}")
    public ResponseEntity<Map<String, Long>> getSummaryByEstablishment(@PathVariable Long establishmentId) {
        Map<String, Long> summary = reportParkingService.getSummaryOfEntriesAndExitsByEstablishment(establishmentId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/parking/summary-by-hour/establishment/{establishmentId}")
    public ResponseEntity<Map<String, Map<String, Long>>> getSummaryByHourAndEstablishment(@PathVariable Long establishmentId) {
        Map<String, Map<String, Long>> summaryByHour = reportParkingService.getSummaryOfEntriesAndExitsByHourAndEstablishment(establishmentId);
        return ResponseEntity.ok(summaryByHour);
    }
}