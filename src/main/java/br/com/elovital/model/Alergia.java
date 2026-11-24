package br.com.elovital.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alergia")
public class Alergia extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }
}
