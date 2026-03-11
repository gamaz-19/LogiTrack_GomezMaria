package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.model.Inventario;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioResponseDTO entidadADTO(
            Inventario inventario,
            BodegaResponseDTO bodegaDTO,
            ProductoResponseDTO productoDTO
    ){
        if(inventario == null || bodegaDTO == null || productoDTO == null) return null;

        return new InventarioResponseDTO(
                inventario.getId(),
                bodegaDTO,
                productoDTO,
                inventario.getStock()
        );
    }

    public Inventario DTOAEntidad(
            InventarioRequestDTO dto,
            Bodega bodega,
            Producto producto
    ){
        if(dto == null || bodega == null || producto == null) return null;

        Inventario i = new Inventario();
        i.setBodega(bodega);
        i.setProducto(producto);
        i.setStock(dto.stock());

        return i;
    }

    public void actualizarEntidadDesdeDTO(
            Inventario i,
            InventarioRequestDTO dto,
            Bodega bodega,
            Producto producto
    ){
        if(dto == null || bodega == null || producto == null) return;

        i.setBodega(bodega);
        i.setProducto(producto);
        i.setStock(dto.stock());
    }
}
