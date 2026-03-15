package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.MovimientoRequestDTO;
import com.s1.LogiTrack.dto.response.MovimientoResponseDTO;
import com.s1.LogiTrack.enums.TipoMovimiento;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.BodegaMapper;
import com.s1.LogiTrack.mapper.MovimientoMapper;
import com.s1.LogiTrack.mapper.PersonaMapper;
import com.s1.LogiTrack.mapper.RolMapper;
import com.s1.LogiTrack.mapper.UsuarioMapper;
import com.s1.LogiTrack.model.Bodega;
import com.s1.LogiTrack.model.Movimiento;
import com.s1.LogiTrack.model.Usuario;
import com.s1.LogiTrack.repository.BodegaRepository;
import com.s1.LogiTrack.repository.MovimientoRepository;
import com.s1.LogiTrack.repository.UsuarioRepository;
import com.s1.LogiTrack.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final BodegaRepository bodegaRepository;
    private final MovimientoMapper movimientoMapper;
    private final UsuarioMapper usuarioMapper;
    private final BodegaMapper bodegaMapper;
    private final PersonaMapper personaMapper;
    private final RolMapper rolMapper;

    private MovimientoResponseDTO toDTO(Movimiento m) {
        return movimientoMapper.entidadADTO(
                m,
                usuarioMapper.entidadADTO(
                        m.getUsuario(),
                        personaMapper.entidadADTO(m.getUsuario().getPersona()),
                        rolMapper.entidadADTO(m.getUsuario().getRol())
                ),
                // bodegaOrigen y bodegaDestino son opcionales (pueden ser null)
                m.getBodegaOrigen() != null
                        ? bodegaMapper.entidadADTO(m.getBodegaOrigen(), personaMapper.entidadADTO(m.getBodegaOrigen().getEncargado()))
                        : null,
                m.getBodegaDestino() != null
                        ? bodegaMapper.entidadADTO(m.getBodegaDestino(), personaMapper.entidadADTO(m.getBodegaDestino().getEncargado()))
                        : null
        );
    }

    @Override
    public MovimientoResponseDTO guardarMovimiento(MovimientoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado con id: " + dto.usuarioId()));
        Bodega origen = dto.bodegaOrigenId() != null
                ? bodegaRepository.findById(dto.bodegaOrigenId())
                .orElseThrow(() -> new BusinessRuleException("Bodega origen no encontrada"))
                : null;
        Bodega destino = dto.bodegaDestinoId() != null
                ? bodegaRepository.findById(dto.bodegaDestinoId())
                .orElseThrow(() -> new BusinessRuleException("Bodega destino no encontrada"))
                : null;
        Movimiento nuevo = movimientoMapper.DTOAEntidad(dto, usuario, origen, destino);
        return toDTO(movimientoRepository.save(nuevo));
    }

    @Override
    public MovimientoResponseDTO actualizarMovimiento(MovimientoRequestDTO dto, Long id) {
        Movimiento existente = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Movimiento no encontrado con id: " + id));
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado con id: " + dto.usuarioId()));
        Bodega origen = dto.bodegaOrigenId() != null
                ? bodegaRepository.findById(dto.bodegaOrigenId())
                .orElseThrow(() -> new BusinessRuleException("Bodega origen no encontrada"))
                : null;
        Bodega destino = dto.bodegaDestinoId() != null
                ? bodegaRepository.findById(dto.bodegaDestinoId())
                .orElseThrow(() -> new BusinessRuleException("Bodega destino no encontrada"))
                : null;
        movimientoMapper.actualizarEntidadDesdeDTO(existente, dto, usuario, origen, destino);
        return toDTO(movimientoRepository.save(existente));
    }

    @Override
    public List<MovimientoResponseDTO> buscarPorTipo(TipoMovimiento tipo) {
        return movimientoRepository.findByTipo(tipo)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public List<MovimientoResponseDTO> buscarPorUsuario(Long usuarioId) {
        return movimientoRepository.findByUsuarioId(usuarioId)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public List<MovimientoResponseDTO> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoRepository.findByFechaBetween(fechaInicio, fechaFin)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public void eliminarMovimiento(Long id) {
        if (!movimientoRepository.existsById(id))
            throw new BusinessRuleException("Movimiento no encontrado con id: " + id);
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<MovimientoResponseDTO> listarMovimientos() {
        return movimientoRepository.findAll()
                .stream().map(this::toDTO).toList();
    }

    @Override
    public MovimientoResponseDTO buscarPorId(Long id) {
        Movimiento m = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Movimiento no encontrado con id: " + id));
        return toDTO(m);
    }
}