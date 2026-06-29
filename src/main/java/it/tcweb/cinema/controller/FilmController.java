package it.tcweb.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.tcweb.cinema.models.Film;
import it.tcweb.cinema.service.FilmService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/films")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Films", description = "Gestione dei film del cinema")
public class FilmController {

    @Autowired
    private FilmService service;

    @GetMapping
    @Operation(summary = "Recupera tutti i film", description = "Restituisce tutti i film presenti nel database db_cinema")
    public ResponseEntity<List<Film>> trovaTutti() {
        return ResponseEntity.ok(service.trovaTutti());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera un film tramite l'ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film trovato"),
            @ApiResponse(responseCode = "404", description = "Film non trovato"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Film> trovaPerId(@PathVariable Integer id) {
        Film film = service.trovaPerId(id);

        if (film == null) {
            return ResponseEntity.notFound().build(); // Se non esiste ritorna 404
        }

        return ResponseEntity.ok(film); // Altrimenti 200 + dati
    }

    @GetMapping("/genere")
    @Operation(summary = "Recupera un film tramite il genere")
    public ResponseEntity<List<Film>> trovaPerGenere(@RequestParam String genere) {
        return ResponseEntity.ok(service.trovaPerGenere(genere));
    }

    @GetMapping("/nome")
    @Operation(summary = "Recupera un film tramite il nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film trovato"),
            @ApiResponse(responseCode = "404", description = "Film non trovato"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Film> trovaPerTitolo(@RequestParam String nome) {
        Film film = service.trovaPerTitolo(nome);

        if (film == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(film);
    }

    @PostMapping
    @Operation(summary = "Crea un film")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Film creato"),
            @ApiResponse(responseCode = "400", description = "Film non creato per dati sbagliati"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Film> crea(@Valid @RequestBody Film film) {
        Film filmSalvato = service.salva(film);

        return ResponseEntity.status(201).body(filmSalvato);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Sostituisce il film con ID specificato")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Film sostutuito"),
            @ApiResponse(responseCode = "400", description = "Film non sostutuito per dati sbagliati"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Film> aggiorna(@PathVariable Integer id, @RequestBody Film film) {
        if (service.trovaPerId(id) == null)
            return ResponseEntity.notFound().build();

        film.setId(id);

        return ResponseEntity.ok(service.salva(film));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancella il film con ID specificato")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Film cancellato"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public ResponseEntity<Void> elimina(@PathVariable Integer id) {
        if (service.trovaPerId(id) == null)
            return ResponseEntity.notFound().build();

        service.elimina(id);
        return ResponseEntity.noContent().build();
    }

}
