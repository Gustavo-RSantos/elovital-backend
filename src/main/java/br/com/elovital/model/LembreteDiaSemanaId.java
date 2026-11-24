package br.com.elovital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LembreteDiaSemanaId implements Serializable {

    @Column(name = "lembrete_medicamento_id")
    private Long lembreteMedicamentoId;

    /** 0 (domingo) a 6 (sábado). */
    @Column(name = "dia_semana")
    private Integer diaSemana;

    public LembreteDiaSemanaId() {
    }

    public LembreteDiaSemanaId(Long lembreteMedicamentoId, Integer diaSemana) {
        this.lembreteMedicamentoId = lembreteMedicamentoId;
        this.diaSemana = diaSemana;
    }

    public Long getLembreteMedicamentoId() { return lembreteMedicamentoId; }

    public void setLembreteMedicamentoId(Long lembreteMedicamentoId) { this.lembreteMedicamentoId = lembreteMedicamentoId; }

    public Integer getDiaSemana() { return diaSemana; }

    public void setDiaSemana(Integer diaSemana) { this.diaSemana = diaSemana; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LembreteDiaSemanaId outro)) return false;
        return Objects.equals(lembreteMedicamentoId, outro.lembreteMedicamentoId)
                && Objects.equals(diaSemana, outro.diaSemana);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lembreteMedicamentoId, diaSemana);
    }
}
