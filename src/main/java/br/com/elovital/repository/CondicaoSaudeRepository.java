package br.com.elovital.repository;

import br.com.elovital.model.CondicaoSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CondicaoSaudeRepository extends JpaRepository<CondicaoSaude, Long> {

    List<CondicaoSaude> findByIdosoId(Long idosoId);
    Optional<CondicaoSaude> findByIdAndIdosoId(Long id, Long idosoId);
}
