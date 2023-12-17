package com.gilberto.authenticate.service;

import com.gilberto.authenticate.entity.Usuario;
import com.gilberto.authenticate.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Usuario não encontrado. ")
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String password){
        Usuario user = buscarPorId(id);
        user.setPassword(password);
        return user;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}