package it.tcweb.cinema.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tcweb.cinema.exceptions.RisorsaNonTrovataException;
import it.tcweb.cinema.models.Spettacolo;
import it.tcweb.cinema.models.DTO.SpettacoliDisponibilitaResponse;
import it.tcweb.cinema.repository.SpettacoloRepository;

@Service
public class SpettacoloService {

    @Autowired
    private SpettacoloRepository repository;

    public List<Spettacolo> trovaTutti() {
        return repository.findAll();
    }

    public Spettacolo trovaPerId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Spettacolo con id " + id + " non trovato"));
    }

    public Spettacolo salva(Spettacolo spettacolo) {
        return repository.save(spettacolo);
    }

    public void elimina(Integer id) {
        repository.deleteById(id);
    }

    public List<Spettacolo> trovaFuturi() {
        return repository.findByDataOraGreaterThanEqualOrderByDataOraAsc(LocalDateTime.now());
    }

    public SpettacoliDisponibilitaResponse trovaDisponibilita(Integer id) {
        Spettacolo trovato = repository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Spettacolo con id " + id + " non trovato"));

        SpettacoliDisponibilitaResponse dto = new SpettacoliDisponibilitaResponse();

        dto.setPostiTotali(trovato.getSala().getNumeroPosti());

        dto.setPostiPrenotati(trovato.getPrenotazioniTotali());

        dto.setPostiDisponibili(dto.getPostiTotali() - dto.getPostiPrenotati());

        return dto;
    }
}
