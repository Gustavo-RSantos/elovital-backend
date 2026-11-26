package br.com.elovital.security;

import br.com.elovital.exception.AcessoNegadoException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

/** Acesso ao usuário autenticado (extraído do JWT já validado pelo Spring Security). */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Long usuarioIdAtual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            try {
                return Long.valueOf(jwt.getSubject());
            } catch (NumberFormatException ignored) {
                // cai no erro abaixo
            }
        }
        throw new AcessoNegadoException("Usuário não autenticado.");
    }

    /** Garante que o recurso pertence ao próprio usuário autenticado. */
    public static void exigirProprioUsuario(Long usuarioId) {
        if (!usuarioIdAtual().equals(usuarioId)) {
            throw new AcessoNegadoException("Você só pode acessar os dados da sua própria conta.");
        }
    }
}
