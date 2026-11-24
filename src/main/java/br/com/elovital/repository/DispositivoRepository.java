package br.com.elovital.repository;

import br.com.elovital.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    List<Dispositivo> findByUsuarioId(Long usuarioId);
    Optional<Dispositivo> findByTokenPush(String tokenPush);
}
