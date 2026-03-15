package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.ProductoRequestDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@Tag(name = "Producto", description = "Gestión de productos")
public class ProductoController {

    private final ProductoService productoService;

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listar() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @Operation(summary = "Buscar producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @Operation(summary = "Buscar productos por categoría")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(categoriaId));
    }

    @Operation(summary = "Guardar un nuevo producto")
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.guardarProducto(dto));
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@RequestBody ProductoRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(productoService.actualizarProducto(dto, id));
    }

    @Operation(summary = "Eliminar un producto por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}