package com.s1.LogiTrack.service.impl;

import com.s1.LogiTrack.dto.request.UsuarioRequestDTO;
import com.s1.LogiTrack.dto.response.UsuarioResponseDTO;
import com.s1.LogiTrack.exception.BusinessRuleException;
import com.s1.LogiTrack.mapper.PersonaMapper;
import com.s1.LogiTrack.mapper.RolMapper;
import com.s1.LogiTrack.mapper.UsuarioMapper;
import com.s1.LogiTrack.model.Persona;
import com.s1.LogiTrack.model.Rol;
import com.s1.LogiTrack.model.Usuario;
import com.s1.LogiTrack.repository.PersonaRepository;
import com.s1.LogiTrack.repository.RolRepository;
import com.s1.LogiTrack.repository.UsuarioRepository;
import com.s1.LogiTrack.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;
    private final PersonaMapper personaMapper;
    private final RolMapper rolMapper;

    // Método privado auxiliar para no repetir la conversión a DTO
    private UsuarioResponseDTO toDTO(Usuario u) {
        return usuarioMapper.entidadADTO(
                u,
                personaMapper.entidadADTO(u.getPersona()),
                rolMapper.entidadADTO(u.getRol())
        );
    }

    @Override
    public UsuarioResponseDTO guardarUsuario(UsuarioRequestDTO dto) {
        Persona persona = personaRepository.findById(dto.personaId())
                .orElseThrow(() -> new BusinessRuleException("Persona no encontrada con id: " + dto.personaId()));
        Rol rol = rolRepository.findById(dto.rolId())
                .orElseThrow(() -> new BusinessRuleException("Rol no encontrado con id: " + dto.rolId()));
        Usuario nuevo = usuarioMapper.DTOAEntidad(dto, persona, rol);
        return toDTO(usuarioRepository.save(nuevo));
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(UsuarioRequestDTO dto, Long id) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado con id: " + id));
        Persona persona = personaRepository.findById(dto.personaId())
                .orElseThrow(() -> new BusinessRuleException("Persona no encontrada con id: " + dto.personaId()));
        Rol rol = rolRepository.findById(dto.rolId())
                .orElseThrow(() -> new BusinessRuleException("Rol no encontrado con id: " + dto.rolId()));
        usuarioMapper.actualizarEntidadDesdeDTO(existente, dto, persona, rol);
        return toDTO(usuarioRepository.save(existente));
    }

    @Override
    public UsuarioResponseDTO buscarPorNombreUsuario(String nombreUsuario) {
        Usuario u = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado: " + nombreUsuario));
        return toDTO(u);
    }

    @Override
    public List<UsuarioResponseDTO> buscarPorRol(Long rolId) {
        return usuarioRepository.findByRolId(rolId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id))
            throw new BusinessRuleException("Usuario no encontrado con id: " + id);
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado con id: " + id));
        return toDTO(u);
    }
}