package sv.com.jcsvall.clientApi.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto  {
			
	private String nombres;
	
	private String apellidos;
	
	private String documento;
	
	private String fechaInicio;
	
	private String fechaFin;

	public ClienteDto() {

	}

}
