package br.com.elovital.dto;

import br.com.elovital.model.Alergia;

import java.time.Instant;

public record AlergiaResponse(
        Long id,
        Long idosoId,
        String descricao,
        Instant createdAt,
        Instant updatedAt
) {
    public static AlergiaResponse de(Alergia a) {
        return new AlergiaResponse(a.getId(), a.getIdoso().getId(), a.getDescricao(),
                a.getCreatedAt(), a.getUpdatedAt());
    }
}
