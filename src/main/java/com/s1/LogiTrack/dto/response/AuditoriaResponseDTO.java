package com.s1.LogiTrack.dto.response;

import com.s1.LogiTrack.enums.TipoOperacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record AuditoriaResponseDTO(

        @Schema(description = "ID del registro de auditoria", example = "1")
        Long id,

        @Schema(description = "Fecha y hora en que se realizo la operacion")
        LocalDateTime fecha,

        @Schema(description = "Usuario que realizo la operacion")
        UsuarioResponseDTO usuario,

        @Schema(description = "Tipo de operacion realizada", example = "CREAR")
        TipoOperacion operacion,

        @Schema(description = "Nombre de la entidad afectada", example = "Producto")
        String entidad,

        @Schema(description = "ID de la entidad afectada", example = "5")
        Long entidadId

) {
}