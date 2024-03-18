package org.restapiparkinglot.restapiparkinglot.services;

import org.restapiparkinglot.restapiparkinglot.dtos.ParkingLotDTO;
import org.restapiparkinglot.restapiparkinglot.exception.AlreadyRegisteredException;
import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.restapiparkinglot.restapiparkinglot.model.ParkingLot;
import org.restapiparkinglot.restapiparkinglot.repository.ParkingLotRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository;

    public List<ParkingLot> findAll() {
        return parkingLotRepository.findAll();
    }

    public ParkingLot create(ParkingLotDTO parkingLotDTO) {
        isRegistered(parkingLotDTO.getCnpj());
        ParkingLot parkingLotModel = convertParking(parkingLotDTO);
        return parkingLotRepository.save(parkingLotModel);
    }

    public ParkingLot retrieveById(int id) throws NotFoundException {
        return parkingLotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking lot not found"));
    }

    public ParkingLot retrieveByCnpj(String cnpj) throws NotFoundException {
        return parkingLotRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new NotFoundException("Parking lot not found"));
    }

    public ParkingLot update(ParkingLot parkingLot, int id) throws NotFoundException {
        retrieveById(id);
        return parkingLotRepository.save(parkingLot);
    }

    public String delete(int id) throws NotFoundException{
        retrieveById(id);
        parkingLotRepository.deleteById(id);
        return "Parking lot successfully deleted";
    }

    private ParkingLot convertParking(ParkingLotDTO parkingLotDTO) {
        ParkingLot parkingLotModel = new ParkingLot();
        BeanUtils.copyProperties(parkingLotDTO, parkingLotModel);
        return parkingLotModel;
    }

    private void isRegistered(String cnpj) throws AlreadyRegisteredException {
        parkingLotRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new AlreadyRegisteredException("Parking lot already registered"));
    }
}