package br.com.elovital.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "registro_agua")
public class RegistroAgua extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "lembrete_agua_id", nullable = true)
    private LembreteAgua lembreteAgua;

    @Column(name = "data_hora", nullable = false)
    private Instant dataHora;

    @Column(name = "quantidade_ml", nullable = false)
    private Integer quantidadeMl;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public LembreteAgua getLembreteAgua() { return lembreteAgua; }

    public void setLembreteAgua(LembreteAgua lembreteAgua) { this.lembreteAgua = lembreteAgua; }

    public Instant getDataHora() { return dataHora; }

    public void setDataHora(Instant dataHora) { this.dataHora = dataHora; }

    public Integer getQuantidadeMl() { return quantidadeMl; }

    public void setQuantidadeMl(Integer quantidadeMl) { this.quantidadeMl = quantidadeMl; }
}
