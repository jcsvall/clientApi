package sv.com.jcsvall.clientApi.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sv.com.jcsvall.clientApi.entities.Cliente;
import sv.com.jcsvall.clientApi.entities.Usuario;
import sv.com.jcsvall.clientApi.models.ClienteResponseDto;
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
	public List<ClienteResponseDto> getAllClientesDto() {
		List<ClienteResponseDto> clienteResponseDtoList = new ArrayList<>();
		List<Cliente> clientes=getAllClientes();
		for(Cliente cli:clientes) {
			ClienteResponseDto cliente = new ClienteResponseDto(cli);
			clienteResponseDtoList.add(cliente);
		}
		return clienteResponseDtoList;
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

	@Override
	public List<Cliente> getAllClientesByUsuario(Usuario usuario) {		
		return clienteRepository.findByUsuario(usuario);
	}

	@Override
	public List<ClienteResponseDto> getAllClientesByUsuarioDto(Usuario usuario) {
		List<ClienteResponseDto> clienteResponseDtoList = new ArrayList<>();
		List<Cliente> clientes=getAllClientesByUsuario(usuario);
		for(Cliente cli:clientes) {
			ClienteResponseDto cliente = new ClienteResponseDto(cli);
			clienteResponseDtoList.add(cliente);
		}
		return clienteResponseDtoList;
	}

	@Override
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findById(id);
	}

	

}
