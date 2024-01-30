package eventos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.*;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Usuario;

@Controller
public class HomeController{
	
	//Formamos los objetos de cada tipo de entidad
	@Autowired
	EventoDao edao;
	@Autowired
	PerfilDao pdao;
	@Autowired
	ReservaDao rdao;
	@Autowired
	TipoDao tdao;
	@Autowired
	UsuarioDao udao;
	
	/*GetMapping para el inicio. Sirve para redirigir a la home y cargar los eventos*/
	@GetMapping("/")
	public String verTodos(Model model, Authentication aut) {
		
	//	System.out.println(aut.getName() + "  -  " + aut.getAuthorities());
		List<Evento> listaDestacados = edao.buscarEventosDestacados("S");
		model.addAttribute("listaDestacados", listaDestacados);
		List<Evento> listaActivos = edao.buscarEventosActivos("ACTIVO");
		model.addAttribute("listaActivos", listaActivos);
		
		return "listaEventos";
		 
	}
	
	/*GetMapping para el registro. Sirve para formar un objeto de tipo usuario para guardar y mandarlo al form*/
	@GetMapping("/signup")
	public String registrar(Model model) {
	model.addAttribute("usuario", new Usuario());	
		return "registro";	 
	}
	
	/*PostMapping para el registro. Sirve para procesar el formulario. Luego va a login*/
	@PostMapping("/signup")
	public String proregistrar(Model model, Usuario usuario, RedirectAttributes ratt) {
		
		usuario.setEnabled(1);
		usuario.setFechaRegistro(new Date());
	 	usuario.addPerfil(pdao.findById(3));
	  //	usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
	 	usuario.setPassword("{noop}"+ usuario.getPassword());
	 	if (udao.registro(usuario)==1) {
	 		ratt.addFlashAttribute("mensaje", "alta realizada");
	 		return "redirect:/login";
	 	}
	 	else {
	 		//Si ya existe, volvemos a registro y lo sacamos en mensaje
	 		model.addAttribute("mensaje", "Error. Ya existe como usuario");
	 		return "registro";
	 		
	 	}
		
	}
	
	
	@GetMapping("/error")
	public String procesarError() {
		
		 
		System.out.println("procesar error");
		
		return "pruebas";
	}
	
	
	
}