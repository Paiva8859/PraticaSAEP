package com.example.pratica_saep.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pratica_saep.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
