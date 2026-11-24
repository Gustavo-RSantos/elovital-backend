package br.com.elovital.dto;

import br.com.elovital.model.Usuario;
import br.com.elovital.model.enums.TipoPerfil;

import java.time.Instant;

/** Nunca expõe senhaHash. */
public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        TipoPerfil tipoPerfil,
        String telefone,
        Integer tamanhoFonte,
        Instant createdAt,
        Instant updatedAt
) {
    public static UsuarioResponse de(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getTipoPerfil(),
                u.getTelefone(), u.getTamanhoFonte(), u.getCreatedAt(), u.getUpdatedAt());
    }
}
