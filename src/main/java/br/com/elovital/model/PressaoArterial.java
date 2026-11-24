package br.com.elovital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pressao_arterial")
public class PressaoArterial extends EntidadeAuditavel {

    @Id
    @Column(name = "medicao_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medicao_id", nullable = false)
    private MedicaoSaude medicao;

    @Column(name = "sistolica_mmhg", nullable = false)
    private Integer sistolicaMmhg;

    @Column(name = "diastolica_mmhg", nullable = false)
    private Integer diastolicaMmhg;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public MedicaoSaude getMedicao() { return medicao; }

    public void setMedicao(MedicaoSaude medicao) { this.medicao = medicao; }

    public Integer getSistolicaMmhg() { return sistolicaMmhg; }

    public void setSistolicaMmhg(Integer sistolicaMmhg) { this.sistolicaMmhg = sistolicaMmhg; }

    public Integer getDiastolicaMmhg() { return diastolicaMmhg; }

    public void setDiastolicaMmhg(Integer diastolicaMmhg) { this.diastolicaMmhg = diastolicaMmhg; }
}
