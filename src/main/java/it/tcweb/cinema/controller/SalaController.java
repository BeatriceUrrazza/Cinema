package it.tcweb.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.tcweb.cinema.models.Sala;
import it.tcweb.cinema.service.SalaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sala")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Sala", description = "Gestione delle sale del cinema")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping
    @Operation(summary = "Recupera tutte le sale", description = "Restituisce tutte le sale presenti nel database db_cinema")
    public ResponseEntity<List<Sala>> trovaTutti() {
        return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera una sala tramite l'ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sala trovata"),
            @ApiResponse(responseCode = "404", description = "Sala non trovata"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Sala> trovaPerId(@PathVariable Integer id) {
        Sala sala = service.trovaPerId(id);

        if (sala == null) {
            return ResponseEntity.notFound().build(); // Se non esiste ritorna 404
        }

        return ResponseEntity.ok(sala); // Altrimenti 200 + dati
    }

    @PostMapping
    @Operation(summary = "Crea un sala")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sala creata"),
            @ApiResponse(responseCode = "400", description = "Sala non creata per dati sbagliati"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Sala> crea(@Valid @RequestBody Sala sala) {
        Sala salaSalvato = service.salva(sala);

        return ResponseEntity.status(201).body(salaSalvato);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Sostituisce la sala con ID specificato")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sala sostutuita"),
            @ApiResponse(responseCode = "400", description = "Sala non sostutuita per dati sbagliati"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Sala> aggiorna(@PathVariable Integer id, @RequestBody Sala sala) {
        if (service.trovaPerId(id) == null)
            return ResponseEntity.notFound().build();

        sala.setId(id);

        return ResponseEntity.ok(service.salva(sala));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancella la sala con ID specificato")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sala cancellata"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Void> elimina(@PathVariable Integer id) {
        if (service.trovaPerId(id) == null)
            return ResponseEntity.notFound().build();

        service.elimina(id);
        return ResponseEntity.noContent().build();
    }

}
