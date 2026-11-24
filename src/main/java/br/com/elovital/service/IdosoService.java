package br.com.elovital.service;

import br.com.elovital.dto.IdosoAtualizarRequest;
import br.com.elovital.dto.IdosoCriarRequest;
import br.com.elovital.dto.IdosoResponse;
import br.com.elovital.exception.ConflitoException;
import br.com.elovital.exception.RecursoNaoEncontradoException;
import br.com.elovital.exception.RegraNegocioException;
import br.com.elovital.model.Idoso;
import br.com.elovital.model.Usuario;
import br.com.elovital.model.enums.TipoPerfil;
import br.com.elovital.repository.IdosoRepository;
import br.com.elovital.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IdosoService {

    private final IdosoRepository idosoRepository;
    private final UsuarioRepository usuarioRepository;

    public IdosoService(IdosoRepository idosoRepository, UsuarioRepository usuarioRepository) {
        this.idosoRepository = idosoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public IdosoResponse criar(IdosoCriarRequest req) {
        Usuario usuario = usuarioRepository.findById(req.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", req.usuarioId()));

        if (usuario.getTipoPerfil() != TipoPerfil.IDOSO) {
            throw new RegraNegocioException("O usuário informado não possui o perfil IDOSO.");
        }
        if (idosoRepository.existsByUsuarioId(usuario.getId())) {
            throw new ConflitoException("Este usuário já possui um perfil de idoso.");
        }

        Idoso idoso = new Idoso();
        idoso.setUsuario(usuario);
        idoso.setDataNascimento(req.dataNascimento());
        idoso.setTipoSanguineo(req.tipoSanguineo());
        return IdosoResponse.de(idosoRepository.save(idoso));
    }

    @Transactional(readOnly = true)
    public IdosoResponse buscarPorId(Long id) {
        return IdosoResponse.de(obterOuFalhar(id));
    }

    public IdosoResponse atualizar(Long id, IdosoAtualizarRequest req) {
        Idoso idoso = obterOuFalhar(id);
        idoso.setDataNascimento(req.dataNascimento());
        idoso.setTipoSanguineo(req.tipoSanguineo());
        return IdosoResponse.de(idoso);
    }

    public void remover(Long id) {
        idosoRepository.delete(obterOuFalhar(id));
    }

    private Idoso obterOuFalhar(Long id) {
        return idosoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Idoso", id));
    }
}
