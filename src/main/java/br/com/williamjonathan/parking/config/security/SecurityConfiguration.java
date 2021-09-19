package br.com.williamjonathan.parking.config.security;

import br.com.williamjonathan.parking.repository.EmployeeRepository;
import br.com.williamjonathan.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationConfigService authenticationConfigService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/parking").permitAll()
                .antMatchers("/vehicle/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/employee")
                .authenticated()
                .antMatchers(HttpMethod.DELETE, "/employee")
                .authenticated()
                .antMatchers("/parking/**")
                .access("hasRole('MANAGER')")
                .antMatchers("/address/**")
                .access("hasRole('MANAGER')")
                .antMatchers("/phone/**")
                .access("hasRole('MANAGER')")
                .antMatchers(HttpMethod.GET,"/reports/**")
                .access("hasRole('ANALYST')")
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .anyRequest().authenticated()
                .and().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationWithTokenFilter(tokenService, employeeRepository), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationConfigService).passwordEncoder((new BCryptPasswordEncoder()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
