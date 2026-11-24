package br.com.elovital.dto;

import br.com.elovital.model.Idoso;

import java.time.Instant;
import java.time.LocalDate;

public record IdosoResponse(
        Long id,
        Long usuarioId,
        LocalDate dataNascimento,
        String tipoSanguineo,
        Instant createdAt,
        Instant updatedAt
) {
    public static IdosoResponse de(Idoso i) {
        return new IdosoResponse(i.getId(), i.getUsuario().getId(), i.getDataNascimento(),
                i.getTipoSanguineo(), i.getCreatedAt(), i.getUpdatedAt());
    }
}
