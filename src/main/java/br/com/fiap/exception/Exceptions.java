package br.com.fiap.exception;

public class Exceptions extends RuntimeException {

    public Exceptions(String mensagem) {
        super(mensagem);
    }

    public Exceptions(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
