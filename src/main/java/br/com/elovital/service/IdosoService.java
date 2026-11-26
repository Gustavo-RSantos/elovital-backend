package br.com.elovital.service;

import br.com.elovital.dto.IdosoAtualizarRequest;
import br.com.elovital.dto.IdosoCriarRequest;
import br.com.elovital.dto.IdosoResponse;
import br.com.elovital.dto.IdosoResumoResponse;
import br.com.elovital.exception.AcessoNegadoException;
import br.com.elovital.exception.ConflitoException;
import br.com.elovital.exception.RecursoNaoEncontradoException;
import br.com.elovital.exception.RegraNegocioException;
import br.com.elovital.model.Idoso;
import br.com.elovital.model.Usuario;
import br.com.elovital.model.VinculoAcesso;
import br.com.elovital.model.enums.StatusVinculo;
import br.com.elovital.model.enums.TipoRelacao;
import br.com.elovital.model.enums.TipoPerfil;
import br.com.elovital.repository.IdosoRepository;
import br.com.elovital.repository.UsuarioRepository;
import br.com.elovital.repository.VinculoAcessoRepository;
import br.com.elovital.security.AcessoIdosoInterceptor;
import br.com.elovital.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IdosoService {

    private final IdosoRepository idosoRepository;
    private final UsuarioRepository usuarioRepository;
    private final VinculoAcessoRepository vinculoAcessoRepository;

    public IdosoService(IdosoRepository idosoRepository,
                        UsuarioRepository usuarioRepository,
                        VinculoAcessoRepository vinculoAcessoRepository) {
        this.idosoRepository = idosoRepository;
        this.usuarioRepository = usuarioRepository;
        this.vinculoAcessoRepository = vinculoAcessoRepository;
    }

    public IdosoResponse criar(IdosoCriarRequest req) {
        Long autenticadoId = SecurityUtils.usuarioIdAtual();
        Usuario autenticado = usuarioRepository.findById(autenticadoId)
                .orElseThrow(() -> new AcessoNegadoException("Usuário autenticado não encontrado."));
        if (autenticado.getTipoPerfil() == TipoPerfil.IDOSO && !autenticadoId.equals(req.usuarioId())) {
            throw new AcessoNegadoException("Um idoso só pode criar o próprio perfil.");
        }

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
        Idoso salvo = idosoRepository.save(idoso);

        // Familiar/cuidador que cadastra o idoso passa a acompanhá-lo (sem isso ele não conseguiria acessá-lo).
        if (!autenticadoId.equals(usuario.getId())) {
            VinculoAcesso vinculo = new VinculoAcesso();
            vinculo.setIdoso(salvo);
            vinculo.setUsuario(autenticado);
            vinculo.setTipoRelacao(autenticado.getTipoPerfil() == TipoPerfil.FAMILIAR
                    ? TipoRelacao.FAMILIAR : TipoRelacao.PROFISSIONAL);
            vinculo.setStatus(StatusVinculo.ATIVO);
            vinculoAcessoRepository.save(vinculo);
        }
        return IdosoResponse.de(salvo);
    }

    @Transactional(readOnly = true)
    public List<IdosoResumoResponse> listar(Long usuarioId) {
        if (usuarioId == null) {
            return idosoRepository.findAll().stream()
                    .map(IdosoResumoResponse::de)
                    .toList();
        }

        Map<Long, Idoso> acessiveis = new LinkedHashMap<>();
        idosoRepository.findByUsuarioId(usuarioId)
                .ifPresent(i -> acessiveis.put(i.getId(), i));

        List<VinculoAcesso> vinculos = vinculoAcessoRepository.findByUsuarioId(usuarioId);
        for (VinculoAcesso v : vinculos) {
            if (AcessoIdosoInterceptor.vigente(v)) {
                acessiveis.put(v.getIdoso().getId(), v.getIdoso());
            }
        }

        return acessiveis.values().stream()
                .map(IdosoResumoResponse::de)
                .toList();
    }

    @Transactional(readOnly = true)
    public IdosoResponse buscarPorId(Long id) {
        return IdosoResponse.de(obterOuFalhar(id));
    }

    @Transactional(readOnly = true)
    public IdosoResumoResponse buscarResumoPorId(Long id) {
        return IdosoResumoResponse.de(obterOuFalhar(id));
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

    public Idoso obterOuFalhar(Long id) {
        return idosoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Idoso", id));
    }
}
