package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Evento;
import eventos.modelo.repository.EventoRepository;


@Repository
public class EventoDaoImplMy8 implements EventoDao {
	
	@Autowired
	private EventoRepository erepo;

	@Override
	public Evento buscarUno(int idEvento) {
		// TODO Auto-generated method stub
		return erepo.findById(idEvento).orElse(null);
	}

	@Override
	public List<Evento> todos() {
		// TODO Auto-generated method stub
		return erepo.findAll();
	}

	@Override
	public List<Evento> buscarEventosPorTipo(int idTipo) {
		// TODO Auto-generated method stub
		return erepo.findPorTipo(idTipo);
	}
	
	@Override
	public List<Evento> buscarEventosActivos(String estado) {
		// TODO Auto-generated method stub
		return erepo.findByEstado(estado);
	}

	@Override
	public List<Evento> buscarEventosDestacados(String destacado) {
		// TODO Auto-generated method stub
		return erepo.findByDestacado(destacado);
	}

	@Override
	public int insertOne(Evento evento) {
		// TODO Auto-generated method stub
		int filas =0;
		try {
			erepo.save(evento);
			filas=1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int borrarEvento(int idEvento) {
		// TODO Auto-generated method stub
		int filas = 0;
		try {
			erepo.deleteById(idEvento);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;	
	}

	@Override
	public int modificarEvento(Evento evento) {
		// TODO Auto-generated method stub
		int filas = 0;
		Evento e1 = null;
		try {
			e1 = erepo.findById(evento.getIdEvento()).orElse(null);
			e1 = evento;
			erepo.save(e1);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}



}
