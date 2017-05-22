package br.com.caelum.notasfiscais.util;

import javax.enterprise.inject.Produces;

public class EmailFactory {

	@Produces
	@EmailComercial
	public String emailComercial = "comercial@empresa.com";
	
	@Produces
	@EmailFinanceiro
	public String emailFinanceiro = "financeiro@empresa.com";
}
