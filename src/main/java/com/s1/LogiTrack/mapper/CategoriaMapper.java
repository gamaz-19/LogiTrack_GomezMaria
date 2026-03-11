package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.CategoriaRequestDTO;
import com.s1.LogiTrack.dto.response.CategoriaResponseDTO;
import com.s1.LogiTrack.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaResponseDTO entidadADTO(Categoria categoria){
        if(categoria == null) return null;

        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre()
        );
    }

    public Categoria DTOAEntidad(CategoriaRequestDTO dto){
        if(dto == null) return null;

        Categoria c = new Categoria();
        c.setNombre(dto.nombre());

        return c;
    }

    public void actualizarEntidadDesdeDTO(Categoria c, CategoriaRequestDTO dto){
        if(dto == null) return;

        c.setNombre(dto.nombre());
    }
}
