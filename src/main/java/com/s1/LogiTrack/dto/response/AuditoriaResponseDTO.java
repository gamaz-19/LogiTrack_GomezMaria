package com.s1.LogiTrack.dto.response;

import com.s1.LogiTrack.enums.TipoOperacion;

import java.time.LocalDateTime;

public record AuditoriaResponseDTO(

        Long id,
        LocalDateTime fecha,
        UsuarioResponseDTO usuario,
        TipoOperacion operacion,
        String entidad,
        Long entidadId

) {
}
