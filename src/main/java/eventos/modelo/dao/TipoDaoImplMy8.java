package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Tipo;
import eventos.modelo.repository.TipoRepository;


@Repository
public class TipoDaoImplMy8 implements TipoDao {
	
	@Autowired
	private TipoRepository trepo;

	@Override
	public Tipo buscarUno(int idTipo) {
		// TODO Auto-generated method stub
		return trepo.findById(idTipo).orElse(null);
	}

	@Override
	public List<Tipo> todos() {
		// TODO Auto-generated method stub
		return trepo.findAll();

	}
}
