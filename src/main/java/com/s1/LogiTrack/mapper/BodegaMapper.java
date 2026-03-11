package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.BodegaRequestDTO;
import com.s1.LogiTrack.dto.response.BodegaResponseDTO;
import com.s1.LogiTrack.dto.response.PersonaResponseDTO;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class BodegaMapper {

    public BodegaResponseDTO entidadADTO(Bodega bodega, PersonaResponseDTO dto){
        if(dto == null || bodega == null) return null;

        return new BodegaResponseDTO(
                bodega.getId(),
                bodega.getNombre(),
                bodega.getUbicacion(),
                bodega.getCapacidad(),
                dto
        );
    }

    public Bodega DTOAEntidad(BodegaRequestDTO dto, Persona persona){
        if(dto == null || persona == null) return null;

        Bodega b = new Bodega();
        b.setNombre(dto.nombre());
        b.setUbicacion(dto.ubicacion());
        b.setCapacidad(dto.capacidad());
        b.setEncargado(persona);

        return b;
    }

    public void actualizarEntidadDesdeDTO(Bodega b, BodegaRequestDTO dto, Persona persona){
        if(dto == null || persona == null) return;

        b.setNombre(dto.nombre());
        b.setUbicacion(dto.ubicacion());
        b.setCapacidad(dto.capacidad());
        b.setEncargado(persona);
    }
}
