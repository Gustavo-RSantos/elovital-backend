package br.com.elovital.repository;

import br.com.elovital.model.RegistroMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroMedicamentoRepository extends JpaRepository<RegistroMedicamento, Long> {

    List<RegistroMedicamento> findByMedicamentoId(Long medicamentoId);
}
