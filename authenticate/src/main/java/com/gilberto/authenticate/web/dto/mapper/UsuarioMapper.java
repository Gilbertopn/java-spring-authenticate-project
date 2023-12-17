package com.gilberto.authenticate.web.dto.mapper;

import com.gilberto.authenticate.entity.Usuario;
import com.gilberto.authenticate.web.dto.UsuarioCreateDto;
import com.gilberto.authenticate.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("Role_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props =
                new PropertyMap<Usuario, UsuarioResponseDto>() {
                    @Override
                    protected void configure() {
                        map().setRole(role);
                    }
                };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);

    }
}
