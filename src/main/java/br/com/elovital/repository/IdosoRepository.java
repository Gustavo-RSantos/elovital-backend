package br.com.elovital.repository;

import br.com.elovital.model.Idoso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdosoRepository extends JpaRepository<Idoso, Long> {

    Optional<Idoso> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioId(Long usuarioId);
}
