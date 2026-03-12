package com.s1.LogiTrack.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record BodegaResponseDTO(

        @Schema(description = "ID de la bodega", example = "1")
        Long id,

        @Schema(description = "Nombre de la bodega", example = "Bodega Central")
        String nombre,

        @Schema(description = "Ubicacion de la bodega", example = "Bogotá - Zona Industrial")
        String ubicacion,

        @Schema(description = "Capacidad maxima de almacenamiento de la bodega", example = "5000")
        Long capacidad,

        @Schema(description = "Persona encargada de la bodega")
        PersonaResponseDTO encargado

) {
}