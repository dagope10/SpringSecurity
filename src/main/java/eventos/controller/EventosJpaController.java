package eventos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDaoImplMy8;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
import eventos.modelo.repository.EventoRepository;
import eventos.modelo.repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app/evento")
public class EventosJpaController {
	@Autowired
	EventoDao edao;
	@Autowired
	ReservaRepository rrepo;
	@Autowired
	ReservaDaoImplMy8 reservaDao;

	//Método para ver cada evento en detalle
	@GetMapping("/verUno/{id}")
	public String verUno(Model model, @PathVariable(name = "id") int idEvento, Authentication authentication) {
		String username = authentication.getName();
		Evento evento = edao.buscarUno(idEvento);
		model.addAttribute("evento", evento);
		int aforoDisponible = reservaDao.calcularEntradas(idEvento);
		int entradasDisponibles = reservaDao.entradasDisponiblesParaUsuario(username);
		model.addAttribute("aforoDisponible", aforoDisponible);
		model.addAttribute("entradasDisponibles", entradasDisponibles);
		return "verUnEvento";

	}
	//Método desarrollado por si llegamos a meter niveles admin
	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model, @PathVariable(name = "id") int idEvento) {

		if (edao.borrarEvento(idEvento) == 1)
			model.addAttribute("mensaje", "evento eliminado");
		else
			model.addAttribute("mensaje", "evento NOOO eliminado");

		// return "redirect:/app/producto/index";
		return "forward:/";
	}

	@GetMapping("/reservar")
	public String mostrarReserva() {
		return "forward:/listaEventos";
	}

	/*
	 * Necesito procesar las reservas En primer lugar tenemos que validar cuántas
	 * reservas tiene esa persona. Necesitamos una query para sacar reservas r donde
	 * username sea ?1 y idEvento ?2
	 */
	@PostMapping("/reservar")
	public String procFormReserva(@RequestParam("numero") int cantidad, @RequestParam("evento") int idEvento,
			Authentication authentication, RedirectAttributes ratt, Model model) {

		String username = authentication.getName();
		boolean reservaCreada = reservaDao.crearReserva(idEvento, username, cantidad);

		if (reservaCreada) {
			ratt.addFlashAttribute("mensajeExito", "Reserva realizada con éxito");
			return "redirect:/app/evento/verUno/" + idEvento;
		} else {
			ratt.addFlashAttribute("mensajeError", "No se pudo realizar la reserva");
			return "redirect:/app/evento/verUno/" + idEvento;
		}

	}

	// Método para cancelar una reserva a un evento
	@GetMapping("/cancelar/{id}")
	public String cancelar(Model model, @PathVariable(name = "id") int idReserva) {

		if (reservaDao.borrarReserva(idReserva) == 1)
			model.addAttribute("mensaje", "reserva eliminada");
		else
			model.addAttribute("mensaje", "evento NOOO eliminada");

		return "redirect:/app/evento/miseventos";

	}

	// Método para cancelar una reserva a un evento
	@GetMapping("/miseventos")
	public String misEventos(Model model, Authentication authentication) {
		String username = authentication.getName();
		List<Reserva> reservas = reservaDao.porUsername(username);
		model.addAttribute("reservas", reservas);
		return "miseventos";
	}

	/*
	 * Si nos da tiempo a meter más roles
	 * 
	 * @GetMapping("/alta") public String enviarFormulario() {
	 * 
	 * 
	 * return "formProducto";
	 * 
	 * 
	 * }
	 * 
	 * @PostMapping("/alta") public String procesarFormulario(Model model,Producto
	 * producto ) {
	 * 
	 * 
	 * System.out.println(producto);
	 * 
	 * pdao.insertarProducto(producto);
	 * 
	 * System.out.println(producto);
	 * 
	 * 
	 * return "redirect:/";
	 * 
	 * 
	 * }
	 * 
	 * @GetMapping("/editar/{id}") public String enviarFormularioEditar(Model
	 * model, @PathVariable(name="id") int codigo) {
	 * 
	 * 
	 * 
	 * model.addAttribute("producto", pdao.buscarUno(codigo)); return
	 * "formProductoEditar";
	 * 
	 * 
	 * }
	 * 
	 * @PostMapping("/modificar") public String procesarFormularioEditar(Model
	 * model,Producto producto ) {
	 * 
	 * 
	 * System.out.println(producto);
	 * 
	 * pdao.modificarProducto(producto);
	 * 
	 * // System.out.println(producto);
	 * 
	 * 
	 * return "redirect:/";
	 * 
	 * 
	 * }
	 * 
	 * @GetMapping("/ver") public String verUsuarios(Model model) {
	 * model.addAttribute("mensaje", "Listado de usuarios"); return "pruebas"; }
	 */

}
