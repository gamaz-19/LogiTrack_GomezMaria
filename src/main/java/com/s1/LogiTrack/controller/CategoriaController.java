package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.CategoriaRequestDTO;
import com.s1.LogiTrack.dto.response.CategoriaResponseDTO;
import com.s1.LogiTrack.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name = "Categoria", description = "Gestión de categorías")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Listar todas las categorías")
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @Operation(summary = "Buscar categoría por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @Operation(summary = "Guardar una nueva categoría")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> guardar(@RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.guardarCategoria(dto));
    }

    @Operation(summary = "Actualizar una categoría existente")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizar(@RequestBody CategoriaRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(dto, id));
    }

    @Operation(summary = "Eliminar una categoría por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}