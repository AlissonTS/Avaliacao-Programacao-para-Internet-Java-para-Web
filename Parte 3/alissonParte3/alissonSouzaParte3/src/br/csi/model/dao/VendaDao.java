package br.csi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.csi.model.Cliente;
import br.csi.model.Item_Venda;
import br.csi.model.Produto;
import br.csi.model.Venda;
import br.csi.model.util.ConectarPostGresFactory;

/*
create table produto ( 
	codigo serial not null, 
	descricao varchar(40) not null, 
	preco float not null, 
	primary key (codigo));

create table fornecedor ( 
	codigo serial not null, 
	razaosocial varchar(40) not null, 
	primary key (codigo));

create table fornecprod (
	codigoprod integer, 
	codigofornec integer, 
	primary key (codigoprod, codigofornec), 
	FOREIGN KEY(codigoprod) REFERENCES produto(codigo), 
	FOREIGN KEY(codigofornec) REFERENCES fornecedor(codigo));

create table cliente ( 
	codigo serial not null, 
	nome varchar(40) not null, 
	primary key (codigo));

create table venda ( 
	codigo serial not null, 
	id_cliente integer not null, 
	dataVenda varchar(11), 
	primary key (codigo));	

create table item_venda (
	id_venda integer not null, 
	id_produto integer not null,
	qtd integer not null, 
	totalitem float not null, 
	);			
		
	insert into produto (descricao, preco) values ('vinho tinto cabernet sauvignon', 21.75);
	insert into produto (descricao, preco) values ('vinho tinto carmenere', 13.40);
	insert into produto (descricao, preco) values ('carvão 5kg', 8.50);
	insert into produto (descricao, preco) values ('carvão 3kg', 5.70);
	
	insert into fornecedor (razaosocial) values ('FBI bebidas');
	insert into fornecedor (razaosocial) values ('Carva Fornos LTDA');
	
	insert into fornecprod (codigoprod, codigofornec) values (1,1);
	insert into fornecprod (codigoprod, codigofornec) values (2,1);
	insert into fornecprod (codigoprod, codigofornec) values (3,2);
	insert into fornecprod (codigoprod, codigofornec) values (4,2);

	insert into cliente (nome) values ('jocelaine');
	insert into cliente (nome) values ('nego nelson');
	insert into cliente (nome) values ('cicatriz');

	insert into venda (id_cliente, dataVenda) values (1,'03/04/2016');
	insert into venda (id_cliente, dataVenda) values (2,'03/04/2016');
	
	insert into item_venda (id_venda, id_produto, qtd, totalitem) values (1,1,2,43.5);
	insert into item_venda (id_venda, id_produto, qtd, totalitem) values (1,2,1,14.4);
	insert into item_venda (id_venda, id_produto, qtd, totalitem) values (2,2,10,140.4);
*/

public class VendaDao {
	
		/* ------------------------------------------------------------------------------------------------- */
		public List<Cliente> getClientes(){
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println("dentro do getClientes()");
			
			try{
					
				PreparedStatement stmt =  
						ConectarPostGresFactory.getConexao().prepareStatement("select * from Cliente");
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					Cliente t = new Cliente();
					t.setCodigo(rs.getLong("codigo"));
					t.setNome(rs.getString("nome"));
					System.out.println("Código do Cliente: "+t.getCodigo());
					clientes.add(t);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return clientes;
		}
		/* ------------------------------------------------------------------------------------------------- */
		/* ------------------------------------------------------------------------------------------------- */
		public List<Produto> getProdutos(){
			ArrayList<Produto> produtos = new ArrayList<Produto>();
			
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println("dentro do getProdutos()");
			
			try{
					
				PreparedStatement stmt =  
						ConectarPostGresFactory.getConexao().prepareStatement("select * from Produto");
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					Produto t = new Produto();
					t.setCodigo(rs.getLong("codigo"));
					t.setDescricao(rs.getString("descricao"));
					t.setPreco(rs.getFloat("preco"));
					System.out.println("Código do Produto: "+t.getCodigo());
					produtos.add(t);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return produtos;
		}
		/* ------------------------------------------------------------------------------------------------- */
		/* ------------------------------------------------------------------------------------------------- */
		public boolean adiciona(Venda v, List<Item_Venda> lista){
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println("dentro do adiciona()");
			
			Connection c = null;
			PreparedStatement stmt = null;
			boolean retorno = false;
			
			try{
				System.out.println("......... vai adicionar .............");
				
				c = ConectarPostGresFactory.getConexao();
				String sql = "";
				
				sql = "INSERT INTO VENDA (ID_CLIENTE, DATAVENDA) "
						+ " values (?,?)";
				stmt = c.prepareStatement(sql);	
				stmt.setLong(1, v.getCodigo());
				stmt.setString(2, v.getData());
				
				stmt.execute();	
				
				long ret = pesquisaVenda();
				
				System.out.println("--------------------------------------------------------------------------------------");
				for(int i=0; i<lista.size(); i++){
					Item_Venda ven = lista.get(i);
					
					sql = "insert into item_venda (id_venda, id_produto, qtd, totalitem) values (?,?,?,?)";
					stmt = c.prepareStatement(sql);	
					stmt.setLong(1, ret);
					stmt.setLong(2, ven.getId_produto());
					stmt.setInt(3, ven.getQtd());
					stmt.setFloat(4, ven.getTotalItem());
					
					System.out.println("Código da Venda: "+ret+", Código do Produto: "+ven.getId_produto()+", "
							+ "Quantidade: "+ven.getQtd()+", Total: R$ "+ven.getTotalItem());
					
					stmt.execute();	
					retorno = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
				retorno = false;
				return retorno;
			}
			
			return retorno;
		}
		/* ------------------------------------------------------------------------------------------------- */
		/* ------------------------------------------------------------------------------------------------- */
		public long pesquisaVenda() throws ClassNotFoundException, SQLException{
			Connection c = ConectarPostGresFactory.getConexao();
			
			String sql = "select MAX(codigo) as id from venda";
			PreparedStatement stmtPre = c.prepareStatement(sql);
			ResultSet rs= stmtPre.executeQuery();
			
			rs.next();
			long idProd = rs.getInt("id");
			
			return idProd;	
		}
		/* ------------------------------------------------------------------------------------------------- */
		/* ------------------------------------------------------------------------------------------------- */
}
