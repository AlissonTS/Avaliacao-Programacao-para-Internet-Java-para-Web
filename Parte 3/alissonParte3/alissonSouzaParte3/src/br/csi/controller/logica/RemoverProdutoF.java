package br.csi.controller.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.dao.ProdutoDao;
import br.csi.model.dao.FornecedorDao;

public class RemoverProdutoF implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		System.out.println("\n--------------------------------------------------------------------------------------");
		System.out.println("dentro do remover ....");
		String codigo = rq.getParameter("codigo");
		
		new ProdutoDao().remover(Long.parseLong(codigo));
		
		rq.setAttribute("fornecedores", new FornecedorDao().getFornecedores());
		
		return "/WEB-INF/jsp/cadastraFornecedor.jsp";
	}
}	
	
