package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Tipo;



public interface TipoRepository extends JpaRepository<Tipo, Integer>{
	

		
}
