package it.tcweb.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tcweb.cinema.exceptions.RisorsaNonTrovataException;
import it.tcweb.cinema.models.Sala;
import it.tcweb.cinema.repository.SalaRepository;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;

    public List<Sala> trovaTutti() {
        return repository.findAll();
    }

    public Sala trovaPerId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Sala con id " + id + " non trovata"));
    }

    public Sala salva(Sala sala) {
        return repository.save(sala);
    }

    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
