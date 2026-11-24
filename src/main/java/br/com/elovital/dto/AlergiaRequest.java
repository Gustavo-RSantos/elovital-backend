package br.com.elovital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlergiaRequest(
        @NotBlank @Size(max = 255) String descricao
) {
}
