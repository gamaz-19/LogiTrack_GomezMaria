package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.UsuarioRequestDTO;
import com.s1.LogiTrack.dto.response.UsuarioResponseDTO;
import com.s1.LogiTrack.dto.response.PersonaResponseDTO;
import com.s1.LogiTrack.dto.response.RolResponseDTO;
import com.s1.LogiTrack.model.Usuario;
import com.s1.LogiTrack.model.Persona;
import com.s1.LogiTrack.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO entidadADTO(Usuario usuario, PersonaResponseDTO personaDTO, RolResponseDTO rolDTO){
        if(usuario == null || personaDTO == null || rolDTO == null) return null;

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombreUsuario(),
                personaDTO,
                rolDTO
        );
    }

    public Usuario DTOAEntidad(UsuarioRequestDTO dto, Persona persona, Rol rol){
        if(dto == null || persona == null || rol == null) return null;

        Usuario u = new Usuario();
        u.setNombreUsuario(dto.nombreUsuario());
        u.setPersona(persona);
        u.setRol(rol);

        return u;
    }

    public void actualizarEntidadDesdeDTO(Usuario u, UsuarioRequestDTO dto, Persona persona, Rol rol){
        if(dto == null || persona == null || rol == null) return;

        u.setNombreUsuario(dto.nombreUsuario());
        u.setPersona(persona);
        u.setRol(rol);
    }
}
