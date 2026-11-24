package br.com.elovital.model;

import jakarta.persistence.*;
import br.com.elovital.model.enums.StatusConsulta;
import java.time.Instant;

@Entity
@Table(name = "consulta")
public class Consulta extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @Column(name = "medico_nome", length = 150)
    private String medicoNome;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criado_por_usuario_id", nullable = false)
    private Usuario criadoPor;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "consulta_origem_id", nullable = true)
    private Consulta consultaOrigem;

    @Column(name = "especialidade", length = 100)
    private String especialidade;

    @Column(name = "data_hora", nullable = false)
    private Instant dataHora;

    @Column(name = "local_atendimento", length = 255)
    private String localAtendimento;

    @Column(name = "observacoes", length = 1000)
    private String observacoes;

    @Column(name = "preparo", length = 1000)
    private String preparo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusConsulta status;

    @Column(name = "anotacao_pos_consulta", length = 4000)
    private String anotacaoPosConsulta;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public String getMedicoNome() { return medicoNome; }

    public void setMedicoNome(String medicoNome) { this.medicoNome = medicoNome; }

    public Usuario getCriadoPor() { return criadoPor; }

    public void setCriadoPor(Usuario criadoPor) { this.criadoPor = criadoPor; }

    public Consulta getConsultaOrigem() { return consultaOrigem; }

    public void setConsultaOrigem(Consulta consultaOrigem) { this.consultaOrigem = consultaOrigem; }

    public String getEspecialidade() { return especialidade; }

    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public Instant getDataHora() { return dataHora; }

    public void setDataHora(Instant dataHora) { this.dataHora = dataHora; }

    public String getLocalAtendimento() { return localAtendimento; }

    public void setLocalAtendimento(String localAtendimento) { this.localAtendimento = localAtendimento; }

    public String getObservacoes() { return observacoes; }

    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getPreparo() { return preparo; }

    public void setPreparo(String preparo) { this.preparo = preparo; }

    public StatusConsulta getStatus() { return status; }

    public void setStatus(StatusConsulta status) { this.status = status; }

    public String getAnotacaoPosConsulta() { return anotacaoPosConsulta; }

    public void setAnotacaoPosConsulta(String anotacaoPosConsulta) { this.anotacaoPosConsulta = anotacaoPosConsulta; }
}
