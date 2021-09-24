package br.com.siscon.security.jwt;

import br.com.siscon.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.token}")
    private String jwtToken;

    @Value("${app.tokenExpiracao}")
    private int jwtTokenExpiracao;

    public String generateJwtToken(Authentication authentication){

        UserDetailsImpl usuarioPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtTokenExpiracao))
                .signWith(SignatureAlgorithm.HS512, jwtToken)
                .compact();
    }

    public String getUserNameFromJwtToken(String stoken){
        return Jwts.parser().setSigningKey(jwtToken).parseClaimsJws(stoken).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtToken).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            logger.error("Assinatura JWT inválida: {}", e.getMessage());
        }catch (MalformedJwtException e){
            logger.error("Token JWT inválido: {}", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("O token JWT expirou: {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("O token JWT não é compatível: {}", e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("A string de declarações JWT está vazia: {}", e.getMessage());
        }

        return false;
    }

}
