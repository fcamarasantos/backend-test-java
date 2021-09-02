package br.com.williamjonathan.parking.model;

import javax.persistence.*;

@Entity
@Table(name = "types")
public class Type {

    @Id
    private Long id;

    private String name;

    public Type() {
    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
