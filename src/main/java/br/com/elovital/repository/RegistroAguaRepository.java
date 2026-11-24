package br.com.elovital.repository;

import br.com.elovital.model.RegistroAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroAguaRepository extends JpaRepository<RegistroAgua, Long> {

    List<RegistroAgua> findByIdosoId(Long idosoId);
    Optional<RegistroAgua> findByIdAndIdosoId(Long id, Long idosoId);
}
