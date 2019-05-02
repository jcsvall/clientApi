package sv.com.jcsvall.clientApi.services;

import java.util.List;

import sv.com.jcsvall.clientApi.entities.Usuario;

public interface UsuarioService {
	public abstract List<Usuario> getAllUser();

	public abstract Usuario crearUsuario(Usuario usuario);

	public abstract Usuario editarUsuario(Usuario usuario);
	
	
}
