package br.com.elovital.exception;

/** Requisição válida no formato, mas que viola uma regra de negócio (HTTP 422). */
public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
