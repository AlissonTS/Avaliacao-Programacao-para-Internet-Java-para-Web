package br.csi.controller.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.dao.VendaDao;

public class RedirecionaVenda implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		System.out.println("\n--------------------------------------------------------------------------------------");
		System.out.println("dentro do redirecinamento ....");
		
		rq.setAttribute("clientes", new VendaDao().getClientes());
		rq.setAttribute("produtos", new VendaDao().getProdutos());
		 
		return "/WEB-INF/jsp/vendaProdutos.jsp";
	}

}
