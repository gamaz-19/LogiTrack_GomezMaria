package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.ProductoRequestDTO;
import com.s1.LogiTrack.dto.response.ProductoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.CategoriaMapper;
import com.s1.LogiTrack.mapper.ProductoMapper;
import com.s1.LogiTrack.model.Categoria;
import com.s1.LogiTrack.model.Producto;
import com.s1.LogiTrack.repository.CategoriaRepository;
import com.s1.LogiTrack.repository.ProductoRepository;
import com.s1.LogiTrack.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaMapper categoriaMapper;

    private ProductoResponseDTO toDTO(Producto p) {
        return productoMapper.entidadADTO(p, categoriaMapper.entidadADTO(p.getCategoria()));
    }

    @Override
    public ProductoResponseDTO guardarProducto(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new BusinessRuleException("Categoría no encontrada con id: " + dto.categoriaId()));
        Producto nuevo = productoMapper.DTOAEntidad(dto, categoria);
        return toDTO(productoRepository.save(nuevo));
    }

    @Override
    public ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Producto no encontrado con id: " + id));
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new BusinessRuleException("Categoría no encontrada con id: " + dto.categoriaId()));
        productoMapper.actualizarEntidadDesdeDTO(existente, dto, categoria);
        return toDTO(productoRepository.save(existente));
    }

    @Override
    public List<ProductoResponseDTO> buscarPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ProductoResponseDTO> buscarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id))
            throw new BusinessRuleException("Producto no encontrado con id: " + id);
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Producto no encontrado con id: " + id));
        return toDTO(p);
    }
}