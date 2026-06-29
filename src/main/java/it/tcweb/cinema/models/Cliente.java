package it.tcweb.cinema.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nominativo del cliente è obbligatorio")
    @Size(max = 60, message = "La lunghezza del nominativo deve essere < 60")
    private String nominativo;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Prenotazione> prenotazioni;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Inserire un'email valida")
    private String email;
}
