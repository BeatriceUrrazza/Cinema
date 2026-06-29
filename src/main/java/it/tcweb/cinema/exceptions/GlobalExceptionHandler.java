package it.tcweb.cinema.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Intercetta le eccezioni di TUTTI i controller
public class GlobalExceptionHandler {

    // Gestisce le eccezioni di tipo RisorsaNonTrovataException
    @ExceptionHandler(RisorsaNonTrovataException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RisorsaNonTrovataException ex) {
        ErrorResponse body = new ErrorResponse(404, ex.getMessage());

        return ResponseEntity.status(404).body(body);
    }

    // Gestisce qualsiasi altro tipo di eccezione
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        ErrorResponse body = new ErrorResponse(500, ex.getMessage());

        return ResponseEntity.status(500).body(body);
    }

    // Gestisce le eccezioni dei vincoli @NotBlank, @Size ecc.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errori = new LinkedHashMap<>();

        ex.getBindingResult() // contiene il risultato della validazione
                .getFieldErrors() // contiene la lista degli errori
                .forEach(err -> errori.put(err.getField(), err.getDefaultMessage())); // inscerisce nella mappa il nome
                                                                                      // del campo che ha dato l'errore
                                                                                      // e poi l'errore specifico

        return ResponseEntity.badRequest().body(errori);
    }
}
