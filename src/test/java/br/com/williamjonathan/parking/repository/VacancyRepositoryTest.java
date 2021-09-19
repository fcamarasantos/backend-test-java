package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.model.Vacancy;
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
public class VacancyRepositoryTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    public void shouldFindAVacancyById() {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1L);

        vacancyRepository.save(vacancy);
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(vacancy.getId());

        assertTrue(optionalVacancy.isPresent());
        assertEquals(vacancy.getId(), optionalVacancy.get().getId());
    }

    @Test
    public void shouldNotFindAVacancyBecauseDontExist() {
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(2L);

        assertTrue(optionalVacancy.isEmpty());
    }

}