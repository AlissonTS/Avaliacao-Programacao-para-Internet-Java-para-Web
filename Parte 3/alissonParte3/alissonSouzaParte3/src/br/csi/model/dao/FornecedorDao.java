package br.csi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.csi.model.Fornecedor;
import br.csi.model.Produto;
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
	totalitem float not null
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

public class FornecedorDao {
			
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public Fornecedor getForn(Long codigo){
				Fornecedor t = new Fornecedor();
				
				try{
					PreparedStatement stmt =  
							ConectarPostGresFactory.getConexao().prepareStatement("select * from Fornecedor where codigo=?");
					stmt.setLong(1, codigo);
					
					ResultSet rs = stmt.executeQuery();
					while(rs.next()){
						t.setCodigo(rs.getLong("codigo"));
						t.setRazaoS(rs.getString("razaosocial"));
						System.out.println("Código do Fornecedor: "+t.getCodigo());
					}
					System.out.println("--------------------------------------------------------------------------------------");
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return t;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public List<Produto> getProdutoF(Long codigo){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do getProdutoF()");
				
				ArrayList<Produto> produtos = new ArrayList<Produto>();
				
				try{
					
					PreparedStatement stmt =  
							ConectarPostGresFactory
							.getConexao().prepareStatement("select p.codigo, p.descricao, p.preco "
									+ "from fornecedor f, produto p, fornecprod fp "
									+ "where f.codigo = fp.codigofornec and p.codigo = fp.codigoprod and  f.codigo=?");
					stmt.setLong(1, codigo);
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()){
						Produto t = new Produto();
						t.setCodigo(rs.getLong("codigo"));
						t.setDescricao(rs.getString("descricao"));
						t.setPreco(rs.getFloat("preco"));
						System.out.println("Código do Produto: "+t.getCodigo());
						System.out.println("Código do Produto: "+t.getDescricao());
						System.out.println("Código do Produto: "+t.getPreco());
						
						produtos.add(t);
					}
				System.out.println("--------------------------------------------------------------------------------------");
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return produtos;
			}	
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public List<Fornecedor> getFornecedores(){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do getFornecedores()");
				
				ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
				try{
						
					PreparedStatement stmt =  
							ConectarPostGresFactory.getConexao().prepareStatement("select * from Fornecedor");
					
					ResultSet rs = stmt.executeQuery();
					while(rs.next()){
						Fornecedor t = new Fornecedor();
						t.setCodigo(rs.getLong("codigo"));
						t.setRazaoS(rs.getString("razaosocial"));
						System.out.println("Código do Fornecedor: "+t.getCodigo());
						fornecedores.add(t);
					}
					System.out.println("--------------------------------------------------------------------------------------");
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return fornecedores;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public boolean adiciona(Fornecedor t){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do adiciona Fornecedor()");
				
				Connection c = null;
				PreparedStatement stmt = null;
				boolean retorno = false;
				
				try {

					c = ConectarPostGresFactory.getConexao();
					String sql = "";
					
					if(t.getCodigo()<= 0){
						System.out.println("......... vai adicionar .............");
						
						sql = "INSERT INTO FORNECEDOR (RAZAOSOCIAL) "
								+ " values (?)";
						stmt = c.prepareStatement(sql);	
						stmt.setString(1, t.getRazaoS());
						
						stmt.execute();	
						
						System.out.println("--------------------------------------------------------------------------------------");
					}else{
						System.out.println("......... vai alterar .............");
						sql = "UPDATE FORNECEDOR SET RAZAOSOCIAL=?  "
								+ " WHERE codigo =?";
						stmt = c.prepareStatement(sql);								
						stmt.setString(1, t.getRazaoS());
						stmt.setLong(2, t.getCodigo());
						
						stmt.execute();
						
						System.out.println("--------------------------------------------------------------------------------------");
					}
					
					stmt.close();
					retorno = true;
					
				} catch (Exception e) {
					e.printStackTrace();
					return retorno;
					
				}	
				return retorno;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
			public boolean remover(Long codigo){
				System.out.println("--------------------------------------------------------------------------------------");
				System.out.println("dentro do remover()");
				
				boolean retorno = false;
				
				String sql;
				PreparedStatement stmt = null;
				Connection c = ConectarPostGresFactory.getConexao();		
				
				try {
					sql = "delete from fornecprod where codigofornec=?";
					stmt = c.prepareStatement(sql);
					stmt.setLong(1, codigo);
					stmt.execute();
					
					sql = "delete from fornecedor where codigo=?";
					stmt = c.prepareStatement(sql);
					stmt.setLong(1, codigo);
					stmt.execute();
					
					retorno = true;
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				
				return retorno;
			}
			/* ------------------------------------------------------------------------------------------------- */
			/* ------------------------------------------------------------------------------------------------- */
}
