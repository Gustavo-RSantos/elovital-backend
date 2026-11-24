package br.com.elovital.exception;

/** Conflito com o estado atual dos dados, ex.: e-mail já cadastrado (HTTP 409). */
public class ConflitoException extends RuntimeException {

    public ConflitoException(String mensagem) {
        super(mensagem);
    }
}
