package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Named
@ConversationScoped
public class NotaFiscalBean implements Serializable{

	private static final long serialVersionUID = 3223736228670025720L;
	
	private NotaFiscal notaFiscal = new NotaFiscal();
	@Inject
	private NotaFiscalDao nDao;
	@Inject
	private ProdutoDao pDao;
	private Item item = new Item();
	private Long idProduto;
	
	@Inject
	private Conversation conversation;
	
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public void guardaItem(){
		Produto produto = pDao.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		
		item.setNotaFiscal(notaFiscal);
		notaFiscal.getItens().add(item);
		
		item = new Item();
		setIdProduto(null);
	}
	
	@Transactional
	public String gravar(){
		nDao.adiciona(notaFiscal);
		conversation.end();
		this.notaFiscal = new NotaFiscal();
		return "notaFiscal?redirect-true";
	}
	
	public String avancar(){
		if(conversation.isTransient()){
			this.conversation.begin();
		}
		return "item?faces-redirect=true"; 
	}
	
	public List<Produto> busca(String nome){
		return pDao.buscaPorNome(nome);
		
	}
}
