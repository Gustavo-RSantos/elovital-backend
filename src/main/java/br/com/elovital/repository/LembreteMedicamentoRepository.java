package br.com.elovital.repository;

import br.com.elovital.model.LembreteMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LembreteMedicamentoRepository extends JpaRepository<LembreteMedicamento, Long> {

    List<LembreteMedicamento> findByMedicamentoId(Long medicamentoId);
}
