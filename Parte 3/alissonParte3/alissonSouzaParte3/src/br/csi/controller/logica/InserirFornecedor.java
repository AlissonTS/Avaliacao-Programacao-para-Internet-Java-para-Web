package br.csi.controller.logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.Fornecedor;
import br.csi.model.Produto;
import br.csi.model.dao.FornecedorDao;
import br.csi.model.dao.ProdutoDao;
import br.csi.model.Fornecedor;

public class InserirFornecedor implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		
			rq.setAttribute("fornecedores", new FornecedorDao().getFornecedores());
		
			System.out.println("\n--------------------------------------------------------------------------------------");
			System.out.println(".......... dentro de inserir InserirFornecedor");
			String descricao = rq.getParameter("descricao");
			String codigo = rq.getParameter("codigo");
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println("id parameter ..."+codigo);
			
			FornecedorDao fD = new FornecedorDao();
			
			String pagina = "/WEB-INF/jsp/cadastraFornecedor.jsp";
			
			Fornecedor u = new Fornecedor();
			
			u.setRazaoS(descricao);
			
			
			if(codigo == null){
				System.out.println("Novo fornecedor a ser criado");
				System.out.println("--------------------------------------------------------------------------------------");
			}else{
				u.setCodigo(Long.parseLong(codigo));
			}
			
			try {
				
				boolean retorno = fD.adiciona(u);
				if(retorno){
					rq.setAttribute("fornecedores", fD.getFornecedores());
				}else{
					rq.setAttribute("msg", "Problemas ao inserir Fornecedor");
				}
				
			} catch (Exception e) {		
				e.printStackTrace();
				rq.setAttribute("msg", "Problemas ao inserir Fornecedor");
				return pagina;
			}
			
			return pagina;//
	}

}
