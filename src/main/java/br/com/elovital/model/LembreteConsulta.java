package br.com.elovital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lembrete_consulta")
public class LembreteConsulta extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @Column(name = "antecedencia_minutos", nullable = false)
    private Integer antecedenciaMinutos;

    @Column(name = "enviado", nullable = false)
    private boolean enviado;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Consulta getConsulta() { return consulta; }

    public void setConsulta(Consulta consulta) { this.consulta = consulta; }

    public Integer getAntecedenciaMinutos() { return antecedenciaMinutos; }

    public void setAntecedenciaMinutos(Integer antecedenciaMinutos) { this.antecedenciaMinutos = antecedenciaMinutos; }

    public boolean isEnviado() { return enviado; }

    public void setEnviado(boolean enviado) { this.enviado = enviado; }
}
