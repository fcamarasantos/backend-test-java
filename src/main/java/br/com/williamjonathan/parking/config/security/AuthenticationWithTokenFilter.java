package br.com.williamjonathan.parking.config.security;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.repository.EmployeeRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationWithTokenFilter extends OncePerRequestFilter {

    private EmployeeRepository employeeRepository;

    private TokenService tokenService;

    public AuthenticationWithTokenFilter(TokenService tokenService, EmployeeRepository employeeRepository) {
        this.tokenService = tokenService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(httpServletRequest);
        boolean valid = tokenService.isTokenValid(token);
        if(valid) {
            authenticateClient(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticateClient(String token) {
        Long userId = tokenService.getUserId(token);
        Optional<Employee> optional = this.employeeRepository.findById(userId);
        if(optional.isPresent()) {
            Employee employee = optional.get();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(employee, null, employee.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
