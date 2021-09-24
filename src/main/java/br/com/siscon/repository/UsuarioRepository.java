package br.com.siscon.repository;

import br.com.siscon.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);

    Boolean existsByUsuario(String usuario);

    Boolean existsByMatricula(String matricula);

}
