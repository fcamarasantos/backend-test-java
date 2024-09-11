package com.harrisson.parking_api.repository;

import com.harrisson.parking_api.model.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {

    @Query("SELECT a FROM AccessControl a WHERE a.vehicle.id = :vehicleId AND a.establishment.id = :establishmentId AND a.exitTime IS NULL")
    AccessControl findByVehicleIdAndEstablishmentIdAndExitTimeIsNull(Long vehicleId, Long establishmentId);

    @Query("SELECT COUNT(a) FROM AccessControl a WHERE a.establishment.id = :establishmentId")
    int countByEstablishmentId(Long establishmentId);

    @Query("SELECT COUNT(a) FROM AccessControl a WHERE a.establishment.id = :establishmentId AND a.entryTime IS NOT NULL")
    int countEntriesByEstablishmentId(Long establishmentId);

    @Query("SELECT COUNT(a) FROM AccessControl a WHERE a.establishment.id = :establishmentId AND a.exitTime IS NOT NULL")
    int countExitsByEstablishmentId(Long establishmentId);

    @Query("SELECT COUNT(a) FROM AccessControl a WHERE a.establishment.id = :establishmentId AND a.vehicle.type = :vehicleType")
    int countByEstablishmentIdAndVehicleType(Long establishmentId, String vehicleType);

    @Query("SELECT HOUR(a.entryTime) as hour, COUNT(a) as count FROM AccessControl a WHERE a.establishment.id = :establishmentId AND a.entryTime BETWEEN :startTime AND :endTime GROUP BY HOUR(a.entryTime)")
    List<Object[]> countVehiclesByHour(Long establishmentId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT HOUR(a.entryTime) as hour, COUNT(a) as count FROM AccessControl a WHERE a.establishment.id = :establishmentId AND a.entryTime BETWEEN :startTime AND :endTime GROUP BY HOUR(a.entryTime)")
    List<Object[]> countEntriesByHour(Long establishmentId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT HOUR(a.exitTime) as hour, COUNT(a) as count FROM AccessControl a WHERE a.establishment.id = :establishmentId AND a.exitTime BETWEEN :startTime AND :endTime GROUP BY HOUR(a.exitTime)")
    List<Object[]> countExitsByHour(Long establishmentId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT DATE(a.entryTime) as date, COUNT(a) as count FROM AccessControl a WHERE a.establishment.id = :establishmentId GROUP BY DATE(a.entryTime)")
    List<Object[]> countVehiclesByDay(Long establishmentId);

    @Query("SELECT a.vehicle.type as type, COUNT(a) as count FROM AccessControl a WHERE a.establishment.id = :establishmentId GROUP BY a.vehicle.type")
    List<Object[]> countVehiclesByType(Long establishmentId);

    @Query("SELECT HOUR(a.entryTime) as hour, a.vehicle.type as type, COUNT(a) as count FROM AccessControl a WHERE a.establishment.id = :establishmentId AND a.entryTime BETWEEN :startTime AND :endTime GROUP BY HOUR(a.entryTime), a.vehicle.type")
    List<Object[]> countVehiclesByTypeAndHour(Long establishmentId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT MONTH(a.entryTime) as month, COUNT(a) as count FROM AccessControl a WHERE a.establishment.id = :establishmentId AND YEAR(a.entryTime) = :year GROUP BY MONTH(a.entryTime)")
    List<Object[]> countVehiclesByMonth(Long establishmentId, int year);

    @Query("SELECT YEAR(a.entryTime) as year, COUNT(a) as count FROM AccessControl a WHERE a.establishment.id = :establishmentId GROUP BY YEAR(a.entryTime)")
    List<Object[]> countVehiclesByYear(Long establishmentId);

}