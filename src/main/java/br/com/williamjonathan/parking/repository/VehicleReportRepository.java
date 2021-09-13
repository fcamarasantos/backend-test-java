package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.VehicleReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VehicleReportRepository extends JpaRepository<VehicleReport, Long> {

    List<VehicleReport> findAllByParkingId(Long id);

    List<VehicleReport> findAllByVehicleLicensePlate(String vehicleLicensePlate);

    List<VehicleReport> findAllByParkingIdAndEntryDateBetween(Long parkingId, LocalDateTime from, LocalDateTime to);
}
