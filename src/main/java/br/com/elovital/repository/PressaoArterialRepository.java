package br.com.elovital.repository;

import br.com.elovital.model.PressaoArterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PressaoArterialRepository extends JpaRepository<PressaoArterial, Long> {
}
