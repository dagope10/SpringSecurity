package eventos.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Usuario;
import eventos.modelo.repository.UsuarioRepository;


@Repository
public class UsuarioDaoImplMy8 implements UsuarioDao {
	@Autowired
	private UsuarioRepository urepo;
	
	@Override
	public Usuario findById(String username) {
		// TODO Auto-generated method stub
		Usuario usuarioEncontrado = urepo.findById(username);
		
		if (usuarioEncontrado==null) {
			return null;
		}
		else return usuarioEncontrado;

	}
	
	@Override
	public int registro(Usuario usuario) {
		if (findById(usuario.getUsername()) == null) {
				urepo.save(usuario);
				return 1;
		}
		return 0;
	}

}
