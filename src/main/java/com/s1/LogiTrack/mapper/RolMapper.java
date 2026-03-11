package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.RolRequestDTO;
import com.s1.LogiTrack.dto.response.RolResponseDTO;
import com.s1.LogiTrack.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public RolResponseDTO entidadADTO(Rol rol){
        if(rol == null) return null;

        return new RolResponseDTO(
                rol.getId(),
                rol.getNombre()
        );
    }

    public Rol DTOAEntidad(RolRequestDTO dto){
        if(dto == null) return null;

        Rol r = new Rol();
        r.setNombre(dto.nombre());

        return r;
    }

    public void actualizarEntidadDesdeDTO(Rol r, RolRequestDTO dto){
        if(dto == null) return;

        r.setNombre(dto.nombre());
    }
}
