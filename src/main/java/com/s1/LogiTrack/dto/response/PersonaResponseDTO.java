package com.s1.LogiTrack.dto.response;

public record PersonaResponseDTO(

        Long id,
        String nombre,
        String apellido,
        String documento,
        String telefono,
        String email,
        String direccion

) {
}
