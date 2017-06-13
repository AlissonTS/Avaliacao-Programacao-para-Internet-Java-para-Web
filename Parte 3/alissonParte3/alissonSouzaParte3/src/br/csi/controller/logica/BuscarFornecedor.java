package br.csi.controller.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.dao.FornecedorDao;

public class BuscarFornecedor implements Logica {

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		
		System.out.println("--------------------------------------------------------------------------------------");
		String codigo = rq.getParameter("codigo");
		System.out.println("Código do Fornecedor: "+codigo);
		
		rq.setAttribute("fornecedor", new FornecedorDao().getForn(Long.parseLong(codigo)));
		rq.setAttribute("fornecedores", new FornecedorDao().getFornecedores());
		
		return "/WEB-INF/jsp/cadastraFornecedor.jsp";
	}

}
