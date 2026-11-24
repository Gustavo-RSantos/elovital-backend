package br.com.elovital.repository;

import br.com.elovital.model.LembreteAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LembreteAguaRepository extends JpaRepository<LembreteAgua, Long> {

    List<LembreteAgua> findByIdosoId(Long idosoId);
    Optional<LembreteAgua> findByIdAndIdosoId(Long id, Long idosoId);
}
