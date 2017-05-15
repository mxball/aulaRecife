package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable{
	
	private Usuario usuario = new Usuario();
	@Inject
	private UsuarioDao uDao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuaLogin(){
		boolean existe = uDao.existe(this.usuario);
		System.out.println("O login era valido? " + existe);
		if(existe){
			usuarioLogado.logar(usuario);
			return "produtos?faces-redirect=true";
		} else{
			usuarioLogado.deslogar();
			this.usuario = new Usuario();
			return "login";
		}
	}

	public String logout() {
		this.usuarioLogado.deslogar();
		return "login?faces-redirect=true";
	}
	
}
