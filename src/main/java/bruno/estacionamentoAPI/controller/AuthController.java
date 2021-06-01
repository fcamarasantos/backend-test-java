package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.config.security.TokenService;
import bruno.estacionamentoAPI.controller.dto.TokenDto;
import bruno.estacionamentoAPI.controller.form.LoginForm;
import bruno.estacionamentoAPI.util.RespostaJson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  @ApiOperation(value = "Efetuar login na aplicação")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Retorna token e tipo"),
          @ApiResponse(code = 400, message = "Credenciais inválidas")
  })
  public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
    UsernamePasswordAuthenticationToken dadosLogin = form.converter();

    try {
      Authentication authentication = authManager.authenticate(dadosLogin);
      String token = tokenService.gerarToken(authentication);
      return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().body(RespostaJson.mensagem("Email ou senha incorretos"));
    }
  }
}
