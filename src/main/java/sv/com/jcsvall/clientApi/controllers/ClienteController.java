package sv.com.jcsvall.clientApi.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.com.jcsvall.clientApi.configurations.Constantes;
import sv.com.jcsvall.clientApi.configurations.HandleMyExeptionMessage;
import sv.com.jcsvall.clientApi.entities.Cliente;
import sv.com.jcsvall.clientApi.entities.Usuario;
import sv.com.jcsvall.clientApi.models.ClienteDto;
import sv.com.jcsvall.clientApi.models.ClienteResponseDto;
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

	@Autowired
	HttpSession session;

	@GetMapping("/clientesList")
	public ResponseEntity<List<ClienteResponseDto>> getAllClientes() {
		String userName = (String) session.getAttribute(Constantes.USUARIO);
		Usuario usuarioD = usuarioService.findByUsuario(userName);
		String usuarioLogeado = (String) session.getAttribute(Constantes.USUARIO_LOGEADO);
		return new ResponseEntity<List<ClienteResponseDto>>(clienteService.getAllClientesByUsuarioDto(usuarioD),
				HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
		Cliente cli = new Cliente();
		cli.setNombres(clienteDto.getNombres());
		cli.setApellidos(clienteDto.getApellidos());
		cli.setDocumento(clienteDto.getDocumento());
		try {
			SimpleDateFormat parseador = new SimpleDateFormat("dd/MM/yyyy");
			Date dateInicio = parseador.parse(clienteDto.getFechaInicio());
			Date dateFin = parseador.parse(clienteDto.getFechaFin());
			cli.setFechaInicio(dateInicio);
			cli.setFechaFin(dateFin);
		} catch (Exception e) {
			throw new HandleMyExeptionMessage("Formato de fecha no es correcto, formato: dd/MM/yyyy");
		}

		String userName = (String) session.getAttribute(Constantes.USUARIO);
		Usuario usuarioD = usuarioService.findByUsuario(userName);

		cli.setUsuario(usuarioD);

		clienteService.addCliente(cli);
		return new ResponseEntity<ClienteDto>(clienteDto, HttpStatus.CREATED);
	}
}
