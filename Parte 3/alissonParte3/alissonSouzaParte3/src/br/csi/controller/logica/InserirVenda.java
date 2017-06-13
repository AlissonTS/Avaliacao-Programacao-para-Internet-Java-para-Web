package br.csi.controller.logica;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.Cliente;
import br.csi.model.Item_Venda;
import br.csi.model.Venda;
import br.csi.model.dao.VendaDao;

/*
String cliente = rq.getParameter("clientes");
String data = rq.getParameter("data");
String codigo = rq.getParameter("codigo");
String quantidade = rq.getParameter("quantidade");
String total = rq.getParameter("total");
System.out.println("--------------------------------------------------------------------------------------");
System.out.println("Código do Produto ..."+codigo);

System.out.println(cliente);
System.out.println(data);
System.out.println(codigo);
System.out.println(quantidade);
System.out.println(total);


*/
public class InserirVenda implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp) {
		
		System.out.println("\n--------------------------------------------------------------------------------------");
		System.out.println(".......... dentro de inserir InserirVenda");
		
		rq.setAttribute("clientes", new VendaDao().getClientes());
		rq.setAttribute("produtos", new VendaDao().getProdutos());
		
		String cliente = rq.getParameter("clientes");
		String data = rq.getParameter("data");
		String[] item = rq.getParameterValues("item");
		
		String cli[] = cliente.split(Pattern.quote(","));
		
		Cliente c = new Cliente();
		
		c.setCodigo(Long.parseLong(cli[0]));
		c.setNome(cli[1]);
		
		System.out.println("Cliente: "+cliente);
		System.out.println("Data: "+data);
		
		ArrayList<Item_Venda> lista = new ArrayList<Item_Venda>();
		
		Venda venda = new Venda();
		
		venda.setCodigo(Long.parseLong(cli[0]));
		venda.setData(data);
		
		float total=0;
		
		for(String it : item){
			Item_Venda itemVenda = new Item_Venda();
			
			String elementos[] = it.split(Pattern.quote(","));
	
			itemVenda.setId_produto(Long.parseLong(elementos[0]));
			itemVenda.setQtd(Integer.parseInt(elementos[1]));
			itemVenda.setTotalItem(Float.parseFloat(elementos[2]));
			lista.add(itemVenda);
			
			total=total+itemVenda.getTotalItem();
			
			System.out.println("Item: "+it);
		}
		
		VendaDao v = new VendaDao();
		String pagina;
		
		try{
			boolean retorno = v.adiciona(venda, lista);
			
			if(retorno){
				rq.setAttribute("cliente", c);
				rq.setAttribute("total", total);
				rq.setAttribute("data", data);
				rq.setAttribute("itens", lista);
				
				rq.setAttribute("msg", "Venda Concretizada!");	
				pagina = "/WEB-INF/jsp/retornoVenda.jsp";
				return pagina;
			}else{
				rq.setAttribute("msg", "Problemas ao inserir Venda");
				pagina = "/WEB-INF/jsp/vendaProdutos.jsp";
				return pagina;
			}
			
		}catch(Exception e){
			rq.setAttribute("msg", "Problemas ao inserir Venda");
			e.printStackTrace();
			pagina = "/WEB-INF/jsp/vendaProdutos.jsp";
			return pagina;
		}
	}

}
