package io.org.alefwhite.domain.entity;

import javax.persistence.*;

@Entity
@Table( name = "usuarios" )
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "senha", length = 100)
    private String senha;

    @Column(name = "admin", columnDefinition = "false")
    private Boolean admin;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
