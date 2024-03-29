package eventos.modelo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario,String>{

	@Query("select u from Usuario u where u.username = ?1")
	public Usuario findByUsername(String username);

	

		
}
