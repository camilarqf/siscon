package br.com.siscon.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess(){
        return "Conteúdo público";
    }

    @GetMapping("/usuario")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR') or hasRole('CAIXA') or hasRole('USUARIO')")
    public String usuarioAcesso(){
        return "Conteúdo usuário";
    }

    @GetMapping("/caixa")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR') or hasRole('CAIXA')")
    public String caixaAcesso(){
        return "Conteúdo caixa";
    }

    @GetMapping("/moderador")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public String moderadorAcesso(){
        return "Conteúdo moderador";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAcesso(){
        return "Conteúdo admin";
    }

}
