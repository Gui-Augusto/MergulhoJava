package com.algaworks.banco.modelo.excecao;

public class SaldoInsuficenteException extends RuntimeException {
    public SaldoInsuficenteException(String message) {
        super(message);
    }
}
