package sv.com.jcsvall.clientApi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sv.com.jcsvall.clientApi.entities.Usuario;
import sv.com.jcsvall.clientApi.repositories.UsuarioRepository;
import sv.com.jcsvall.clientApi.services.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioRepository")
	UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> getAllUser() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario editarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario findById(Long id) {		
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario findByUsuarioAndPassword(String usuario, String password) {
		return usuarioRepository.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public Usuario findByUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	
}
