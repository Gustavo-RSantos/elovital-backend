package br.com.elovital.exception;

/** Usuário autenticado, mas sem permissão para o recurso (HTTP 403). */
public class AcessoNegadoException extends RuntimeException {

    public AcessoNegadoException(String message) {
        super(message);
    }
}
