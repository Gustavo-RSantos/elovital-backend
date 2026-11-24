package br.com.elovital.repository;

import br.com.elovital.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    List<Medicamento> findByIdosoId(Long idosoId);
    Optional<Medicamento> findByIdAndIdosoId(Long id, Long idosoId);
}
