package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.InventarioRequestDTO;
import com.s1.LogiTrack.dto.response.InventarioResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.BodegaMapper;
import com.s1.LogiTrack.mapper.CategoriaMapper;
import com.s1.LogiTrack.mapper.InventarioMapper;
import com.s1.LogiTrack.mapper.PersonaMapper;
import com.s1.LogiTrack.mapper.ProductoMapper;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Inventario;
import com.s1.LogiTrack.model.Producto;
import com.s1.LogiTrack.repository.BodegaRepository;
import com.s1.LogiTrack.repository.InventarioRepository;
import com.s1.LogiTrack.repository.ProductoRepository;
import com.s1.LogiTrack.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;
    private final InventarioMapper inventarioMapper;
    private final BodegaMapper bodegaMapper;
    private final ProductoMapper productoMapper;
    private final PersonaMapper personaMapper;
    private final CategoriaMapper categoriaMapper;

    private InventarioResponseDTO toDTO(Inventario i) {
        return inventarioMapper.entidadADTO(
                i,
                bodegaMapper.entidadADTO(i.getBodega(), personaMapper.entidadADTO(i.getBodega().getEncargado())),
                productoMapper.entidadADTO(i.getProducto(), categoriaMapper.entidadADTO(i.getProducto().getCategoria()))
        );
    }

    @Override
    public InventarioResponseDTO guardarInventario(InventarioRequestDTO dto) {
        Bodega bodega = bodegaRepository.findById(dto.bodegaId())
                .orElseThrow(() -> new BusinessRuleException("Bodega no encontrada con id: " + dto.bodegaId()));
        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new BusinessRuleException("Producto no encontrado con id: " + dto.productoId()));
        Inventario nuevo = inventarioMapper.DTOAEntidad(dto, bodega, producto);
        return toDTO(inventarioRepository.save(nuevo));
    }

    @Override
    public InventarioResponseDTO actualizarInventario(InventarioRequestDTO dto, Long id) {
        Inventario existente = inventarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Inventario no encontrado con id: " + id));
        Bodega bodega = bodegaRepository.findById(dto.bodegaId())
                .orElseThrow(() -> new BusinessRuleException("Bodega no encontrada con id: " + dto.bodegaId()));
        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new BusinessRuleException("Producto no encontrado con id: " + dto.productoId()));
        inventarioMapper.actualizarEntidadDesdeDTO(existente, dto, bodega, producto);
        return toDTO(inventarioRepository.save(existente));
    }

    @Override
    public List<InventarioResponseDTO> buscarPorBodega(Long bodegaId) {
        return inventarioRepository.findByBodegaId(bodegaId)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public List<InventarioResponseDTO> buscarPorProducto(Long productoId) {
        return inventarioRepository.findByProductoId(productoId)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public void eliminarInventario(Long id) {
        if (!inventarioRepository.existsById(id))
            throw new BusinessRuleException("Inventario no encontrado con id: " + id);
        inventarioRepository.deleteById(id);
    }

    @Override
    public List<InventarioResponseDTO> listarInventarios() {
        return inventarioRepository.findAll()
                .stream().map(this::toDTO).toList();
    }

    @Override
    public InventarioResponseDTO buscarPorId(Long id) {
        Inventario i = inventarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Inventario no encontrado con id: " + id));
        return toDTO(i);
    }
}