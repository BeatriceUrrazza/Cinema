package it.tcweb.cinema.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film")
@Data
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo del film è obbligatorio")
    @Size(max = 30, message = "La lunghezza del titolo deve essere < 30")
    private String titolo;

    @Size(max = 100, message = "La lunghezza della descrizione deve essere < 100")
    private String descrizione;

    @NotNull(message = "La durata del film è obbligatoria")
    @Positive(message = "La durata deve essere positiva")
    private Integer durataMinuti;

    @NotBlank(message = "Il genere del film è obbligatorio")
    @Size(max = 30, message = "La lunghezza del genere deve essere < 30")
    private String genere;

    @NotNull(message = "La durata del film è obbligatoria")
    private LocalDate dataUscita;

    @OneToMany(mappedBy = "film")
    @JsonIgnore
    private List<Spettacolo> spettacoli;
}
