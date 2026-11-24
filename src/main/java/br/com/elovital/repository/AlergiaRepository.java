package br.com.elovital.repository;

import br.com.elovital.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia, Long> {

    List<Alergia> findByIdosoId(Long idosoId);
    Optional<Alergia> findByIdAndIdosoId(Long id, Long idosoId);
}
