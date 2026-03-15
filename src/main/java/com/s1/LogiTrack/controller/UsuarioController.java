package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.UsuarioRequestDTO;
import com.s1.LogiTrack.dto.response.UsuarioResponseDTO;
import com.s1.LogiTrack.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Gestión de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @Operation(summary = "Buscar usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Operation(summary = "Guardar un nuevo usuario")
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> guardar(@RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.guardarUsuario(dto));
    }

    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@RequestBody UsuarioRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(dto, id));
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}