package br.com.elovital.repository;

import br.com.elovital.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByIdosoId(Long idosoId);
    Optional<Consulta> findByIdAndIdosoId(Long id, Long idosoId);
}
