package br.com.brunobrolesi.parking;

import br.com.brunobrolesi.parking.domain.City;
import br.com.brunobrolesi.parking.domain.State;
import br.com.brunobrolesi.parking.domain.Vehicle;
import br.com.brunobrolesi.parking.domain.VehicleType;
import br.com.brunobrolesi.parking.repositories.CityRepository;
import br.com.brunobrolesi.parking.repositories.StateRepository;
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

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Vehicle v1 = new Vehicle(null,"FORD","Fusion","2018","Preto","ALX1282",VehicleType.CARRO);
		Vehicle v2 = new Vehicle(null,"Ford","Ka","2014","Branco","ALX8182",VehicleType.CARRO);
		Vehicle v3 = new Vehicle(null,"volkswagen","gol","2020","Azul","ALX4182",VehicleType.CARRO);

		vehicleRepository.saveAll(Arrays.asList(v1, v2, v3));

		State s1 = new State(null, "São Paulo");
		State s2 = new State(null, "Paraná");

		City c1 = new City(null, "Santos", s1);
		City c2 = new City(null, "Campo Mourão", s2);
		City c3 = new City(null, "São Paulo", s1);

		s1.getCities().addAll(Arrays.asList(c1, c3));
		s2.getCities().addAll(Arrays.asList(c2));

		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));


	}
}
