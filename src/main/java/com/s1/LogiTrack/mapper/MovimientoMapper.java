package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.UsuarioResponseDTO;
import com.s1.LogiTrack.model.Movimiento;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

    public MovimientoResponseDTO entidadADTO(
            Movimiento movimiento,
            UsuarioResponseDTO usuarioDTO,
            BodegaResponseDTO bodegaOrigenDTO,
            BodegaResponseDTO bodegaDestinoDTO
    ){
        if(movimiento == null || usuarioDTO == null) return null;

        return new MovimientoResponseDTO(
                movimiento.getId(),
                movimiento.getFecha(),
                movimiento.getTipo(),
                usuarioDTO,
                bodegaOrigenDTO,
                bodegaDestinoDTO
        );
    }

    public Movimiento DTOAEntidad(
            MovimientoRequestDTO dto,
            Usuario usuario,
            Bodega bodegaOrigen,
            Bodega bodegaDestino
    ){
        if(dto == null || usuario == null) return null;

        Movimiento m = new Movimiento();
        m.setFecha(dto.fecha());
        m.setTipo(dto.tipo());
        m.setUsuario(usuario);
        m.setBodegaOrigen(bodegaOrigen);
        m.setBodegaDestino(bodegaDestino);

        return m;
    }

    public void actualizarEntidadDesdeDTO(
            Movimiento m,
            MovimientoRequestDTO dto,
            Usuario usuario,
            Bodega bodegaOrigen,
            Bodega bodegaDestino
    ){
        if(dto == null || usuario == null) return;

        m.setFecha(dto.fecha());
        m.setTipo(dto.tipo());
        m.setUsuario(usuario);
        m.setBodegaOrigen(bodegaOrigen);
        m.setBodegaDestino(bodegaDestino);
    }
}
