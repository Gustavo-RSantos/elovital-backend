package br.com.elovital.model;

import jakarta.persistence.*;
import br.com.elovital.model.enums.TipoPerfil;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_perfil", nullable = false, length = 20)
    private TipoPerfil tipoPerfil;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "tamanho_fonte")
    private Integer tamanhoFonte;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenhaHash() { return senhaHash; }

    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }

    public TipoPerfil getTipoPerfil() { return tipoPerfil; }

    public void setTipoPerfil(TipoPerfil tipoPerfil) { this.tipoPerfil = tipoPerfil; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Integer getTamanhoFonte() { return tamanhoFonte; }

    public void setTamanhoFonte(Integer tamanhoFonte) { this.tamanhoFonte = tamanhoFonte; }
}
