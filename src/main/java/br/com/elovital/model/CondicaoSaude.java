package br.com.elovital.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "condicao_saude")
public class CondicaoSaude extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @Column(name = "medico_nome", length = 150)
    private String medicoNome;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "data_diagnostico")
    private LocalDate dataDiagnostico;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public String getMedicoNome() { return medicoNome; }

    public void setMedicoNome(String medicoNome) { this.medicoNome = medicoNome; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataDiagnostico() { return dataDiagnostico; }

    public void setDataDiagnostico(LocalDate dataDiagnostico) { this.dataDiagnostico = dataDiagnostico; }
}
