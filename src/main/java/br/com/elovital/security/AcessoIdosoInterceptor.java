package br.com.elovital.security;

import br.com.elovital.exception.AcessoNegadoException;
import br.com.elovital.model.VinculoAcesso;
import br.com.elovital.model.enums.StatusVinculo;
import br.com.elovital.repository.IdosoRepository;
import br.com.elovital.repository.VinculoAcessoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.time.Instant;
import java.util.Map;

/**
 * Autorização por idoso para toda rota /api/idosos/{id}/**:
 *  - só o próprio idoso ou quem tem vínculo ATIVO (e não expirado) com ele acessa;
 *  - marcar lembretes (PATCH .../lembretes/...) é exclusivo do idoso (app mobile);
 *  - excluir o perfil do idoso é exclusivo do próprio idoso.
 * Usa a variável de path já resolvida pelo Spring MVC ({idosoId} ou {id}), sem reinterpretar a URL.
 */
@Component
public class AcessoIdosoInterceptor implements HandlerInterceptor {

    private final IdosoRepository idosoRepository;
    private final VinculoAcessoRepository vinculoRepository;

    public AcessoIdosoInterceptor(IdosoRepository idosoRepository, VinculoAcessoRepository vinculoRepository) {
        this.idosoRepository = idosoRepository;
        this.vinculoRepository = vinculoRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Map<String, String> vars = (Map<String, String>) request.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (vars == null) return true; // GET/POST /api/idosos (sem id): tratado no controller/service

        String rawId = vars.getOrDefault("idosoId", vars.get("id"));
        if (rawId == null) return true;

        Long idosoId;
        try {
            idosoId = Long.valueOf(rawId);
        } catch (NumberFormatException e) {
            return true; // deixa o MVC responder 400
        }

        Long usuarioId = SecurityUtils.usuarioIdAtual();
        boolean ehOIdoso = idosoRepository.findByUsuarioId(usuarioId)
                .map(i -> i.getId().equals(idosoId)).orElse(false);

        if (!ehOIdoso && !possuiVinculoVigente(idosoId, usuarioId)) {
            throw new AcessoNegadoException("Você não tem acesso a este idoso.");
        }

        String metodo = request.getMethod();
        String uri = request.getRequestURI();
        if ("PATCH".equals(metodo) && uri.contains("/lembretes/") && !ehOIdoso) {
            throw new AcessoNegadoException("Somente o idoso pode marcar lembretes como realizados.");
        }
        if ("DELETE".equals(metodo) && vars.get("idosoId") == null && !ehOIdoso) {
            throw new AcessoNegadoException("Somente o próprio idoso pode excluir o seu perfil.");
        }
        return true;
    }

    private boolean possuiVinculoVigente(Long idosoId, Long usuarioId) {
        return vinculoRepository.findByIdosoIdAndUsuarioId(idosoId, usuarioId)
                .map(AcessoIdosoInterceptor::vigente).orElse(false);
    }

    public static boolean vigente(VinculoAcesso v) {
        return v.getStatus() == StatusVinculo.ATIVO
                && (v.getDataExpiracao() == null || v.getDataExpiracao().isAfter(Instant.now()));
    }
}
