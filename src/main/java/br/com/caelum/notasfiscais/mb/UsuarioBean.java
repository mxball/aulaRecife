package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean {
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	@Inject
	private UsuarioDao dao;
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioLogadoBean getUsuarioLogado() {
		return usuarioLogado;
	}

	public String gerenciaUsuarios() {
		return "usuario?faces-redirect=true";
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = dao.listaTodos();
		}
		return usuarios;
	}

	public void grava() {
		if (usuario.getId() == null) {
			dao.adiciona(usuario);
		} else {
			dao.atualiza(usuario);
		}
		this.usuarios = dao.listaTodos();
		this.usuario = new Usuario();
	}

	public void remove(Usuario usuario) {
		dao.remove(usuario);
		this.usuarios = dao.listaTodos();
	}
}
