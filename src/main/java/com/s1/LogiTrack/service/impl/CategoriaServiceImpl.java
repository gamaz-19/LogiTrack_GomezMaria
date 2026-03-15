package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.CategoriaRequestDTO;
import com.s1.LogiTrack.dto.response.CategoriaResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.CategoriaMapper;
import com.s1.LogiTrack.model.Categoria;
import com.s1.LogiTrack.repository.CategoriaRepository;
import com.s1.LogiTrack.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaResponseDTO guardarCategoria(CategoriaRequestDTO dto) {
        Categoria nueva = categoriaMapper.DTOAEntidad(dto);
        return categoriaMapper.entidadADTO(categoriaRepository.save(nueva));
    }

    @Override
    public CategoriaResponseDTO actualizarCategoria(CategoriaRequestDTO dto, Long id) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Categoría no encontrada con id: " + id));
        categoriaMapper.actualizarEntidadDesdeDTO(existente, dto);
        return categoriaMapper.entidadADTO(categoriaRepository.save(existente));
    }

    @Override
    public List<CategoriaResponseDTO> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre)
                .stream()
                .map(categoriaMapper::entidadADTO)
                .toList();
    }

    @Override
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id))
            throw new BusinessRuleException("Categoría no encontrada con id: " + id);
        categoriaRepository.deleteById(id);
    }

    @Override
    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::entidadADTO)
                .toList();
    }

    @Override
    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria c = categoriaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Categoría no encontrada con id: " + id));
        return categoriaMapper.entidadADTO(c);
    }
}