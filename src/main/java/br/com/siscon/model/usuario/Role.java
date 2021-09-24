package br.com.siscon.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MODERADOR("ROLE_MODERADOR"),
    ROLE_CAIXA("ROLE_CAIXA"),
    ROLE_USUARIO("ROLE_USUARIO");

    private String role;
}
