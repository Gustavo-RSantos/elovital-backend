package br.com.elovital.service;

import br.com.elovital.dto.AlergiaRequest;
import br.com.elovital.dto.AlergiaResponse;
import br.com.elovital.exception.RecursoNaoEncontradoException;
import br.com.elovital.model.Alergia;
import br.com.elovital.model.Idoso;
import br.com.elovital.repository.AlergiaRepository;
import br.com.elovital.repository.IdosoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Modelo para os recursos "filhos de idoso" (contato de emergência, condição de saúde, etc.):
 * toda operação recebe o idosoId e garante que o registro pertence a ele.
 */
@Service
@Transactional
public class AlergiaService {

    private final AlergiaRepository alergiaRepository;
    private final IdosoRepository idosoRepository;

    public AlergiaService(AlergiaRepository alergiaRepository, IdosoRepository idosoRepository) {
        this.alergiaRepository = alergiaRepository;
        this.idosoRepository = idosoRepository;
    }

    @Transactional(readOnly = true)
    public List<AlergiaResponse> listar(Long idosoId) {
        garantirIdosoExiste(idosoId);
        return alergiaRepository.findByIdosoId(idosoId).stream().map(AlergiaResponse::de).toList();
    }

    @Transactional(readOnly = true)
    public AlergiaResponse buscar(Long idosoId, Long id) {
        return AlergiaResponse.de(obterOuFalhar(idosoId, id));
    }

    public AlergiaResponse criar(Long idosoId, AlergiaRequest req) {
        Idoso idoso = idosoRepository.findById(idosoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Idoso", idosoId));
        Alergia alergia = new Alergia();
        alergia.setIdoso(idoso);
        alergia.setDescricao(req.descricao().trim());
        return AlergiaResponse.de(alergiaRepository.save(alergia));
    }

    public AlergiaResponse atualizar(Long idosoId, Long id, AlergiaRequest req) {
        Alergia alergia = obterOuFalhar(idosoId, id);
        alergia.setDescricao(req.descricao().trim());
        return AlergiaResponse.de(alergia);
    }

    public void remover(Long idosoId, Long id) {
        alergiaRepository.delete(obterOuFalhar(idosoId, id));
    }

    private void garantirIdosoExiste(Long idosoId) {
        if (!idosoRepository.existsById(idosoId)) {
            throw new RecursoNaoEncontradoException("Idoso", idosoId);
        }
    }

    private Alergia obterOuFalhar(Long idosoId, Long id) {
        return alergiaRepository.findByIdAndIdosoId(id, idosoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Alergia", id));
    }
}
