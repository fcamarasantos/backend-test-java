package br.com.brunobrolesi.parking.service;

import br.com.brunobrolesi.parking.model.*;
import br.com.brunobrolesi.parking.repositories.ParkingRepository;
import br.com.brunobrolesi.parking.repositories.ParkingSpaceRepository;
import br.com.brunobrolesi.parking.repositories.TicketRepository;
import br.com.brunobrolesi.parking.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public Ticket entry(Integer parkingId, String vehiclePlate) {
        Optional<Parking> parking = parkingRepository.findById(parkingId);

        if(parking.isEmpty()) return null;

        Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(vehiclePlate);

        if(vehicle.isEmpty()) return null;

        List<ParkingSpace> parkingSpaces;
        parkingSpaces = parking.get().getParkingSpaces();
        Optional<ParkingSpace> parkingSpace = parkingSpaces
                .stream()
                .filter(element -> element.getVehicleType() == vehicle.get().getType() && element.getState() == ParkingSpaceState.FREE)
                .findFirst();

        if(parkingSpace.isEmpty()) return null;

        parkingSpace.get().setState(ParkingSpaceState.BUSY);
        Ticket ticket = new Ticket(null, vehicle.get(), parkingSpace.get());

        parkingSpaceRepository.save(parkingSpace.get());
        return ticketRepository.save(ticket);
    }

    public Ticket exit(Integer parkingId, Integer id) {
        Optional<Parking> parking = parkingRepository.findById(parkingId);

        if(parking.isEmpty()) return null;

        Optional<Ticket> ticket = ticketRepository.findById(id);

        if(ticket.isEmpty()) return null;

        ticket.get().setExitTime(LocalDate.now());
        ticket.get().getParkingSpace().setState(ParkingSpaceState.FREE);

        return ticketRepository.save(ticket.get());

    }
}
