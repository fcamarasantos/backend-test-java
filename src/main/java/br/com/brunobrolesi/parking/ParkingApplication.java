package br.com.brunobrolesi.parking;

import br.com.brunobrolesi.parking.domain.Vehicle;
import br.com.brunobrolesi.parking.domain.VehicleType;
import br.com.brunobrolesi.parking.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ParkingApplication implements CommandLineRunner {

	@Autowired
	private VehicleRepository vehicleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Vehicle v1 = new Vehicle(null,"FORD","Fusion","2018","Preto","ALX1282",VehicleType.CARRO);
		Vehicle v2 = new Vehicle(null,"Ford","Ka","2014","Branco","ALX8182",VehicleType.CARRO);
		Vehicle v3 = new Vehicle(null,"volkswagen","gol","2020","Azul","ALX4182",VehicleType.CARRO);

		vehicleRepository.saveAll(Arrays.asList(v1, v2, v3));
	}
}
