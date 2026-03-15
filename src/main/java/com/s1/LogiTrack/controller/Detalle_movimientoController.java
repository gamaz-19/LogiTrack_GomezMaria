package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.Detalle_movimientoRequestDTO;
import com.s1.LogiTrack.dto.response.Detalle_movimientoResponseDTO;
import com.s1.LogiTrack.service.Detalle_movimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-movimiento")
@RequiredArgsConstructor
@Tag(name = "Detalle Movimiento", description = "Gestión de detalles de movimientos")
public class Detalle_movimientoController {

    private final Detalle_movimientoService detalleService;

    @Operation(summary = "Listar todos los detalles")
    @GetMapping
    public ResponseEntity<List<Detalle_movimientoResponseDTO>> listar() {
        return ResponseEntity.ok(detalleService.listarDetalles());
    }

    @Operation(summary = "Buscar detalle por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Detalle_movimientoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(detalleService.buscarPorId(id));
    }

    @Operation(summary = "Buscar detalles por movimiento")
    @GetMapping("/movimiento/{movimientoId}")
    public ResponseEntity<List<Detalle_movimientoResponseDTO>> buscarPorMovimiento(@PathVariable Long movimientoId) {
        return ResponseEntity.ok(detalleService.buscarPorMovimiento(movimientoId));
    }

    @Operation(summary = "Guardar un nuevo detalle de movimiento")
    @PostMapping
    public ResponseEntity<Detalle_movimientoResponseDTO> guardar(@RequestBody Detalle_movimientoRequestDTO dto) {
        return ResponseEntity.ok(detalleService.guardarDetalleMovimiento(dto));
    }

    @Operation(summary = "Actualizar un detalle existente")
    @PutMapping("/{id}")
    public ResponseEntity<Detalle_movimientoResponseDTO> actualizar(@RequestBody Detalle_movimientoRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(detalleService.actualizarDetalleMovimiento(dto, id));
    }

    @Operation(summary = "Eliminar un detalle por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleService.eliminarDetalleMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}