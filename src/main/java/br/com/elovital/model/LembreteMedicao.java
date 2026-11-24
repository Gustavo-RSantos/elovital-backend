package br.com.elovital.model;

import jakarta.persistence.*;
import br.com.elovital.model.enums.TipoMedicao;
import java.time.LocalTime;

@Entity
@Table(name = "lembrete_medicao")
public class LembreteMedicao extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoMedicao tipo;

    @Column(name = "horario", nullable = false)
    private LocalTime horario;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public TipoMedicao getTipo() { return tipo; }

    public void setTipo(TipoMedicao tipo) { this.tipo = tipo; }

    public LocalTime getHorario() { return horario; }

    public void setHorario(LocalTime horario) { this.horario = horario; }

    public boolean isAtivo() { return ativo; }

    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
