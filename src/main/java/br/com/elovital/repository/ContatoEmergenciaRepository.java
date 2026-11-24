package br.com.elovital.repository;

import br.com.elovital.model.ContatoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoEmergenciaRepository extends JpaRepository<ContatoEmergencia, Long> {

    List<ContatoEmergencia> findByIdosoId(Long idosoId);
    Optional<ContatoEmergencia> findByIdAndIdosoId(Long id, Long idosoId);
}
