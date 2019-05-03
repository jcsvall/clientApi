package sv.com.jcsvall.clientApi.services;

import java.util.List;

import sv.com.jcsvall.clientApi.entities.Cliente;
import sv.com.jcsvall.clientApi.entities.Usuario;
import sv.com.jcsvall.clientApi.models.ClienteResponseDto;

public interface ClienteService {
	
	public abstract List<Cliente> getAllClientes();
    public abstract List<ClienteResponseDto> getAllClientesDto();
    
	public abstract Cliente addCliente(Cliente cliente);

	public abstract Cliente editCliente(Cliente cliente);

	public abstract int deleteCliente(Cliente cliente);
	
	public abstract List<Cliente> getAllClientesByUsuario(Usuario usuario);
	public abstract List<ClienteResponseDto> getAllClientesByUsuarioDto(Usuario usuario);
	
}
