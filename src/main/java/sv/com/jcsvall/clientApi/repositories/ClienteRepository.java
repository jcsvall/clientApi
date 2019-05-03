package sv.com.jcsvall.clientApi.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.com.jcsvall.clientApi.entities.Cliente;
import sv.com.jcsvall.clientApi.entities.Usuario;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Serializable> {
	public List<Cliente> findByUsuario(Usuario usuario);
}
