package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.Detalle_movimientoRequestDTO;
import com.s1.LogiTrack.dto.response.Detalle_movimientoResponseDTO;
import com.s1.LogiTrack.dto.response.Detalle_movimientoResponseDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.model.Detalle_movimiento;
import com.s1.LogiTrack.model.Movimiento;
import com.s1.LogiTrack.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class Detalle_movimientoMapper {

    public Detalle_movimientoResponseDTO entidadADTO(
            Detalle_movimiento detalle,
            MovimientoResponseDTO movimientoDTO,
            ProductoResponseDTO productoDTO
    ){
        if(detalle == null || movimientoDTO == null || productoDTO == null) return null;

        return new Detalle_movimientoResponseDTO(
                detalle.getId(),
                movimientoDTO,
                productoDTO,
                detalle.getCantidad()
        );
    }

    public Detalle_movimiento DTOAEntidad(
            Detalle_movimientoRequestDTO dto,
            Movimiento movimiento,
            Producto producto
    ){
        if(dto == null || movimiento == null || producto == null) return null;

        Detalle_movimiento d = new Detalle_movimiento();
        d.setMovimiento(movimiento);
        d.setProducto(producto);
        d.setCantidad(dto.cantidad());

        return d;
    }

    public void actualizarEntidadDesdeDTO(
            Detalle_movimiento d,
            Detalle_movimientoRequestDTO dto,
            Movimiento movimiento,
            Producto producto
    ){
        if(dto == null || movimiento == null || producto == null) return;

        d.setMovimiento(movimiento);
        d.setProducto(producto);
        d.setCantidad(dto.cantidad());
    }
}
