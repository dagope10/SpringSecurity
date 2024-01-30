package eventos.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Perfil;
import eventos.modelo.repository.PerfilRepository;


@Repository
public class PerfilDaoImplMy8 implements PerfilDao {

	@Autowired
	private PerfilRepository prepo;
	@Override
	public Perfil findById(int idPerfil) {
		// TODO Auto-generated method stub
		return prepo.findById(idPerfil).orElse(null);
	}
	


}
