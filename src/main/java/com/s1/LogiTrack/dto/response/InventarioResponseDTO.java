package com.s1.LogiTrack.dto.response;

public record InventarioResponseDTO(

        Long id,
        BodegaResponseDTO bodega,
        ProductoResponseDTO producto,
        Long stock

) {
}
