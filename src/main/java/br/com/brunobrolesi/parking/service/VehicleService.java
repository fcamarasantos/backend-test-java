package br.com.brunobrolesi.parking.service;

import br.com.brunobrolesi.parking.model.Vehicle;
import br.com.brunobrolesi.parking.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    public Vehicle findById(Integer id) {
        Optional<Vehicle> obj = repository.findById(id);
        if(obj.isPresent()) return obj.get();
        return null;
    }

    public Vehicle insert(Vehicle obj) {
        return repository.save(obj);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }


}
