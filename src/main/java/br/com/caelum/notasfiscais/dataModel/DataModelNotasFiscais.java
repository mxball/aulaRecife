package br.com.caelum.notasfiscais.dataModel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

public class DataModelNotasFiscais extends LazyDataModel<NotaFiscal>{
	
	@Inject
	private NotaFiscalDao dao;
	
	@PostConstruct
	void inicializaTudo(){
		super.setRowCount(dao.contaTodos());
	}
	
	@Override
	public List<NotaFiscal> load(int inicio, int quantidade, 
			String campoOrdenacao, SortOrder sentidoOrdenacao, 
			Map<String, Object> filtros) {
		return dao.listaTodosPaginada(inicio, quantidade);
	}		

}
