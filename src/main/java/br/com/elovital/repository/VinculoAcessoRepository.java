package br.com.elovital.repository;

import br.com.elovital.model.VinculoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VinculoAcessoRepository extends JpaRepository<VinculoAcesso, Long> {

    List<VinculoAcesso> findByIdosoId(Long idosoId);
    List<VinculoAcesso> findByUsuarioId(Long usuarioId);
    Optional<VinculoAcesso> findByIdosoIdAndUsuarioId(Long idosoId, Long usuarioId);
    boolean existsByIdosoIdAndUsuarioId(Long idosoId, Long usuarioId);
}
