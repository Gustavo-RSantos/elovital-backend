package br.com.elovital.repository;

import br.com.elovital.model.MedicaoSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicaoSaudeRepository extends JpaRepository<MedicaoSaude, Long> {

    List<MedicaoSaude> findByIdosoId(Long idosoId);
    Optional<MedicaoSaude> findByIdAndIdosoId(Long id, Long idosoId);
}
