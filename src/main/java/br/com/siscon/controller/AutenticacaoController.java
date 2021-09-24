package br.com.siscon.controller;

import br.com.siscon.model.usuario.Role;
import br.com.siscon.model.usuario.Usuario;
import br.com.siscon.payload.request.LoginRequest;
import br.com.siscon.payload.request.SignupRequest;
import br.com.siscon.payload.response.JwtResponse;
import br.com.siscon.payload.response.MessageResponse;
import br.com.siscon.repository.UsuarioRepository;
import br.com.siscon.security.jwt.JwtUtils;
import br.com.siscon.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> autenticacaoUsuario(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsuario(), loginRequest.getSenha())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(userDetails.getId(),
                jwt,
                userDetails.getUsername(),
                userDetails.getMatricula(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?>regitrarUsuario(@Valid @RequestBody SignupRequest signupRequest){
        if(usuarioRepository.existsByUsuario(signupRequest.getUsuario())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erro: o nome de usuário já está em uso!"));

        }

        if(usuarioRepository.existsByMatricula(signupRequest.getMatricula())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erro: matrícula já está em uso"));
        }

        Usuario usuario = new Usuario(signupRequest.getUsuario(),
                signupRequest.getMatricula(),
                passwordEncoder.encode(signupRequest.getSenha()),
                new Date());
        Set<String> strRoles = null;
        Set<Role> roles = new HashSet<>();

        if (strRoles == null){
            Role usuarioRole = Role.ROLE_USUARIO;
            roles.add(usuarioRole);
        }else {
            strRoles.forEach(role -> {
                switch (role){
                    case "ROLE_ADMIN":
                        Role adminRole = Role.ROLE_ADMIN;
                        roles.add(adminRole);
                        break;
                    case "ROLE_MODERADOR":
                        Role moderadorRole = Role.ROLE_MODERADOR;
                        roles.add(moderadorRole);
                        break;
                    case "ROLE_CAIXA":
                        Role caixaRole = Role.ROLE_CAIXA;
                        roles.add(caixaRole);
                        break;
                    default:
                        Role usuarioRole = Role.ROLE_USUARIO;
                        roles.add(usuarioRole);
                }
            });
        }

        usuario.setRole(roles);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok(new MessageResponse("Usuário registrado com sucesso!"));
    }

}
