package bruno.estacionamentoAPI.config.security;

import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

  private TokenService tokenService;
  private EstacionamentoRepository estacionamentoRepository;

  public AutenticacaoTokenFilter(TokenService tokenService, EstacionamentoRepository estacionamentoRepository) {
    this.tokenService = tokenService;
    this.estacionamentoRepository = estacionamentoRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = recuperarToken(request);
    boolean valido = tokenService.isTokenValid(token);
    if(valido) {
      autenticarCliente(token);
    }
    filterChain.doFilter(request, response);
  }


  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }

    return token.substring(7, token.length());
  }

  private void autenticarCliente(String token) {
    Long usuarioId = tokenService.getUsuarioId(token);
    Estacionamento usuario =estacionamentoRepository.findById(usuarioId).get();
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
