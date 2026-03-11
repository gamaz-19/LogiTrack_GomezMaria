package com.s1.LogiTrack.dto.response;

public record UsuarioResponseDTO(

        Long id,
        String nombreUsuario,
        PersonaResponseDTO persona,
        RolResponseDTO rol

) {
}
