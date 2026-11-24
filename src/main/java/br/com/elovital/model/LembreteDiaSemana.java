package br.com.elovital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lembrete_dia_semana")
public class LembreteDiaSemana extends EntidadeAuditavel {

    @EmbeddedId
    private LembreteDiaSemanaId id;

    @MapsId("lembreteMedicamentoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lembrete_medicamento_id", nullable = false)
    private LembreteMedicamento lembreteMedicamento;

    public LembreteDiaSemanaId getId() { return id; }

    public void setId(LembreteDiaSemanaId id) { this.id = id; }

    public LembreteMedicamento getLembreteMedicamento() { return lembreteMedicamento; }

    public void setLembreteMedicamento(LembreteMedicamento lembreteMedicamento) { this.lembreteMedicamento = lembreteMedicamento; }
}
