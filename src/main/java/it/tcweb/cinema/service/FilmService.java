package it.tcweb.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tcweb.cinema.exceptions.RisorsaNonTrovataException;
import it.tcweb.cinema.models.Film;
import it.tcweb.cinema.repository.FilmRepository;

@Service
public class FilmService {
    @Autowired
    private FilmRepository repository;

    public List<Film> trovaTutti() {
        return repository.findAll();
    }

    public Film trovaPerId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Film con id " + id + " non trovato"));
    }

    public Film trovaPerTitolo(String nome) {
        return repository.findByTitolo(nome)
                .orElseThrow(() -> new RisorsaNonTrovataException("Film con nome " + nome + " non trovato"));
    }

    public List<Film> trovaPerGenere(String genere) {
        List<Film> lista = repository.findByGenere(genere);

        if (lista.size() == 0)
            throw new RisorsaNonTrovataException("Film con genere " + genere + " non trovati");

        return lista;
    }

    public Film salva(Film film) {
        return repository.save(film);
    }

    public void elimina(Integer id) {
        repository.deleteById(id);
    }

}
