package it.tcweb.cinema.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.tcweb.cinema.models.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    Optional<Film> findByTitolo(String nome);

    List<Film> findByGenere(String genere);
}
