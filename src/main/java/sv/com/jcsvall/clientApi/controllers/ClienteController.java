package sv.com.jcsvall.clientApi.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.com.jcsvall.clientApi.entities.Cliente;
import sv.com.jcsvall.clientApi.entities.Usuario;
import sv.com.jcsvall.clientApi.models.ClienteDto;
import sv.com.jcsvall.clientApi.services.ClienteService;
import sv.com.jcsvall.clientApi.services.UsuarioService;

@RestController
@RequestMapping("cliente-api")
public class ClienteController {

	@Autowired
	@Qualifier("clienteService")
	ClienteService clienteService;
	
	@Autowired
	@Qualifier("usuarioService")
	UsuarioService usuarioService;

	@GetMapping("/clientesList")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		return new ResponseEntity<List<Cliente>>(clienteService.getAllClientes(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
		Cliente cli = new Cliente();
		cli.setNombres(clienteDto.getNombres());
		cli.setApellidos(clienteDto.getApellidos());
		cli.setDocumento(clienteDto.getDocumento());
		cli.setFechaInicio(new Date());
		cli.setFechaFin(new Date());
		
		List<Usuario> us = usuarioService.getAllUser();
		
		cli.setUsuario(us.get(0));
		
		clienteService.addCliente(cli);
		return new ResponseEntity<ClienteDto>(clienteDto, HttpStatus.CREATED);
	}
}
