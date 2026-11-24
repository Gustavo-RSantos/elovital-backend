package br.com.elovital.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "lembrete_agua")
public class LembreteAgua extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @Column(name = "horario", nullable = false)
    private LocalTime horario;

    @Column(name = "meta_ml")
    private Integer metaMl;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public LocalTime getHorario() { return horario; }

    public void setHorario(LocalTime horario) { this.horario = horario; }

    public Integer getMetaMl() { return metaMl; }

    public void setMetaMl(Integer metaMl) { this.metaMl = metaMl; }
}
