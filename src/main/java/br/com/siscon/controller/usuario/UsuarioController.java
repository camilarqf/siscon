package br.com.siscon.controller.usuario;

import br.com.siscon.model.usuario.Usuario;
import br.com.siscon.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public Optional<Usuario> buscarUsuarioPorId(@PathVariable("id") Long id){
        Usuario verificaId = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário "+ id + " não existe"));
        return usuarioRepository.findById(id);
    }

    @PostMapping("/salvar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public Usuario salvarUsuario(@Valid @RequestBody Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setData(new Date());
        return usuarioRepository.save(usuario);

    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public Usuario editarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable("id") Long id){
        Usuario verificaId = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário "+ id + " não existe"));
        usuario.setUsuario(usuario.getUsuario());
        usuario.setMatricula(usuario.getMatricula());
        usuario.setRole(usuario.getRole());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setData_atualizacao(new Date());
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void excluirUsuario(@Valid @PathVariable("id") Long id){
        Usuario verificaId = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário "+ id + " não existe"));
        usuarioRepository.deleteById(id);
    }

}
