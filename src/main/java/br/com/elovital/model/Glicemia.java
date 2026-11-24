package br.com.elovital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "glicemia")
public class Glicemia extends EntidadeAuditavel {

    @Id
    @Column(name = "medicao_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medicao_id", nullable = false)
    private MedicaoSaude medicao;

    @Column(name = "valor_mgdl", nullable = false)
    private Integer valorMgdl;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public MedicaoSaude getMedicao() { return medicao; }

    public void setMedicao(MedicaoSaude medicao) { this.medicao = medicao; }

    public Integer getValorMgdl() { return valorMgdl; }

    public void setValorMgdl(Integer valorMgdl) { this.valorMgdl = valorMgdl; }
}
