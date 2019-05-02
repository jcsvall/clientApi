package sv.com.jcsvall.clientApi.services;

import java.util.List;

import sv.com.jcsvall.clientApi.entities.Cliente;

public interface ClienteService {
	public abstract List<Cliente> getAllClientes();

	public abstract Cliente addCliente(Cliente cliente);

	public abstract Cliente editCliente(Cliente cliente);

	public abstract int deleteCliente(Cliente cliente);
}
