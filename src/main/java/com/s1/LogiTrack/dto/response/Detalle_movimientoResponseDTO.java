package com.s1.LogiTrack.dto.response;

public record Detalle_movimientoResponseDTO(

        Long id,
        MovimientoResponseDTO movimiento,
        ProductoResponseDTO producto,
        Integer cantidad

) {
}
