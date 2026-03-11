package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record BodegaRequestDTO(

        @Schema(description = "Nombre de la bodega",
                example = "Bodega Central")
        String nombre,

        @Schema(description = "Ubicación de la bodega",
                example = "Bogotá")
        String ubicacion,

        @Schema(description = "Capacidad máxima de almacenamiento",
                example = "5000")
        Long capacidad,

        Long encargadoId

) {
}
