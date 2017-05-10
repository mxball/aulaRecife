package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@ManagedBean
public class ProdutoBean {
	
	private Produto produto = new Produto();
	private List<Produto> produtos;;
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void gravar(){
		ProdutoDao produtoDao = new ProdutoDao();
		if(produto.getId() == null){
			produtoDao.adiciona(produto);
		} else{
			produtoDao.atualiza(produto);
		}
		this.produtos = produtoDao.listaTodos();
		this.produto = new Produto();
	}
	
	public List<Produto> getProdutos(){
		if(produtos == null){
			ProdutoDao produtoDao = new ProdutoDao();
			this.produtos = produtoDao.listaTodos();
		}
		return this.produtos;
	}
	
	public void remover(Produto produto) {
		ProdutoDao dao = new ProdutoDao();
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
	
	public void cancela(){
		this.produto = new Produto();
	}
	
}
