package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.model.Vehicle;
import br.com.williamjonathan.parking.model.VehicleModel;
import br.com.williamjonathan.parking.model.dto.VehicleDto;
import br.com.williamjonathan.parking.model.form.VehicleForm;
import br.com.williamjonathan.parking.model.form.exception.LicenseplateDuplicateEntryException;
import br.com.williamjonathan.parking.model.form.exception.handlers.LicenseplateDuplicateEntryHandler;
import br.com.williamjonathan.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ParkingServiceImpl parkingService;

    @Autowired
    VacancyServiceImpl vacancyService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    VehicleModelServiceImpl vehicleModelService;

    @Autowired
    VehicleReportServiceImpl vehicleReportService;

    @Override
    public ResponseEntity<?> create(VehicleForm form) {
        Optional<VehicleModel> optionalVehicleModel = vehicleModelService.searchByName(form.getVehicleModelName());
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByLicensePlate(form.getLicensePlate());
        if(optionalVehicleModel.isPresent()) {
            if(optionalVehicle.isEmpty()) {

                Vehicle vehicleForm = new Vehicle(form.getColor(),
                        form.getLicensePlate(),
                        optionalVehicleModel.get());

                vehicleRepository.save(vehicleForm);
                VehicleDto vehicle = new VehicleDto(vehicleForm);
                return new ResponseEntity<VehicleDto>(vehicle, HttpStatus.OK);
            }
            throw new LicenseplateDuplicateEntryException();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> read(String licensePlate) {
        //Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByLicensePlate(licensePlate);
        if(optionalVehicle.isPresent()) {
            return new ResponseEntity<VehicleDto>(new VehicleDto(optionalVehicle.get()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> park(Long parkingId, String licensePlate) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByLicensePlate(licensePlate);
        Optional<Parking> optionalParking = parkingService.searchById(parkingId);
        if(optionalParking.isPresent() && optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            Type typeOfVehicle = vehicle.getVehicleModel().getType();
            if(!this.isParked(vehicle) && vacancyService.parkingHasVacancyOfThisType(optionalParking.get(), typeOfVehicle))  {

                vehicle.setParking(optionalParking.get());
                vehicleRepository.save(vehicle);

                vacancyService.increaseQuantityOcuppiedByType(optionalParking.get(), typeOfVehicle);
                vehicleReportService.entry(vehicle, vehicle.getParking());

                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build(); // EXCEPTION FOR THE VEHICLE IS PARKED OR THE PARKING DONT HAVE VACANCY OF THIS TYPE
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> unpark(String licensePlate) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByLicensePlate(licensePlate);
        if(optionalVehicle.isPresent()) {
            if(isParked(optionalVehicle.get())) {
                Vehicle vehicle = optionalVehicle.get();
                vacancyService.decreaseQuantityOcuppiedByType(vehicle.getParking(), vehicle.getVehicleModel().getType());
                vehicle.setParking(null);
                vehicleReportService.exit(vehicle);
                vehicleRepository.save(vehicle);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build(); //Exception for vehicle is not parked
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public Boolean isParked(Vehicle vehicle) {
        if(vehicle.getParking() == null) {
            return false;
        }
        return true;
    }

    @Override
    public ResponseEntity<?> readVehicleLogged() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String licensePlate = ((UserDetails) principal).getUsername();
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByLicensePlate(licensePlate);
        if(optionalVehicle.isPresent()) {
            VehicleDto vehicleDto = new VehicleDto(optionalVehicle.get());
            return new ResponseEntity<VehicleDto>(vehicleDto, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

}
