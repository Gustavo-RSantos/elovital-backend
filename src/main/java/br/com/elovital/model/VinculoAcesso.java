package br.com.elovital.model;

import jakarta.persistence.*;
import br.com.elovital.model.enums.StatusVinculo;
import br.com.elovital.model.enums.TipoRelacao;
import java.time.Instant;

@Entity
@Table(name = "vinculo_acesso", uniqueConstraints = @UniqueConstraint(columnNames = {"idoso_id", "usuario_id"}))
public class VinculoAcesso extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_relacao", nullable = false, length = 20)
    private TipoRelacao tipoRelacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusVinculo status;

    @Column(name = "data_expiracao")
    private Instant dataExpiracao;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoRelacao getTipoRelacao() { return tipoRelacao; }

    public void setTipoRelacao(TipoRelacao tipoRelacao) { this.tipoRelacao = tipoRelacao; }

    public StatusVinculo getStatus() { return status; }

    public void setStatus(StatusVinculo status) { this.status = status; }

    public Instant getDataExpiracao() { return dataExpiracao; }

    public void setDataExpiracao(Instant dataExpiracao) { this.dataExpiracao = dataExpiracao; }
}
