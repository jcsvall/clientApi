package sv.com.jcsvall.clientApi.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.com.jcsvall.clientApi.entities.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {

	public Usuario findById(Long id);
	
//	public Usuario findByUsuarioAndFindByPassword(String usuario,String password);
	
	public Usuario findByUsuarioAndPassword(String usuario,String password);
	public Usuario findByUsuario(String usuario);
}