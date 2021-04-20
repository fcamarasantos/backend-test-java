package io.org.alefwhite.domain.repository;

import io.org.alefwhite.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRespository extends JpaRepository<Cliente, Integer> {

    @Query(value = " select * from clientes c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome );

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.veiculos where c.id = :id  ")
    Cliente findClienteFetchVeiculos( @Param("id") Integer id );

}
