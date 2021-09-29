package br.com.siscon.controller.usuario;

import br.com.siscon.model.usuario.Usuario;
import br.com.siscon.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Api(tags = "API de usuário")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @ApiOperation(value = "Listar usuários")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar um usuário pelo identificador")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable("id") Long id){
        Usuario verificaId = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário "+ id + " não existe"));
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
    }

    @PostMapping("/salvar")
    @ApiOperation(value = "Cadastrar usuário no sistema")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setData(new Date());
        Usuario usuarioCadastrado =  usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuarioCadastrado, HttpStatus.CREATED);

    }

    @PutMapping("/editar/{id}")
    @ApiOperation(value = "Editar usuário")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERADOR')")
    public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable("id") Long id){
        Usuario verificaId = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário "+ id + " não existe"));
        usuario.setUsuario(usuario.getUsuario());
        usuario.setMatricula(usuario.getMatricula());
        usuario.setRole(usuario.getRole());
        if(usuario.getSenha() != null){
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        usuario.setData_atualizacao(new Date());
        Usuario usuarioEditado =  usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuarioEditado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir usuário")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> excluirUsuario(@Valid @PathVariable("id") Long id){
        Usuario verificaId = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário "+ id + " não existe"));
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
