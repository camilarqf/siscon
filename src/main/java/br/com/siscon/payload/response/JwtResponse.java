package br.com.siscon.payload.response;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private Long id;

    private String token;

    private String type = "Bearer";

    private String usuario;

    private String matricula;

    private List<String> roles;

    public JwtResponse(Long id, String token, String usuario, String matricula, List<String> roles) {
        this.id = id;
        this.token = token;
        this.usuario = usuario;
        this.matricula = matricula;
        this.roles = roles;
    }
}
