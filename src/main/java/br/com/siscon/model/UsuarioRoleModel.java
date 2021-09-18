package br.com.siscon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UsuarioRoleModel {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MODERADOR("ROLE_MODERADOR"),
    ROLE_CAIXA("ROLE_CAIXA");

    private String role;
}
