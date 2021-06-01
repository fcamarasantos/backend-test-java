package bruno.estacionamentoAPI.config.security;

import bruno.estacionamentoAPI.model.Estacionamento;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Service
public class TokenService {

  @Value("${estacionamento.jwt.secret}")
  private String secret;

  private String expiration = "86400000";

  public String gerarToken(Authentication authentication) {
    Estacionamento logado = (Estacionamento) authentication.getPrincipal();
    Date hoje = new Date();
    Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
    return Jwts.builder()
            .setIssuer("API estacionamento")
            .setSubject(logado.getId().toString())
            .setIssuedAt(hoje)
            .setExpiration(dataExpiracao)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Long getUsuarioId(String token) {
    Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    return Long.parseLong(body.getSubject());
  }
}
