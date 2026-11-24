package br.com.elovital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioAtualizarRequest(
        @NotBlank @Size(max = 150) String nome,
        @Size(max = 20) String telefone,
        Integer tamanhoFonte
) {
}
