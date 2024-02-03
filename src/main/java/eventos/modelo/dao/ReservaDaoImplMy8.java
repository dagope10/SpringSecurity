package eventos.modelo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
import eventos.modelo.entitis.Tipo;
import eventos.modelo.entitis.Usuario;
import eventos.modelo.repository.EventoRepository;
import eventos.modelo.repository.ReservaRepository;
import eventos.modelo.repository.TipoRepository;
import eventos.modelo.repository.UsuarioRepository;


@Repository
public class ReservaDaoImplMy8 implements ReservaDao {
	
	@Autowired
	private ReservaRepository rrepo;
	@Autowired 
	UsuarioRepository usuarioRepository;
	@Autowired
	EventoRepository eventoRepository;
	

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
	public boolean crearReserva(int idEvento, String username, int cantidad) {
		Usuario usuario = usuarioRepository.findByUsername(username);
		Evento evento = eventoRepository.findById(idEvento).orElse(null);
		if(puedeReservar(username)) {
			if (usuario != null && evento != null) {
	            Reserva reserva = new Reserva();
	            reserva.setEvento(evento);
	            reserva.setUsuario(usuario);
	            reserva.setCantidad(cantidad);
	            reserva.setPrecioVenta(evento.getPrecio());
	            rrepo.save(reserva);
	            return true;
	        }
		}
	    
	    
	    return false;
		
	}
	
	public boolean puedeReservar(String username) {
		
		Integer cantidadReservados = rrepo.sumarCantidadReservasPorUsuario(username);
		
		if(cantidadReservados == null) {
			cantidadReservados = 0;
		}
		
		return cantidadReservados < 10;
		
	}
	
	public int calcularEntradas(int idEvento){
		Evento evento = eventoRepository.findById(idEvento).orElse(null);
		int disponible = 0;
		if(rrepo.sumarReservasPorEvento(idEvento) == null) {
			disponible = evento.getAforoMaximo();
		}
		else {
			disponible = evento.getAforoMaximo() - rrepo.sumarReservasPorEvento(idEvento);	
		}
		 
		System.out.println(disponible);
		return disponible;
	}
	
	public int entradasDisponiblesParaUsuario(String username) {
		int maximoEntradasPermitidas = 10;
		Integer entradasReservadas = rrepo.sumarCantidadReservasPorUsuario(username);
		
		if(entradasReservadas == null) {
			entradasReservadas = 0;
		}
		return maximoEntradasPermitidas - entradasReservadas;
		
	}
	
	




	
}
