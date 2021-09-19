package br.com.williamjonathan.parking.repository;


import br.com.williamjonathan.parking.model.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void shouldFindAAddressById() {
        Address address = new Address();
        address.setId(1L);

        addressRepository.save(address);
        Optional<Address> optionalAddress = addressRepository.findById(address.getId());

        assertTrue(optionalAddress.isPresent());
        assertEquals(address.getId(), optionalAddress.get().getId());
    }

    @Test
    public void shouldNotFindAAddressBecauseDontExist() {
        Optional<Address> optionalAddress = addressRepository.findById(2L);

        assertTrue(optionalAddress.isEmpty());
    }

}