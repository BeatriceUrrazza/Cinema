package it.tcweb.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tcweb.cinema.models.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

}
