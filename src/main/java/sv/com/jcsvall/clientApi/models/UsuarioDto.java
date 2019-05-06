package sv.com.jcsvall.clientApi.models;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
	private String usuario;

	private String password;

	private String email;
	
	private String empresaEstablecimiento;

	public UsuarioDto() {
	}

}
