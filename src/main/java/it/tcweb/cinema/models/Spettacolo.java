package it.tcweb.cinema.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spettacolo")
@Data
@NoArgsConstructor
public class Spettacolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La data e l'ora delo spettacolo sono obbligatori")
    @FutureOrPresent(message = "Inserire una data valdia (oggi o nel futuro)")
    private LocalDateTime dataOra;

    @OneToMany(mappedBy = "spettacolo")
    @JsonIgnore
    private List<Prenotazione> prenotazioni;

    @NotNull(message = "Il prezzo è obbligatorio")
    @PositiveOrZero(message = "Il prezzo deve essere positivo o nullo")
    private BigDecimal prezzo;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    public int getPrenotazioniTotali() {

        List<Prenotazione> prenotazioni = this.getPrenotazioni();
        int postiPrenotati = 0;

        for (Prenotazione prenotazione : prenotazioni) {
            postiPrenotati += prenotazione.getNumeroPostiPrenotati();
        }

        return postiPrenotati;
    }
}
