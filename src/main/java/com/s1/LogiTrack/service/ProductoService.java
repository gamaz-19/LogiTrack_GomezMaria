package com.s1.LogiTrack.service;

import com.s1.LogiTrack.dto.request.ProductoRequestDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

    ProductoResponseDTO guardarProducto(ProductoRequestDTO dto);

    ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id);

    List<ProductoResponseDTO> buscarPorNombre(String nombre);

    List<ProductoResponseDTO> buscarPorCategoria(Long categoriaId);

    void eliminarProducto(Long id);

    List<ProductoResponseDTO> listarProductos();

    ProductoResponseDTO buscarPorId(Long id);

}
