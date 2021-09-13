package br.com.williamjonathan.parking.model.dto;

import br.com.williamjonathan.parking.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class VacancyDto {

    private Long id;
    private Integer quantity;
    private Integer quantityOcuppied;
    private String typeName;

    public VacancyDto(Vacancy vacancy) {
        this.id = vacancy.getId();
        this.quantity = vacancy.getQuantity();
        this.quantityOcuppied = vacancy.getQuantityOcuppied();
        this.typeName = vacancy.getType().getName();
    }

    public static List<VacancyDto> converter(List<Vacancy> vacancies) {
        List<VacancyDto> vacancyDtos = new ArrayList<>();
        if(vacancies != null) {
            vacancies.forEach(
                    vacancy -> {
                        VacancyDto vacancyDto = new VacancyDto(vacancy);
                        vacancyDtos.add(vacancyDto);
                    }
            );
        }
        return vacancyDtos;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getQuantityOcuppied() {
        return quantityOcuppied;
    }

    public String getTypeName() {
        return typeName;
    }
}
