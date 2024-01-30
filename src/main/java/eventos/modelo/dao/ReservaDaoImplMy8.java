package eventos.modelo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
import eventos.modelo.entitis.Tipo;
import eventos.modelo.repository.ReservaRepository;
import eventos.modelo.repository.TipoRepository;


@Repository
public class ReservaDaoImplMy8 implements ReservaDao {
	
	@Autowired
	private ReservaRepository rrepo;

	@Override
	public Reserva buscarUna(int idReserva) {
		// TODO Auto-generated method stub
		return rrepo.findById(idReserva).orElse(null);
	}

	@Override
	public List<Reserva> todas() {
		// TODO Auto-generated method stub
		return rrepo.findAll();
	}

	@Override
	public int insertOne(Reserva reserva) {
		// TODO Auto-generated method stub
		int filas =0;
		try {
			rrepo.save(reserva);
			filas=1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public int borrarReserva(int idReserva) {
		// TODO Auto-generated method stub
		int filas = 0;
		try {
			rrepo.deleteById(idReserva);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;	
	}

	@Override
	public int modificarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		int filas = 0;
		Reserva r1 = null;
		try {
			r1 = rrepo.findById(reserva.getIdReserva()).orElse(null);
			r1 = reserva;
			rrepo.save(r1);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public List<Reserva> porUsername(String username) {
		return rrepo.findByUsername(username);
	}

	@Override
	public List<Reserva> findByIdEvento(int idEvento) {
		// TODO Auto-generated method stub
		return rrepo.findPorEvento(idEvento);
	}



	
}
