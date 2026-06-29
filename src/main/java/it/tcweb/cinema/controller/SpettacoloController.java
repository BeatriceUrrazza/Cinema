package it.tcweb.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.tcweb.cinema.models.Spettacolo;
import it.tcweb.cinema.models.DTO.SpettacoliDisponibilitaResponse;
import it.tcweb.cinema.service.SpettacoloService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/spettacolo")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Spettacolo", description = "Gestione degli spettacoli del cinema")
public class SpettacoloController {

    @Autowired
    private SpettacoloService service;

    @GetMapping
    @Operation(summary = "Recupera tutti gli spettacoli", description = "Restituisce tutti gli spettacoli presenti nel database db_cinema")
    public ResponseEntity<List<Spettacolo>> trovaTutti() {
        return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera uno spettacolo tramite l'ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Spettacolo trovato"),
            @ApiResponse(responseCode = "404", description = "Spettacolo non trovato"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Spettacolo> trovaPerId(@PathVariable Integer id) {
        Spettacolo spettacolo = service.trovaPerId(id);

        if (spettacolo == null) {
            return ResponseEntity.notFound().build(); // Se non esiste ritorna 404
        }

        return ResponseEntity.ok(spettacolo); // Altrimenti 200 + dati
    }

    @GetMapping("{id}/disponibilita")
    @Operation(summary = "Recupera la disponibilità di uno spettacolo")
    public ResponseEntity<SpettacoliDisponibilitaResponse> trovaDisponibilita(@PathVariable Integer id) {

        return ResponseEntity.ok(service.trovaDisponibilita(id));
    }

    @GetMapping("/futuri")
    @Operation(summary = "Recupera tutti gli spettacoli futuri")
    public ResponseEntity<List<Spettacolo>> trovaFuturi() {

        return ResponseEntity.ok(service.trovaFuturi());
    }

    @PostMapping
    @Operation(summary = "Crea uno spettacolo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Spettacolo creato"),
            @ApiResponse(responseCode = "400", description = "Spettacolo non creato per dati sbagliati"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Spettacolo> crea(@Valid @RequestBody Spettacolo spettacolo) {
        Spettacolo spettacoloSalvato = service.salva(spettacolo);

        return ResponseEntity.status(201).body(spettacoloSalvato);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Sostituisce lo spettacolo con ID specificato")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Spettacolo sostutuito"),
            @ApiResponse(responseCode = "400", description = "Spettacolo non sostutuito per dati sbagliati"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Spettacolo> aggiorna(@PathVariable Integer id, @RequestBody Spettacolo spettacolo) {
        if (service.trovaPerId(id) == null)
            return ResponseEntity.notFound().build();

        spettacolo.setId(id);

        return ResponseEntity.ok(service.salva(spettacolo));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancella lo spettacolo con ID specificato")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Spettacolo cancellato"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Void> elimina(@PathVariable Integer id) {
        if (service.trovaPerId(id) == null)
            return ResponseEntity.notFound().build();

        service.elimina(id);
        return ResponseEntity.noContent().build();
    }

}
