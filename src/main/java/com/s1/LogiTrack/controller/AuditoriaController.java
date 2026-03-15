package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.response.AuditoriaResponseDTO;
import com.s1.LogiTrack.service.AuditoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditorias")
@RequiredArgsConstructor
@Tag(name = "Auditoria", description = "Consulta de registros de auditoría")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @Operation(summary = "Listar todas las auditorías")
    @GetMapping
    public ResponseEntity<List<AuditoriaResponseDTO>> listar() {
        return ResponseEntity.ok(auditoriaService.listarAuditorias());
    }

    @Operation(summary = "Buscar auditoría por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(auditoriaService.buscarPorId(id));
    }

    @Operation(summary = "Buscar auditorías por usuario")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AuditoriaResponseDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(auditoriaService.buscarPorUsuario(usuarioId));
    }
}