package com.s1.LogiTrack.controller;

import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.PersonaResponseDTO;
import com.s1.LogiTrack.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
@Tag(name = "Persona", description = "Gestión de personas")
public class PersonaController {

    private final PersonaService personaService;

    @Operation(summary = "Listar todas las personas")
    @GetMapping
    public ResponseEntity<List<PersonaResponseDTO>> listar() {
        return ResponseEntity.ok(personaService.listarPersonas());
    }

    @Operation(summary = "Buscar persona por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.buscarPorId(id));
    }

    @Operation(summary = "Guardar una nueva persona")
    @PostMapping
    public ResponseEntity<PersonaResponseDTO> guardar(@RequestBody PersonaRequestDTO dto) {
        return ResponseEntity.ok(personaService.guardarPersona(dto));
    }

    @Operation(summary = "Actualizar una persona existente")
    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> actualizar(@RequestBody PersonaRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(personaService.actualizarPersona(dto, id));
    }

    @Operation(summary = "Eliminar una persona por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}