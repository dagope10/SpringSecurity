package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Tipo;

public interface TipoDao {
	
	Tipo buscarUno(int idTipo);
	List<Tipo> todos();

}
