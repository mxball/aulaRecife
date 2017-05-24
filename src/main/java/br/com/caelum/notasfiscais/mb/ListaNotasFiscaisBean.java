package br.com.caelum.notasfiscais.mb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.dataModel.DataModelNotasFiscais;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@Named
@RequestScoped
public class ListaNotasFiscaisBean {

	@Inject
	private DataModelNotasFiscais dataModelNotasFiscais;

	public LazyDataModel<NotaFiscal> getDataModelNotasFiscais() {
		return dataModelNotasFiscais;
	}
}
