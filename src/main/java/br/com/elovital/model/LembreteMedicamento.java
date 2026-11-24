package br.com.elovital.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "lembrete_medicamento")
public class LembreteMedicamento extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    @Column(name = "horario", nullable = false)
    private LocalTime horario;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Medicamento getMedicamento() { return medicamento; }

    public void setMedicamento(Medicamento medicamento) { this.medicamento = medicamento; }

    public LocalTime getHorario() { return horario; }

    public void setHorario(LocalTime horario) { this.horario = horario; }

    public boolean isAtivo() { return ativo; }

    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
