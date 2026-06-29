package it.tcweb.cinema.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tcweb.cinema.models.Spettacolo;

public interface SpettacoloRepository extends JpaRepository<Spettacolo, Integer> {

    List<Spettacolo> findByDataOraGreaterThanEqualOrderByDataOraAsc(LocalDateTime data);
}
