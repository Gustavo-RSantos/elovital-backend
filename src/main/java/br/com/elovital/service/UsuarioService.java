package br.com.elovital.service;

import br.com.elovital.dto.UsuarioAtualizarRequest;
import br.com.elovital.dto.UsuarioCriarRequest;
import br.com.elovital.dto.UsuarioResponse;
import br.com.elovital.exception.ConflitoException;
import br.com.elovital.exception.RecursoNaoEncontradoException;
import br.com.elovital.model.Usuario;
import br.com.elovital.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse criar(UsuarioCriarRequest req) {
        String email = req.email().trim().toLowerCase(Locale.ROOT);
        if (usuarioRepository.existsByEmail(email)) {
            throw new ConflitoException("Já existe um usuário cadastrado com o e-mail informado.");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(req.nome().trim());
        usuario.setEmail(email);
        usuario.setSenhaHash(passwordEncoder.encode(req.senha()));
        usuario.setTipoPerfil(req.tipoPerfil());
        usuario.setTelefone(req.telefone());
        usuario.setTamanhoFonte(req.tamanhoFonte());
        return UsuarioResponse.de(usuarioRepository.save(usuario));
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        return UsuarioResponse.de(obterOuFalhar(id));
    }

    public UsuarioResponse atualizar(Long id, UsuarioAtualizarRequest req) {
        Usuario usuario = obterOuFalhar(id);
        usuario.setNome(req.nome().trim());
        usuario.setTelefone(req.telefone());
        usuario.setTamanhoFonte(req.tamanhoFonte());
        return UsuarioResponse.de(usuario); // entidade gerenciada: o flush acontece no commit
    }

    public void remover(Long id) {
        usuarioRepository.delete(obterOuFalhar(id));
    }

    private Usuario obterOuFalhar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", id));
    }
}
