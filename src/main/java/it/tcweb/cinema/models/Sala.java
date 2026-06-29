package it.tcweb.cinema.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sala")
@Data
@NoArgsConstructor
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome della sala è obbligatorio")
    @Size(max = 30, message = "La lunghezza del nome deve essere < 30")
    private String nome;

    @NotNull(message = "Il numero dei posti è obbligatoria")
    @Positive(message = "Il numero dei posti positivo")
    private Integer numeroPosti;

    @OneToMany(mappedBy = "sala")
    @JsonIgnore
    private List<Spettacolo> spettacoli;
}
