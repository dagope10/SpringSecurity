package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;

public interface ReservaDao {
	
	Reserva buscarUna(int idReserva);
	List<Reserva> todas();
	int insertOne(Reserva reserva);
	int borrarReserva(int idReserva);
	int modificarReserva(Reserva reserva);
	List<Reserva> porUsername(String username);
	List<Reserva> findByIdEvento(int idEvento);

	

}
