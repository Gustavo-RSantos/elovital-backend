package br.com.elovital.model;

import jakarta.persistence.*;
import br.com.elovital.model.enums.StatusRegistroMedicamento;
import java.time.Instant;

@Entity
@Table(name = "registro_medicamento")
public class RegistroMedicamento extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "lembrete_medicamento_id", nullable = true)
    private LembreteMedicamento lembreteMedicamento;

    @Column(name = "data_hora", nullable = false)
    private Instant dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusRegistroMedicamento status;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Medicamento getMedicamento() { return medicamento; }

    public void setMedicamento(Medicamento medicamento) { this.medicamento = medicamento; }

    public LembreteMedicamento getLembreteMedicamento() { return lembreteMedicamento; }

    public void setLembreteMedicamento(LembreteMedicamento lembreteMedicamento) { this.lembreteMedicamento = lembreteMedicamento; }

    public Instant getDataHora() { return dataHora; }

    public void setDataHora(Instant dataHora) { this.dataHora = dataHora; }

    public StatusRegistroMedicamento getStatus() { return status; }

    public void setStatus(StatusRegistroMedicamento status) { this.status = status; }
}
