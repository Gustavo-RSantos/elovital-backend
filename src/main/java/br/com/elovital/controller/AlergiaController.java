package br.com.elovital.controller;

import br.com.elovital.dto.AlergiaRequest;
import br.com.elovital.dto.AlergiaResponse;
import br.com.elovital.service.AlergiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/idosos/{idosoId}/alergias")
public class AlergiaController {

    private final AlergiaService alergiaService;

    public AlergiaController(AlergiaService alergiaService) {
        this.alergiaService = alergiaService;
    }

    @GetMapping
    public List<AlergiaResponse> listar(@PathVariable Long idosoId) {
        return alergiaService.listar(idosoId);
    }

    @GetMapping("/{id}")
    public AlergiaResponse buscar(@PathVariable Long idosoId, @PathVariable Long id) {
        return alergiaService.buscar(idosoId, id);
    }

    @PostMapping
    public ResponseEntity<AlergiaResponse> criar(@PathVariable Long idosoId, @Valid @RequestBody AlergiaRequest req) {
        AlergiaResponse criada = alergiaService.criar(idosoId, req);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criada.id()).toUri();
        return ResponseEntity.created(location).body(criada);
    }

    @PutMapping("/{id}")
    public AlergiaResponse atualizar(@PathVariable Long idosoId, @PathVariable Long id,
                                     @Valid @RequestBody AlergiaRequest req) {
        return alergiaService.atualizar(idosoId, id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long idosoId, @PathVariable Long id) {
        alergiaService.remover(idosoId, id);
    }
}
