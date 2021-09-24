package br.com.siscon.dataloader;

import br.com.siscon.model.usuario.Role;
import br.com.siscon.model.usuario.Usuario;
import br.com.siscon.repository.UsuarioRepository;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.Collections;
import java.util.Date;


@Component
@AllArgsConstructor
public class Dataloader implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!usuarioRepository.existsByUsuario("usuario")){
            Usuario usuario= new Usuario();
            usuario.setUsuario("usuario");
            usuario.setMatricula("097.301-6");
            usuario.setSenha(passwordEncoder.encode("123456"));
            usuario.setRole(Collections.singleton(Role.ROLE_USUARIO));
            usuario.setData(new Date());
            usuarioRepository.save(usuario);
            System.out.println(usuario);
        }

        if(!usuarioRepository.existsByUsuario("caixa")){
            Usuario usuario= new Usuario();
            usuario.setUsuario("caixa");
            usuario.setMatricula("232.173-0");
            usuario.setSenha(passwordEncoder.encode("123456"));
            usuario.setRole(Collections.singleton(Role.ROLE_CAIXA));
            usuario.setData(new Date());
            usuarioRepository.save(usuario);
        }

        if(!usuarioRepository.existsByUsuario("moderador")){
            Usuario usuario= new Usuario();
            usuario.setUsuario("moderador");
            usuario.setMatricula("473.204-4");
            usuario.setSenha(passwordEncoder.encode("123456"));
            usuario.setRole(Collections.singleton(Role.ROLE_MODERADOR));
            usuario.setData(new Date());
            usuarioRepository.save(usuario);
        }

        if(!usuarioRepository.existsByUsuario("admin")){
            Usuario usuario= new Usuario();
            usuario.setUsuario("admin");
            usuario.setMatricula("027.221-8");
            usuario.setSenha(passwordEncoder.encode("123456"));
            usuario.setRole(Collections.singleton(Role.ROLE_ADMIN));
            usuario.setData(new Date());
            usuarioRepository.save(usuario);
        }
    }
}
