package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.response.AuditoriaResponseDTO;
import com.s1.LogiTrack.enums.TipoOperacion;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.AuditoriaMapper;
import com.s1.LogiTrack.mapper.PersonaMapper;
import com.s1.LogiTrack.mapper.RolMapper;
import com.s1.LogiTrack.mapper.UsuarioMapper;
import com.s1.LogiTrack.model.Auditoria;
import com.s1.LogiTrack.repository.AuditoriaRepository;
import com.s1.LogiTrack.service.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final AuditoriaMapper auditoriaMapper;
    private final UsuarioMapper usuarioMapper;
    private final PersonaMapper personaMapper;
    private final RolMapper rolMapper;

    private AuditoriaResponseDTO toDTO(Auditoria a) {
        return auditoriaMapper.entidadADTO(
                a,
                usuarioMapper.entidadADTO(
                        a.getUsuario(),
                        personaMapper.entidadADTO(a.getUsuario().getPersona()),
                        rolMapper.entidadADTO(a.getUsuario().getRol())
                )
        );
    }

    @Override
    public List<AuditoriaResponseDTO> listarAuditorias() {
        return auditoriaRepository.findAll()
                .stream().map(this::toDTO).toList();
    }

    @Override
    public AuditoriaResponseDTO buscarPorId(Long id) {
        Auditoria a = auditoriaRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Auditoría no encontrada con id: " + id));
        return toDTO(a);
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorUsuario(Long usuarioId) {
        return auditoriaRepository.findByUsuarioId(usuarioId)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorOperacion(TipoOperacion operacion) {
        return auditoriaRepository.findByOperacion(operacion)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public List<AuditoriaResponseDTO> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return auditoriaRepository.findByFechaBetween(fechaInicio, fechaFin)
                .stream().map(this::toDTO).toList();
    }
}