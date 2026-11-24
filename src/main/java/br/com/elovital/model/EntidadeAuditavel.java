package br.com.elovital.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;

/**
 * Colunas comuns a todas as tabelas do DER: created_at, updated_at e sincronizado.
 */
@MappedSuperclass
public abstract class EntidadeAuditavel {

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "sincronizado", nullable = false)
    private boolean sincronizado = true;

    @PrePersist
    void aoCriar() {
        Instant agora = Instant.now();
        this.createdAt = agora;
        this.updatedAt = agora;
    }

    @PreUpdate
    void aoAtualizar() {
        this.updatedAt = Instant.now();
    }

    public Instant getCreatedAt() { return createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }

    public boolean isSincronizado() { return sincronizado; }

    public void setSincronizado(boolean sincronizado) { this.sincronizado = sincronizado; }
}
