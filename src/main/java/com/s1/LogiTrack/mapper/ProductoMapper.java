package com.s1.LogiTrack.mapper;

import com.s1.LogiTrack.dto.request.ProductoRequestDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.dto.response.CategoriaResponseDTO;
import com.s1.LogiTrack.model.Producto;
import com.s1.LogiTrack.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponseDTO entidadADTO(Producto producto, CategoriaResponseDTO categoriaDTO){
        if(producto == null || categoriaDTO == null) return null;

        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                categoriaDTO,
                producto.getPrecio()
        );
    }

    public Producto DTOAEntidad(ProductoRequestDTO dto, Categoria categoria){
        if(dto == null || categoria == null) return null;

        Producto p = new Producto();
        p.setNombre(dto.nombre());
        p.setCategoria(categoria);
        p.setPrecio(dto.precio());

        return p;
    }

    public void actualizarEntidadDesdeDTO(Producto p, ProductoRequestDTO dto, Categoria categoria){
        if(dto == null || categoria == null) return;

        p.setNombre(dto.nombre());
        p.setCategoria(categoria);
        p.setPrecio(dto.precio());
    }
}
