package fcamara.controller.dto;

public class TokenDto {

	private String token;
	private String tipo;

	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
		
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	
}
