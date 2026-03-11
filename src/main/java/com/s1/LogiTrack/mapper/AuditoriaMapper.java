package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.AuditoriaRequestDTO;
import com.s1.LogiTrack.dto.response.AuditoriaResponseDTO;
import com.s1.LogiTrack.dto.response.UsuarioResponseDTO;
import com.s1.LogiTrack.model.Auditoria;
import com.s1.LogiTrack.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AuditoriaMapper {

    public AuditoriaResponseDTO entidadADTO(Auditoria auditoria, UsuarioResponseDTO dto){
        if(dto == null || auditoria == null) return null;

        return new AuditoriaResponseDTO(
                auditoria.getId(),
                auditoria.getFecha(),
                dto,
                auditoria.getOperacion(),
                auditoria.getEntidad(),
                auditoria.getEntidadId()
        );
    }

    public Auditoria DTOAEntidad(AuditoriaRequestDTO dto, Usuario usuario){
        if(dto == null || usuario == null) return null;

        Auditoria a = new Auditoria();
        a.setFecha(dto.fecha());
        a.setUsuario(usuario);
        a.setOperacion(dto.operacion());
        a.setEntidad(dto.entidad());
        a.setEntidadId(dto.entidadId());

        return a;
    }

    public void actualizarEntidadDesdeDTO(Auditoria a, AuditoriaRequestDTO dto, Usuario usuario){
        if(dto == null || usuario == null) return;

        a.setFecha(dto.fecha());
        a.setUsuario(usuario);
        a.setOperacion(dto.operacion());
        a.setEntidad(dto.entidad());
        a.setEntidadId(dto.entidadId());
    }
}
