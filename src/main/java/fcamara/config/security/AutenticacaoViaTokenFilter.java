package fcamara.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import fcamara.model.entity.User;
import fcamara.model.repository.UserRepository;


public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UserRepository repository;
	
	

	public AutenticacaoViaTokenFilter(TokenService tokenService,UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		if(valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUser = tokenService.getIdUser(token);
		User user = repository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token  = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer"))
			return null;
		return token.substring(7, token.length());
	}
}
