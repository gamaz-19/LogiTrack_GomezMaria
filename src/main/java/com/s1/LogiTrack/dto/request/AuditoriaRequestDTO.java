package com.s1.LogiTrack.dto.request;

import com.s1.LogiTrack.enums.TipoOperacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record AuditoriaRequestDTO(

        @Schema(description = "Fecha en la que se realizo la operacion")
        LocalDateTime fecha,

        @Schema(description = "ID del usuario que realizo la operacion",
                example = "1")
        Long usuarioId,

        @Schema(description = "Tipo de operacion realizada",
                example = "CREAR")
        TipoOperacion operacion,

        @Schema(description = "Nombre de la entidad afectada",
                example = "Producto")
        String entidad,

        @Schema(description = "ID de la entidad afectada",
                example = "5")
        Long entidadId

) {
}