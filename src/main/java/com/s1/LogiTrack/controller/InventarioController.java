package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;
import com.s1.LogiTrack.service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventarios")
@RequiredArgsConstructor
@Tag(name = "Inventario", description = "Gestión de inventarios")
public class InventarioController {

    private final InventarioService inventarioService;

    @Operation(summary = "Listar todos los inventarios")
    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listar() {
        return ResponseEntity.ok(inventarioService.listarInventarios());
    }

    @Operation(summary = "Buscar inventario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.buscarPorId(id));
    }

    @Operation(summary = "Buscar inventarios por bodega")
    @GetMapping("/bodega/{bodegaId}")
    public ResponseEntity<List<InventarioResponseDTO>> buscarPorBodega(@PathVariable Long bodegaId) {
        return ResponseEntity.ok(inventarioService.buscarPorBodega(bodegaId));
    }

    @Operation(summary = "Guardar un nuevo inventario")
    @PostMapping
    public ResponseEntity<InventarioResponseDTO> guardar(@RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.ok(inventarioService.guardarInventario(dto));
    }

    @Operation(summary = "Actualizar un inventario existente")
    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizar(@RequestBody InventarioRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.actualizarInventario(dto, id));
    }

    @Operation(summary = "Eliminar un inventario por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }
}