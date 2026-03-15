package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.Detalle_movimientoRequestDTO;
import com.s1.LogiTrack.dto.response.Detalle_movimientoResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.*;
import com.s1.LogiTrack.model.Detalle_movimiento;
import com.s1.LogiTrack.model.Movimiento;
import com.s1.LogiTrack.model.Producto;
import com.s1.LogiTrack.repository.Detalle_movimientoRepository;
import com.s1.LogiTrack.repository.MovimientoRepository;
import com.s1.LogiTrack.repository.ProductoRepository;
import com.s1.LogiTrack.service.Detalle_movimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Detalle_movimientoServiceImpl implements Detalle_movimientoService {

    private final Detalle_movimientoRepository detalleRepository;
    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;
    private final Detalle_movimientoMapper detalleMapper;
    private final MovimientoMapper movimientoMapper;
    private final ProductoMapper productoMapper;
    private final UsuarioMapper usuarioMapper;
    private final BodegaMapper bodegaMapper;
    private final PersonaMapper personaMapper;
    private final RolMapper rolMapper;
    private final CategoriaMapper categoriaMapper;

    private Detalle_movimientoResponseDTO toDTO(Detalle_movimiento d) {
        Movimiento m = d.getMovimiento();
        Producto p = d.getProducto();
        return detalleMapper.entidadADTO(
                d,
                movimientoMapper.entidadADTO(
                        m,
                        usuarioMapper.entidadADTO(
                                m.getUsuario(),
                                personaMapper.entidadADTO(m.getUsuario().getPersona()),
                                rolMapper.entidadADTO(m.getUsuario().getRol())
                        ),
                        m.getBodegaOrigen() != null
                                ? bodegaMapper.entidadADTO(m.getBodegaOrigen(), personaMapper.entidadADTO(m.getBodegaOrigen().getEncargado()))
                                : null,
                        m.getBodegaDestino() != null
                                ? bodegaMapper.entidadADTO(m.getBodegaDestino(), personaMapper.entidadADTO(m.getBodegaDestino().getEncargado()))
                                : null
                ),
                productoMapper.entidadADTO(p, categoriaMapper.entidadADTO(p.getCategoria()))
        );
    }

    @Override
    public Detalle_movimientoResponseDTO guardarDetalleMovimiento(Detalle_movimientoRequestDTO dto) {
        Movimiento movimiento = movimientoRepository.findById(dto.movimientoId())
                .orElseThrow(() -> new BusinessRuleException("Movimiento no encontrado con id: " + dto.movimientoId()));
        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new BusinessRuleException("Producto no encontrado con id: " + dto.productoId()));
        Detalle_movimiento nuevo = detalleMapper.DTOAEntidad(dto, movimiento, producto);
        return toDTO(detalleRepository.save(nuevo));
    }

    @Override
    public Detalle_movimientoResponseDTO actualizarDetalleMovimiento(Detalle_movimientoRequestDTO dto, Long id) {
        Detalle_movimiento existente = detalleRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Detalle no encontrado con id: " + id));
        Movimiento movimiento = movimientoRepository.findById(dto.movimientoId())
                .orElseThrow(() -> new BusinessRuleException("Movimiento no encontrado con id: " + dto.movimientoId()));
        Producto producto = productoRepository.findById(dto.productoId())
                .orElseThrow(() -> new BusinessRuleException("Producto no encontrado con id: " + dto.productoId()));
        detalleMapper.actualizarEntidadDesdeDTO(existente, dto, movimiento, producto);
        return toDTO(detalleRepository.save(existente));
    }

    @Override
    public List<Detalle_movimientoResponseDTO> buscarPorMovimiento(Long movimientoId) {
        return detalleRepository.findByMovimientoId(movimientoId)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public void eliminarDetalleMovimiento(Long id) {
        if (!detalleRepository.existsById(id))
            throw new BusinessRuleException("Detalle no encontrado con id: " + id);
        detalleRepository.deleteById(id);
    }

    @Override
    public List<Detalle_movimientoResponseDTO> listarDetalles() {
        return detalleRepository.findAll()
                .stream().map(this::toDTO).toList();
    }

    @Override
    public Detalle_movimientoResponseDTO buscarPorId(Long id) {
        Detalle_movimiento d = detalleRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Detalle no encontrado con id: " + id));
        return toDTO(d);
    }
}