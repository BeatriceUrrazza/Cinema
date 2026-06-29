package it.tcweb.cinema.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prenotazione")
@Data
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Il numero di posti prenotati è obbligatorio")
    @PositiveOrZero(message = "Il numero di posti prenotati deve essere positivo o nullo")
    private Integer numeroPostiPrenotati;

    @ManyToOne
    @JoinColumn(name = "spettacolo_id")
    private Spettacolo spettacolo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
