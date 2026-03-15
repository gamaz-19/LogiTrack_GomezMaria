package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.RolRequestDTO;
import com.s1.LogiTrack.dto.response.RolResponseDTO;
import com.s1.LogiTrack.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "Rol", description = "Gestión de roles")
public class RolController {

    private final RolService rolService;

    @Operation(summary = "Listar todos los roles")
    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> listar() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @Operation(summary = "Buscar rol por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.buscarPorId(id));
    }

    @Operation(summary = "Guardar un nuevo rol")
    @PostMapping
    public ResponseEntity<RolResponseDTO> guardar(@RequestBody RolRequestDTO dto) {
        return ResponseEntity.ok(rolService.guardarRol(dto));
    }

    @Operation(summary = "Actualizar un rol existente")
    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> actualizar(@RequestBody RolRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(rolService.actualizarRol(dto, id));
    }

    @Operation(summary = "Eliminar un rol por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}