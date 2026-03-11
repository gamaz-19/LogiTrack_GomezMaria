package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.model.TipoOperacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record AuditoriaRequestDTO(

        @Schema(description = "Fecha en la que se realizó la operación",
                example = "2026-03-11T10:30:00")
        LocalDateTime fecha,

        Long usuarioId,

        TipoOperacion operacion,

        String entidad,

        Long entidadId

) {
}
