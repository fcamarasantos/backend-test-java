package com.harrisson.parking_api.service;

import com.harrisson.parking_api.enums.Type;
import com.harrisson.parking_api.model.Report;
import com.harrisson.parking_api.repository.AccessControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private AccessControlRepository accessControlRepository;

    public Report generateReport(Long establishmentId) {
        String establishmentName = accessControlRepository.findEstablishmentNameById(establishmentId);

        int totalMotorcycles;
        int totalCars;
        int totalVehicles;
        try {
            totalMotorcycles = accessControlRepository.countByEstablishmentIdAndVehicleType(establishmentId, Type.MOTORCYCLE.toString());
            totalCars = accessControlRepository.countByEstablishmentIdAndVehicleType(establishmentId, Type.CAR.toString());
            totalVehicles = totalMotorcycles + totalCars;
        } catch (Exception e) {
            totalMotorcycles = 0;
            totalCars = 0;
            totalVehicles = 0;
        }
        return new Report(establishmentName, totalVehicles, totalMotorcycles, totalCars, LocalDateTime.now());
    }

    public Map<Integer, Long> getVehicleCountByHour(Long establishmentId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Object[]> results = accessControlRepository.countVehiclesByHour(establishmentId, startTime, endTime);
        return results.stream().collect(Collectors.toMap(
                result -> (Integer) result[0],
                result -> (Long) result[1],
                (oldValue, newValue) -> oldValue,
                () -> new java.util.HashMap<>(Map.of(0, 0L))
        ));
    }

    public Map<LocalDateTime, Long> getVehicleCountByDay(Long establishmentId) {
        List<Object[]> results = accessControlRepository.countVehiclesByDay(establishmentId);
        return results.stream().collect(Collectors.toMap(
                result -> (LocalDateTime) result[0],
                result -> (Long) result[1],
                (oldValue, newValue) -> oldValue,
                () -> new java.util.HashMap<>(Map.of(LocalDateTime.MIN, 0L))
        ));
    }

    public Map<String, Long> getVehicleCountByType(Long establishmentId) {
        List<Object[]> results = accessControlRepository.countVehiclesByType(establishmentId);
        return results.stream().collect(Collectors.toMap(
                result -> (String) result[0],
                result -> (Long) result[1],
                (oldValue, newValue) -> oldValue,
                () -> new java.util.HashMap<>(Map.of("", 0L))
        ));
    }

    public Map<String, Map<Integer, Long>> getVehicleCountByTypeAndHour(Long establishmentId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Object[]> results = accessControlRepository.countVehiclesByTypeAndHour(establishmentId, startTime, endTime);
        return results.stream().collect(Collectors.groupingBy(
                result -> (String) result[1],
                Collectors.toMap(
                        result -> (Integer) result[0],
                        result -> (Long) result[2],
                        (oldValue, newValue) -> oldValue,
                        () -> new java.util.HashMap<>(Map.of(0, 0L))
                )
        ));
    }

    public int getEntryCount(Long establishmentId) {
        return accessControlRepository.countEntriesByEstablishmentId(establishmentId);
    }

    public int getExitCount(Long establishmentId) {
        return accessControlRepository.countExitsByEstablishmentId(establishmentId);
    }

    public Map<Integer, Long> getEntryCountByHour(Long establishmentId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Object[]> results = accessControlRepository.countEntriesByHour(establishmentId, startTime, endTime);
        return results.stream().collect(Collectors.toMap(
                result -> (Integer) result[0],
                result -> (Long) result[1],
                (oldValue, newValue) -> oldValue,
                () -> new java.util.HashMap<>(Map.of(0, 0L))
        ));
    }

    public Map<Integer, Long> getExitCountByHour(Long establishmentId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Object[]> results = accessControlRepository.countExitsByHour(establishmentId, startTime, endTime);
        return results.stream().collect(Collectors.toMap(
                result -> (Integer) result[0],
                result -> (Long) result[1],
                (oldValue, newValue) -> oldValue,
                () -> new java.util.HashMap<>(Map.of(0, 0L))
        ));
    }

    public Map<Integer, Long> getVehicleCountByMonth(Long establishmentId, int year) {
        List<Object[]> results = accessControlRepository.countVehiclesByMonth(establishmentId, year);
        return results.stream().collect(Collectors.toMap(
                result -> (Integer) result[0],
                result -> (Long) result[1],
                (oldValue, newValue) -> oldValue,
                () -> new java.util.HashMap<>(Map.of(0, 0L))
        ));
    }

    public Map<Integer, Long> getVehicleCountByYear(Long establishmentId) {
        List<Object[]> results = accessControlRepository.countVehiclesByYear(establishmentId);
        return results.stream().collect(Collectors.toMap(
                result -> (Integer) result[0],
                result -> (Long) result[1],
                (oldValue, newValue) -> oldValue,
                () -> new java.util.HashMap<>(Map.of(0, 0L))
        ));
    }
}