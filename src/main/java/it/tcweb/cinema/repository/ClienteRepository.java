package it.tcweb.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tcweb.cinema.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
