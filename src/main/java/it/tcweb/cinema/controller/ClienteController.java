package it.tcweb.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.tcweb.cinema.models.Cliente;
import it.tcweb.cinema.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Clienti", description = "Gestione dei clienti del cinema")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    @Operation(summary = "Recupera tutti i clienti", description = "Restituisce tutti i clienti presenti nel database db_cinema")
    public ResponseEntity<List<Cliente>> trovaTutti() {
        return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera un cliente tramite l'ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente trovato"),
            @ApiResponse(responseCode = "404", description = "Cliente non trovato"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Cliente> trovaPerId(@PathVariable Integer id) {
        Cliente cliente = service.trovaPerId(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build(); // Se non esiste ritorna 404
        }

        return ResponseEntity.ok(cliente); // Altrimenti 200 + dati
    }

    @PostMapping
    @Operation(summary = "Crea un cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creato"),
            @ApiResponse(responseCode = "400", description = "Cliente non creato per dati sbagliati"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Cliente> crea(@Valid @RequestBody Cliente cliente) {
        Cliente clienteSalvato = service.salva(cliente);

        return ResponseEntity.status(201).body(clienteSalvato);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Sostituisce il cliente con ID specificato")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente sostutuito"),
            @ApiResponse(responseCode = "400", description = "Cliente non sostutuito per dati sbagliati"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Cliente> aggiorna(@PathVariable Integer id, @RequestBody Cliente cliente) {
        if (service.trovaPerId(id) == null)
            return ResponseEntity.notFound().build();

        cliente.setId(id);

        return ResponseEntity.ok(service.salva(cliente));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancella il cliente con ID specificato")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente cancellato"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Void> elimina(@PathVariable Integer id) {
        if (service.trovaPerId(id) == null)
            return ResponseEntity.notFound().build();

        service.elimina(id);
        return ResponseEntity.noContent().build();
    }

}
