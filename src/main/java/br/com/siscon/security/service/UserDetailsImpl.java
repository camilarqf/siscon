package br.com.siscon.security.service;

import br.com.siscon.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String usuario;

    private String matricula;

    @JsonIgnore
    private String senha;

    private Date data;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String usuario, String matricula, String senha, Date data,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.usuario = usuario;
        this.matricula = matricula;
        this.senha = senha;
        this.data = data;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getMatricula(),
                usuario.getSenha(),
                usuario.getData(),
                authorities
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId(){
        return id;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    public String getMatricula(){
        return matricula;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        UserDetailsImpl usuario = (UserDetailsImpl) obj;
        return Objects.equals(id, usuario.id);
    }
}
