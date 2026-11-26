package br.com.elovital.security;

import br.com.elovital.model.Usuario;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

/** Emite tokens JWT assinados (HS256). A validação é feita pelo JwtDecoder do Spring Security. */
@Service
public class JwtService {

    private final JwtEncoder encoder;
    private final JwtProperties props;

    public JwtService(JwtEncoder encoder, JwtProperties props) {
        this.encoder = encoder;
        this.props = props;
    }

    public String gerar(Usuario usuario, Instant emitidoEm) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("elovital")
                .subject(String.valueOf(usuario.getId()))
                .issuedAt(emitidoEm)
                .expiresAt(emitidoEm.plus(Duration.ofMinutes(props.getExpiracaoMinutos())))
                .claim("perfil", usuario.getTipoPerfil().name())
                .build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
