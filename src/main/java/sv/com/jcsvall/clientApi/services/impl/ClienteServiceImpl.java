package sv.com.jcsvall.clientApi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sv.com.jcsvall.clientApi.entities.Cliente;
import sv.com.jcsvall.clientApi.repositories.ClienteRepository;
import sv.com.jcsvall.clientApi.services.ClienteService;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	@Qualifier("clienteRepository")
	ClienteRepository clienteRepository;

	@Override
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente addCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente editCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public int deleteCliente(Cliente cliente) {
		int deleted = 0;
		clienteRepository.delete(cliente);
		deleted = 1;
		return deleted;
	}

}
