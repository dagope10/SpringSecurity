package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entitis.Evento;



public interface EventoRepository extends JpaRepository<Evento, Integer>{
	
	public List<Evento> findByDestacado(String destacado);
	
	public List<Evento> findByEstado(String estado);


	
	@Query("select e from Evento e where e.tipo.idTipo = ?1")
	public List<Evento> findPorTipo(int idTipo);
		
}
