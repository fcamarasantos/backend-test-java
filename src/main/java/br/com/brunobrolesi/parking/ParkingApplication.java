package br.com.brunobrolesi.parking;

import br.com.brunobrolesi.parking.model.*;
import br.com.brunobrolesi.parking.repositories.*;
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

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private ParkingSpaceRepository parkingSpaceRepository;

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

		Parking p1 = new Parking(null,"555.555.555", "Estacionamento Central");
		p1.getPhones().addAll(Arrays.asList("2231-4323", "33432-34234"));

		Address a1 = new Address(null,"Avenida Brasil","333",null, p1, c1);
		Address a2 = new Address(null,"Avenida Matos","3233",null, p1, c3);

		p1.getAddresses().addAll(Arrays.asList(a1, a2));

		parkingRepository.saveAll(Arrays.asList(p1));
		addressRepository.saveAll(Arrays.asList(a1, a2));

		ParkingSpace ps1 = new ParkingSpace(null, VehicleType.CARRO, p1);
		ParkingSpace ps2 = new ParkingSpace(null, VehicleType.CARRO, p1);
		ParkingSpace ps3 = new ParkingSpace(null, VehicleType.MOTO, p1);

		p1.getParkingSpaces().addAll(Arrays.asList(ps1, ps2, ps3));

		parkingSpaceRepository.saveAll(Arrays.asList(ps1, ps2, ps3));
	}
}
