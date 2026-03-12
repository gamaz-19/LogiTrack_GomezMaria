package com.s1.LogiTrack.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record BodegaRequestDTO(

        @Schema(description = "Nombre de la bodega",
                example = "Bodega Central")
        String nombre,

        @Schema(description = "Ubicacion de la bodega",
                example = "Bogota")
        String ubicacion,

        @Schema(description = "Capacidad maxima de almacenamiento de la bodega",
                example = "5000")
        Long capacidad,

        @Schema(description = "ID del empleado encargado de la bodega",
                example = "3")
        Long encargadoId

) {
}