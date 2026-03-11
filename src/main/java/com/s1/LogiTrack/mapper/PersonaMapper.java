package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.PersonaRequestDTO;
import com.s1.LogiTrack.dto.response.PersonaResponseDTO;
import com.s1.LogiTrack.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {

    public PersonaResponseDTO entidadADTO(Persona persona){
        if(persona == null) return null;

        return new PersonaResponseDTO(
                persona.getId(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getDocumento(),
                persona.getTelefono(),
                persona.getEmail(),
                persona.getDireccion()
        );
    }

    public Persona DTOAEntidad(PersonaRequestDTO dto){
        if(dto == null) return null;

        Persona p = new Persona();
        p.setNombre(dto.nombre());
        p.setApellido(dto.apellido());
        p.setDocumento(dto.documento());
        p.setTelefono(dto.telefono());
        p.setEmail(dto.email());
        p.setDireccion(dto.direccion());

        return p;
    }

    public void actualizarEntidadDesdeDTO(Persona p, PersonaRequestDTO dto){
        if(dto == null) return;

        p.setNombre(dto.nombre());
        p.setApellido(dto.apellido());
        p.setDocumento(dto.documento());
        p.setTelefono(dto.telefono());
        p.setEmail(dto.email());
        p.setDireccion(dto.direccion());
    }
}
