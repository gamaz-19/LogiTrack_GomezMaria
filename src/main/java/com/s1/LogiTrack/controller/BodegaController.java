package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.BodegaRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.service.BodegaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodegas")
@RequiredArgsConstructor
@Tag(name = "Bodega", description = "Gestión de bodegas")
public class BodegaController {

    private final BodegaService bodegaService;

    @Operation(summary = "Listar todas las bodegas")
    @GetMapping
    public ResponseEntity<List<BodegaResponseDTO>> listar() {
        return ResponseEntity.ok(bodegaService.listarBodegas());
    }

    @Operation(summary = "Buscar bodega por ID")
    @GetMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.buscarPorId(id));
    }

    @Operation(summary = "Guardar una nueva bodega")
    @PostMapping
    public ResponseEntity<BodegaResponseDTO> guardar(@RequestBody BodegaRequestDTO dto) {
        return ResponseEntity.ok(bodegaService.guardarBodega(dto));
    }

    @Operation(summary = "Actualizar una bodega existente")
    @PutMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> actualizar(@RequestBody BodegaRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.actualizarBodega(dto, id));
    }

    @Operation(summary = "Eliminar una bodega por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bodegaService.eliminarBodega(id);
        return ResponseEntity.noContent().build();
    }
}