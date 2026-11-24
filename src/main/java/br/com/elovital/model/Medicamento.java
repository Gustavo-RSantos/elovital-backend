package br.com.elovital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medicamento")
public class Medicamento extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @Column(name = "medico_nome", length = 150)
    private String medicoNome;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "dosagem", length = 100)
    private String dosagem;

    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public String getMedicoNome() { return medicoNome; }

    public void setMedicoNome(String medicoNome) { this.medicoNome = medicoNome; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getDosagem() { return dosagem; }

    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public String getFotoUrl() { return fotoUrl; }

    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
}
