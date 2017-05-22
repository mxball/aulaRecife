package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Named
@RequestScoped
public class ProdutoBean {
	
	private Produto produto = new Produto();
	private List<Produto> produtos;
	@Inject
	private ProdutoDao pDao;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Transactional
	public String gravar(){
		if(produto.getId() == null){
			pDao.adiciona(produto);
		} else{
			pDao.atualiza(produto);
		}
		this.produtos = pDao.listaTodos();
		this.produto = new Produto();
		return "produtos?faces-redirect=true";
	}
	
	public List<Produto> getProdutos(){
		if(produtos == null){
			this.produtos = pDao.listaTodos();
		}
		return this.produtos;
	}
	
	@Transactional
	public void remover(Produto produto) {
		pDao.remove(produto);
		this.produtos = pDao.listaTodos();
	}
	
	public void cancela(){
		this.produto = new Produto();
	}
	
}
