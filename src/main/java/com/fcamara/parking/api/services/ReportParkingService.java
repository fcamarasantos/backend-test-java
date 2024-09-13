package com.fcamara.parking.api.services;

import com.fcamara.parking.api.domain.entities.Parking;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.fcamara.parking.api.utils.DateUtil.convertToLocalDateTime;
import static com.fcamara.parking.api.utils.DateUtil.formatDate;

@AllArgsConstructor
@Service
public class ReportParkingService extends AbstractReportService {

    private final ParkingService parkingService;
    private final EstablishmentService establishmentService;

    public InputStreamResource report(Long establishmentId, String dateTimeInitial, String dateTimeFinal) {
        var establishment = establishmentService.getById(establishmentId);

        String title = "Relatório de Estacionamento - " + establishment.getName();
        List<String> columnHeaders = Arrays.asList("Veículo", "Placa", "Tipo", "Data/Hora Entrada", "Data/Hora Saída");
        List<Map<String, String>> rows = new ArrayList<>();

        LocalDateTime localDateTimeInitial = Objects.isNull(dateTimeInitial) ? null : convertToLocalDateTime(dateTimeInitial);
        LocalDateTime localDateTimeFinal = Objects.isNull(dateTimeFinal) ? null : convertToLocalDateTime(dateTimeFinal);
        List<Parking> parkingList = parkingService.getParkingsByEstablishment(establishment.getId()).stream()
                .filter(parking -> (localDateTimeInitial == null || parking.getEntryTime().isAfter(localDateTimeInitial) || parking.getEntryTime().isEqual(localDateTimeInitial))
                        && (localDateTimeFinal == null || parking.getEntryTime().isBefore(localDateTimeFinal) || parking.getEntryTime().isEqual(localDateTimeFinal)))
                .toList();

        for (Parking parking : parkingList) {
            Map<String, String> row = new HashMap<>();
            row.put("Veículo", parking.getVehicle().getModel());
            row.put("Placa", parking.getVehicle().getLicensePlate());
            row.put("Tipo", parking.getVehicle().getType().name());
            row.put("Data/Hora Entrada", formatDate(parking.getEntryTime(), "dd/MM/yyyy hh:mm:ss"));
            row.put("Data/Hora Saída", parking.getExitTime() != null ? formatDate(parking.getExitTime(), "dd/MM/yyyy hh:mm:ss") : "Estacionado");

            rows.add(row);
        }

        Map<String, Long> summary = new HashMap<>();
        summary.put("Total de Entradas", (long) parkingList.size());
        summary.put("Total de Saídas", parkingList.stream().filter(parking -> parking.getExitTime() != null).count());

        return generateReport(title, columnHeaders, rows, summary);
    }


    public Map<String, Long> getSummaryOfEntriesAndExits() {
        List<Parking> parkings = parkingService.getAll();
        long totalEntries = parkings.stream().filter(m -> m.getEntryTime() != null).count();
        long totalExits = parkings.stream().filter(m -> m.getExitTime() != null).count();

        return Map.of(
                "totalEntries", totalEntries,
                "totalExits", totalExits
        );
    }

    public Map<String, Long> getSummaryOfEntriesAndExitsByHour() {
        List<Parking> parkings = parkingService.getAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00");

        Map<String, Long> entriesByHour = parkings.stream()
                .filter(m -> m.getEntryTime() != null)
                .collect(Collectors.groupingBy(m -> m.getEntryTime().format(formatter), Collectors.counting()));

        Map<String, Long> exitsByHour = parkings.stream()
                .filter(m -> m.getExitTime() != null)
                .collect(Collectors.groupingBy(m -> m.getExitTime().format(formatter), Collectors.counting()));

        entriesByHour.forEach((key, value) ->
                exitsByHour.merge(key, value, (v1, v2) -> v1 + v2)
        );

        return exitsByHour;
    }

    public Map<String, Long> getSummaryOfEntriesAndExitsByEstablishment(Long establishmentId) {
        List<Parking> parkings = parkingService.getParkingsByEstablishment(establishmentId);
        long totalEntries = parkings.stream().filter(m -> m.getEntryTime() != null).count();
        long totalExits = parkings.stream().filter(m -> m.getExitTime() != null).count();

        return Map.of(
                "totalEntries", totalEntries,
                "totalExits", totalExits
        );
    }

    public Map<String, Map<String, Long>> getSummaryOfEntriesAndExitsByHourAndEstablishment(Long establishmentId) {
        List<Parking> parkings = parkingService.getParkingsByEstablishment(establishmentId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00");

        Map<String, Long> entriesByHour = parkings.stream()
                .filter(m -> m.getEntryTime() != null)
                .collect(Collectors.groupingBy(m -> m.getEntryTime().format(formatter), Collectors.counting()));

        Map<String, Long> exitsByHour = parkings.stream()
                .filter(m -> m.getExitTime() != null)
                .collect(Collectors.groupingBy(m -> m.getExitTime().format(formatter), Collectors.counting()));

        entriesByHour.forEach((key, value) ->
                exitsByHour.merge(key, value, (v1, v2) -> v1 + v2)
        );

        return Map.of(
                "entriesByHour", entriesByHour,
                "exitsByHour", exitsByHour
        );
    }
}