package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.service.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
@Tag(name = "Movimiento", description = "Gestión de movimientos de inventario")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @Operation(summary = "Listar todos los movimientos")
    @GetMapping
    public ResponseEntity<List<MovimientoResponseDTO>> listar() {
        return ResponseEntity.ok(movimientoService.listarMovimientos());
    }

    @Operation(summary = "Buscar movimiento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.buscarPorId(id));
    }

    @Operation(summary = "Guardar un nuevo movimiento")
    @PostMapping
    public ResponseEntity<MovimientoResponseDTO> guardar(@RequestBody MovimientoRequestDTO dto) {
        return ResponseEntity.ok(movimientoService.guardarMovimiento(dto));
    }

    @Operation(summary = "Actualizar un movimiento existente")
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> actualizar(@RequestBody MovimientoRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.actualizarMovimiento(dto, id));
    }

    @Operation(summary = "Eliminar un movimiento por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}