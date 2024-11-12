package com.example.pratica_saep.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.pratica_saep.Model.Usuario;
import com.example.pratica_saep.Repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios/cadastrar")
    public String exibirFormularioCadastro() {
        return "usuario/cadastrar";
    }

    @PostMapping("/usuarios/salvar")
    public String salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios/cadastrar?sucesso";
    }
}

