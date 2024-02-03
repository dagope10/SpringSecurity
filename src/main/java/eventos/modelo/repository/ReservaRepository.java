package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;



public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
	
	@Query("select r from Reserva r where r.evento.idEvento = ?1")
	public List<Reserva> findPorEvento(int idEvento);
	
	@Query("select r from Reserva r where r.usuario.username = ?1")
	public List<Reserva> findByUsername(String username);
	
	@Query("select r from Reserva r where r.evento.idEvento = ?1 and r.usuario.username= ?2")
	public List<Reserva> findPorEventoYUsername(int idEvento, String username);
	
	@Query("SELECT SUM(r.cantidad) FROM Reserva r WHERE r.usuario.username = :username")
    Integer sumarCantidadReservasPorUsuario(@Param("username") String username);
	
	@Query("SELECT SUM(r.cantidad) FROM Reserva r WHERE r.evento.idEvento = :idEvento")
    Integer sumarReservasPorEvento(@Param("idEvento") int idEvento);
		
}
