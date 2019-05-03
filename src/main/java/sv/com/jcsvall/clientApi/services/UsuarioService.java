package sv.com.jcsvall.clientApi.services;

import java.util.List;

import sv.com.jcsvall.clientApi.entities.Usuario;

public interface UsuarioService {
	public abstract List<Usuario> getAllUser();

	public abstract Usuario crearUsuario(Usuario usuario);

	public abstract Usuario editarUsuario(Usuario usuario);
	
	public abstract Usuario findById(Long id);
	
	public abstract Usuario findByUsuarioAndPassword(String usuario, String password);
	
	public abstract Usuario findByUsuario(String usuario);
}
