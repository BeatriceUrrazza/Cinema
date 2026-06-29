package it.tcweb.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tcweb.cinema.exceptions.PrenotazioneNonDisponibileException;
import it.tcweb.cinema.exceptions.RisorsaNonTrovataException;
import it.tcweb.cinema.models.Prenotazione;
import it.tcweb.cinema.models.Sala;
import it.tcweb.cinema.models.Spettacolo;
import it.tcweb.cinema.repository.PrenotazioneRepository;
import it.tcweb.cinema.repository.SpettacoloRepository;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository PrenotazioneRepository;

    @Autowired
    private SpettacoloRepository SpettacoloRepository;

    public List<Prenotazione> trovaTutti() {
        return PrenotazioneRepository.findAll();
    }

    public Prenotazione trovaPerId(Integer id) {
        return PrenotazioneRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Prenotazione con id " + id + " non trovata"));
    }

    public Prenotazione salva(Prenotazione prenotazione, Integer idSpettacolo) {

        Spettacolo spettacolo = SpettacoloRepository.findById(idSpettacolo).orElseThrow(
                () -> new RisorsaNonTrovataException("Spettacolo con id " + idSpettacolo + " non trovato"));

        Sala sala = spettacolo.getSala();

        int numPrenotazioni = spettacolo.getPrenotazioniTotali();

        if (numPrenotazioni + prenotazione.getNumeroPostiPrenotati() < sala.getNumeroPosti()) {

            return PrenotazioneRepository.save(prenotazione);
        }

        throw new PrenotazioneNonDisponibileException("Non sono disponibili posti per la prenotazione");
    }

    public void elimina(Integer id) {
        PrenotazioneRepository.deleteById(id);
    }
}
