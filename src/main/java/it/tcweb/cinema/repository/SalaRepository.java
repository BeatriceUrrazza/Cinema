package it.tcweb.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tcweb.cinema.models.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer> {

}
