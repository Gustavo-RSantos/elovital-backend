package br.com.elovital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contato_emergencia")
public class ContatoEmergencia extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "parentesco", length = 50)
    private String parentesco;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getParentesco() { return parentesco; }

    public void setParentesco(String parentesco) { this.parentesco = parentesco; }
}
