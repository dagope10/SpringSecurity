package eventos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eventos.modelo.dao.ReservaDao;
import eventos.modelo.entitis.Reserva;


@RestController
@RequestMapping("rest/reserva")
public class ReservaRestController {
	
	@Autowired
	private ReservaDao rdao;
	@GetMapping("/todas")
	public List<Reserva> todos(){
		return rdao.todas();
	}
	
	@GetMapping("/porEvento/{idEvento}")
	public List<Reserva> porEvento(int idEvento){
		return rdao.findByIdEvento(idEvento);
	}

}
