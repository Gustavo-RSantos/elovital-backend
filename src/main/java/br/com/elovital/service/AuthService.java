package br.com.elovital.service;

import br.com.elovital.dto.LoginRequest;
import br.com.elovital.dto.LoginResponse;
import br.com.elovital.dto.UsuarioResponse;
import br.com.elovital.exception.CredenciaisInvalidasException;
import br.com.elovital.model.Idoso;
import br.com.elovital.model.Usuario;
import br.com.elovital.repository.IdosoRepository;
import br.com.elovital.repository.UsuarioRepository;
import br.com.elovital.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Locale;

@Service
@Transactional(readOnly = true)
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final IdosoRepository idosoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,
                       IdosoRepository idosoRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.idosoRepository = idosoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest req) {
        String email = req.email().trim().toLowerCase(Locale.ROOT);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new CredenciaisInvalidasException("E-mail ou senha inválidos."));

        if (!passwordEncoder.matches(req.senha(), usuario.getSenhaHash())) {
            throw new CredenciaisInvalidasException("E-mail ou senha inválidos.");
        }

        Long idosoId = idosoRepository.findByUsuarioId(usuario.getId())
                .map(Idoso::getId)
                .orElse(null);

        Instant agora = Instant.now();
        String token = jwtService.gerar(usuario, agora);

        return new LoginResponse(
                token,
                "Bearer",
                agora,
                UsuarioResponse.de(usuario),
                idosoId
        );
    }
}
