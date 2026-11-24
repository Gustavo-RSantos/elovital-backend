package br.com.elovital.dto;

import br.com.elovital.model.enums.TipoPerfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioCriarRequest(
        @NotBlank @Size(max = 150) String nome,
        @NotBlank @Email @Size(max = 255) String email,
        // BCrypt considera no máximo 72 bytes da senha
        @NotBlank @Size(min = 8, max = 72) String senha,
        @NotNull TipoPerfil tipoPerfil,
        @Size(max = 20) String telefone,
        Integer tamanhoFonte
) {
}
