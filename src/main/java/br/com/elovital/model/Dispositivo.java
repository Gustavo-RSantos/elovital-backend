package br.com.elovital.model;

import jakarta.persistence.*;
import br.com.elovital.model.enums.Plataforma;

@Entity
@Table(name = "dispositivo")
public class Dispositivo extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "token_push", nullable = false, length = 512, unique = true)
    private String tokenPush;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma", nullable = false, length = 10)
    private Plataforma plataforma;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getTokenPush() { return tokenPush; }

    public void setTokenPush(String tokenPush) { this.tokenPush = tokenPush; }

    public Plataforma getPlataforma() { return plataforma; }

    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }
}
