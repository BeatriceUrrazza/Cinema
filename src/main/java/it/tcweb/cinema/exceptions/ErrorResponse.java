package it.tcweb.cinema.exceptions;

// Classe che deve contenere due cose: il codice di stato e un messaggio leggibile
public class ErrorResponse {

    private int status;
    private String messaggio;

    public ErrorResponse(int status, String messaggio) {
        this.status = status;
        this.messaggio = messaggio;
    }

    public int getStatus() {
        return status;
    }

    public String getMessaggio() {
        return messaggio;
    }

}
