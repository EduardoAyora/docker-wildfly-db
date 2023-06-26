package ec.edu.ups.demo.helloworld.bussines;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.demo.helloworld.dao.PersonaDAO;
import ec.edu.ups.demo.helloworld.model.Persona;

@Stateless
public class PersonaON {

	@Inject
	private PersonaDAO usuarioDAO;
	
	/*
	 * El metodo insertarUsuario tiene como parametrsos un objeto de tipo usuario 
	 * destro de este metodo se valida varios campos como: existencia de usuario en la base de datos, contraseña, correo, telefono
	 * una vez concluida las verificaciones inserta en la base de datos.
	 */

	public void insertarUsuario(Persona u) throws Exception {
		if (!this.validarCorreo(u.getCorreo()))
			throw new Exception("Correo Incorrecto Debe Contener @ ");
		
		usuarioDAO.insert(u);
	}
	
	
	public List<Persona> getUsuarios() {
		return usuarioDAO.getList();
	}

	public boolean validarCorreo(String correo) {
		if (correo.contains("@")) {
			return true;
		} else {
			return false;
		}
	}

}
