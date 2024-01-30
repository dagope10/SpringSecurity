package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Evento;

public interface EventoDao {
	
	Evento buscarUno(int idEvento);
	List<Evento> todos();
	List<Evento> buscarEventosPorTipo(int idTipo);
	List<Evento> buscarEventosActivos(String estado);
	List<Evento> buscarEventosDestacados(String destacado);
	int insertOne(Evento evento);
	int borrarEvento(int idEvento);
	int modificarEvento(Evento evento);
	

}
