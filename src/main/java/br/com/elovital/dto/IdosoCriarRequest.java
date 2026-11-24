package br.com.elovital.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record IdosoCriarRequest(
        @NotNull Long usuarioId,
        @NotNull @Past LocalDate dataNascimento,
        @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Tipo sanguíneo inválido (ex.: A+, O-, AB+)")
        String tipoSanguineo
) {
}
