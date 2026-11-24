package br.com.elovital.model;

import jakarta.persistence.*;
import br.com.elovital.model.enums.TipoMedicao;
import java.time.Instant;

@Entity
@Table(name = "medicao_saude")
public class MedicaoSaude extends EntidadeAuditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idoso_id", nullable = false)
    private Idoso idoso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoMedicao tipo;

    @Column(name = "data_hora", nullable = false)
    private Instant dataHora;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Idoso getIdoso() { return idoso; }

    public void setIdoso(Idoso idoso) { this.idoso = idoso; }

    public TipoMedicao getTipo() { return tipo; }

    public void setTipo(TipoMedicao tipo) { this.tipo = tipo; }

    public Instant getDataHora() { return dataHora; }

    public void setDataHora(Instant dataHora) { this.dataHora = dataHora; }
}
