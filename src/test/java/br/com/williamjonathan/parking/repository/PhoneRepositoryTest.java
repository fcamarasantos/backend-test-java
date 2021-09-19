package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PhoneRepositoryTest {

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void shouldFindAPhoneById() {
        Phone phone = new Phone();
        phone.setId(1L);

        phoneRepository.save(phone);
        Optional<Phone> optionalPhone = phoneRepository.findById(phone.getId());

        assertTrue(optionalPhone.isPresent());
        assertEquals(phone.getId(), optionalPhone.get().getId());
    }

    @Test
    public void shouldNotFindAPhoneBecauseDontExist() {
        Optional<Phone> optionalPhone = phoneRepository.findById(2L);

        assertTrue(optionalPhone.isEmpty());
    }

}