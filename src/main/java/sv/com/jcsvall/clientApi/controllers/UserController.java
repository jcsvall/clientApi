package sv.com.jcsvall.clientApi.controllers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import sv.com.jcsvall.clientApi.configurations.Constantes;
import sv.com.jcsvall.clientApi.entities.Usuario;
import sv.com.jcsvall.clientApi.models.UserDto;
import sv.com.jcsvall.clientApi.models.UsuarioDto;
import sv.com.jcsvall.clientApi.services.UsuarioService;

@RestController
public class UserController {
	@Autowired
	@Qualifier("usuarioService")
	UsuarioService usuarioService;
	
	//@Autowired 
	//HttpSession session;
	
	@PostMapping("user")
	public UserDto login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		String roles="ROLE_USER,ROLE_ADMIN";
		Usuario usSession=usuarioService.findByUsuarioAndPassword(username, pwd);
		String token = getJWToken(username,roles);
		UserDto us = new UserDto();
		us.setUser(username);
		us.setToken(token);
		return us;
	}

	@PostMapping("user2")
	public ResponseEntity<UserDto> login2(@RequestBody UserDto userDto) {
		String roles="ROLE_ADMIN1";		
		UserDto us = new UserDto();
		us.setUser(userDto.getUser());
		
		
		Usuario usSession=usuarioService.findByUsuarioAndPassword(userDto.getUser(), userDto.getPassword());
		
		if(usSession==null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		String token = getJWToken(usSession.getUsuario(),roles);
		us.setToken(token);	
		
		//session.setAttribute("usuario",usSession);
		return  new ResponseEntity<UserDto>(us,HttpStatus.OK);
	}

	private String getJWToken(String username,String roles) {
		final long MINUTO = 1000*60;
		String secretKey = "secrectKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.claim(Constantes.USUARIO_LOGEADO, username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (MINUTO*180)))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	@PostMapping("create")
	public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody UsuarioDto usuario) {
		Usuario us = new Usuario();
		us.setUsuario(usuario.getUsuario());
		us.setPassword(usuario.getPassword());
		us.setEmail(usuario.getEmail());
		us.setEmpresaEstablecimiento(usuario.getEmpresaEstablecimiento());
		usuarioService.crearUsuario(us);
		usuario.setPassword("*******");
		return new ResponseEntity<UsuarioDto>(usuario, HttpStatus.CREATED);
	}
	
	@PutMapping("editar")
	public ResponseEntity<Usuario> editUsuario(@RequestBody Usuario usuario) {
		Usuario us = new Usuario();
		us.setId(usuario.getId());
		us.setUsuario(usuario.getUsuario());
		us.setPassword(usuario.getPassword());
		us.setEmail(usuario.getEmail());
		usuarioService.crearUsuario(us);
		us.setPassword("*******");
		return new ResponseEntity<Usuario>(us, HttpStatus.OK);
	}

	//Solo de prueba
	@PostMapping("user3")
	public ResponseEntity<UserDto> login3(@RequestBody UserDto userDto) {
		String secret = "secrectKey";
		boolean existUser = true;
		if (!existUser) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		UserDto us = new UserDto();
		us.setUser(userDto.getUser());
		

		final Instant now = Instant.now();

		final String jwt = Jwts.builder().setSubject(userDto.getUser()).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
				.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret)).compact();
		
		us.setToken("Bearer "+jwt);

		return new ResponseEntity<UserDto>(us,HttpStatus.OK);
	}
}
