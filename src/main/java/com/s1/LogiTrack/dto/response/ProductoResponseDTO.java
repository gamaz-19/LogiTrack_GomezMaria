package com.s1.LogiTrack.dto.response;

import java.math.BigDecimal;

public record ProductoResponseDTO(

        Long id,
        String nombre,
        CategoriaResponseDTO categoria,
        BigDecimal precio

) {
}
