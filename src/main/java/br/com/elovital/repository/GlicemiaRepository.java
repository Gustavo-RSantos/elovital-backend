package br.com.elovital.repository;

import br.com.elovital.model.Glicemia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlicemiaRepository extends JpaRepository<Glicemia, Long> {
}
