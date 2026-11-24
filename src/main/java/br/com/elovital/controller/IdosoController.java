package br.com.elovital.controller;

import br.com.elovital.dto.IdosoAtualizarRequest;
import br.com.elovital.dto.IdosoCriarRequest;
import br.com.elovital.dto.IdosoResponse;
import br.com.elovital.service.IdosoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/idosos")
public class IdosoController {

    private final IdosoService idosoService;

    public IdosoController(IdosoService idosoService) {
        this.idosoService = idosoService;
    }

    @PostMapping
    public ResponseEntity<IdosoResponse> criar(@Valid @RequestBody IdosoCriarRequest req) {
        IdosoResponse criado = idosoService.criar(req);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.id()).toUri();
        return ResponseEntity.created(location).body(criado);
    }

    @GetMapping("/{id}")
    public IdosoResponse buscar(@PathVariable Long id) {
        return idosoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public IdosoResponse atualizar(@PathVariable Long id, @Valid @RequestBody IdosoAtualizarRequest req) {
        return idosoService.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        idosoService.remover(id);
    }
}
