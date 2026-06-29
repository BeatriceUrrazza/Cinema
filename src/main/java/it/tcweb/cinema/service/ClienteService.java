package it.tcweb.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tcweb.cinema.exceptions.RisorsaNonTrovataException;
import it.tcweb.cinema.models.Cliente;
import it.tcweb.cinema.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> trovaTutti() {
        return repository.findAll();
    }

    public Cliente trovaPerId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Cliente con id " + id + " non trovato"));
    }

    public Cliente salva(Cliente cliente) {
        return repository.save(cliente);
    }

    public void elimina(Integer id) {
        repository.deleteById(id);
    }
}
