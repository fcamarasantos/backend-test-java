package br.com.williamjonathan.parking.model;

import javax.persistence.*;

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Integer quantityOcuppied;

    @ManyToOne
    @JoinColumn(name = "parking_id", referencedColumnName = "id")
    private Parking parking;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getQuantityOcuppied() {
        return quantityOcuppied;
    }

    public Type getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setQuantityOcuppied(Integer quantityOcuppied) {
        this.quantityOcuppied = quantityOcuppied;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
