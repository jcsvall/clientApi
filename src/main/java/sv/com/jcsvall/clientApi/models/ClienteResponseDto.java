package sv.com.jcsvall.clientApi.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import sv.com.jcsvall.clientApi.entities.Cliente;

@Getter
@Setter
public class ClienteResponseDto  {
	
	private Long id;
			
	private String nombres;
	
	private String apellidos;
	
	private String documento;
	
	private Date fechaInicio;
	
	private Date fechaFin;

	public ClienteResponseDto() {

	}

	public ClienteResponseDto(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nombres = cliente.getNombres();
		this.apellidos = cliente.getApellidos();
		this.documento = cliente.getDocumento();
		this.fechaInicio = cliente.getFechaInicio();
		this.fechaFin = cliente.getFechaFin();
	}
	
	

}
