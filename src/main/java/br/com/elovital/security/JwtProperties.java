package br.com.elovital.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuração do JWT (application.properties):
 *   elovital.jwt.secret=<mínimo 32 caracteres, vindo de variável de ambiente>
 *   elovital.jwt.expiracao-minutos=480
 */
@ConfigurationProperties(prefix = "elovital.jwt")
public class JwtProperties {

    private String secret;
    private long expiracaoMinutos = 480;

    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
    public long getExpiracaoMinutos() { return expiracaoMinutos; }
    public void setExpiracaoMinutos(long expiracaoMinutos) { this.expiracaoMinutos = expiracaoMinutos; }
}
