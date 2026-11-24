package br.com.elovital.repository;

import br.com.elovital.model.LembreteMedicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LembreteMedicaoRepository extends JpaRepository<LembreteMedicao, Long> {

    List<LembreteMedicao> findByIdosoId(Long idosoId);
    Optional<LembreteMedicao> findByIdAndIdosoId(Long id, Long idosoId);
}
