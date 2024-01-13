package com.gilberto.authenticate.service;

import com.gilberto.authenticate.entity.Usuario;
import com.gilberto.authenticate.exception.EntityNotFoundException;
import com.gilberto.authenticate.exception.PasswordInvalidException;
import com.gilberto.authenticate.exception.UsernameUniqueViolationException;
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
        try {
            return usuarioRepository.save(usuario);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username '%s' já cadastrado", usuario.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Usuario não encontrado. ")
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmarSenha){

        if(!novaSenha.equals(confirmarSenha)){
            throw new PasswordInvalidException("As senhas devem ser iguais");
        }

        Usuario user = buscarPorId(id);

        if (!senhaAtual.equals(user.getPassword())){
            throw new PasswordInvalidException("Senha não confere");
        }

        user.setPassword(novaSenha);
        return user;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario com '%s' não encontrado", username))
        );
    }

    @Transactional(readOnly = true)
    public Usuario.Role buscarRolePorUsername(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }
}
