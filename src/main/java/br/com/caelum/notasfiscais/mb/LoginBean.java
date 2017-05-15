package br.com.caelum.notasfiscais.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuaLogin(){
		UsuarioDao uDao = new UsuarioDao();
		boolean existe = uDao.existe(this.usuario);
		System.out.println("O login era valido? " + existe);
		if(existe){
			return "produtos";
		} else{
			this.usuario = new Usuario();
			return "login";
		}
	}
}
