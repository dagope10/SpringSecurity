package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Perfil;



public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
	

		
}
