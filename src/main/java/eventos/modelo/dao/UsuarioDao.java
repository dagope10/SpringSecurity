package eventos.modelo.dao;

import eventos.modelo.entitis.Usuario;

public interface UsuarioDao {
	
	Usuario findByUsername(String username);
	int registro(Usuario usuario);
	

}
