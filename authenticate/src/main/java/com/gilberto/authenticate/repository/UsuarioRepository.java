package com.gilberto.authenticate.repository;

import com.gilberto.authenticate.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
