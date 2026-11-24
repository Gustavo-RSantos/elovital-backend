package br.com.elovital.repository;

import br.com.elovital.model.LembreteConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LembreteConsultaRepository extends JpaRepository<LembreteConsulta, Long> {

    List<LembreteConsulta> findByConsultaId(Long consultaId);
}
