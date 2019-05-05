package sv.com.jcsvall.clientApi.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sv.com.jcsvall.clientApi.entities.Cliente;
import sv.com.jcsvall.clientApi.entities.Usuario;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Serializable> {
	public List<Cliente> findByUsuario(Usuario usuario);
	
	@Modifying
    @Transactional
    @Query("delete from Cliente c where c.id = ?1")
	public void deleteById(Long id);
	
	public Cliente findById(Long id);
	
}
