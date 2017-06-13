<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Venda de Produtos - Alisson</title>
		
		<link href="css/bootstrap.css" rel="stylesheet">
		<link href="css/login.css" rel="stylesheet">
		<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
		
		<script src="js/jquery.js" type="text/javascript"></script>
		<script src="js/jquery.maskedinput.js" type="text/javascript"></script>
		<script src="js/jsProgB.js" type="text/javascript"></script>
				
	</head>
	
	<body>
		<div class="container">
		
				<!--  
				<select name="opcoes" id="opcoes" onChange="Redireciona(this.value)" class="form-control">
	                <option value="#">Escolher Produto</option>
	                <option value="mvc?logica=RedirecionaProduto">Batata</option>
	    		</select>
				-->
			
			<br>
			<div class="row">
				<div class="col-lg-5">
					<center><img src="http://sites.multiweb.ufsm.br/sisint/images/banner.png" 
					alt="Sistemas para Internet" height="125" width="250"></center>
				</div>
				<div class="col-lg-6">
					<br><br>
					<h3>Sistema de Vendas WEB - Alisson</h3>
				</div>
			</div>
			
			<hr style="border-color: black;">
			
			<div class="row">
				<div class="col-md-12">
					<center><a href="index.jsp">Index</a></center>
					<center><a href="mvc?logica=RedirecionaProduto">Cadastrar Produto</a></center>
					<center><a href="mvc?logica=RedirecionaFornecedor">Cadastrar Fornecedor</a></center>
					<center><a href="mvc?logica=RedirecionaVenda">Venda de Produtos</a></center>
				</div>
			</div>
						
				<hr style="border-color: black;">
				
					<div class="row">
						<div class="col-lg-6">
							<p style="font-size: 15px">Cliente</p>
							<select form="InserirVenda" name="clientes" id="clientes" class="form-control">
								<c:forEach var="cliente" items="${clientes}">
									<option value="${cliente.codigo},${cliente.nome}">${cliente.nome}</option> 
								</c:forEach>	
							</select>
							
						</div>
						<div class="col-lg-6">
							<p style="font-size: 15px">Data</p>
							<input form="InserirVenda" type="text" class="form-control" name="data" placeholder="Data" id="data" required>
						</div>
					</div>
					
					<hr style="border-color: black;">
					<br>
					<hr style="border-color: black;">
					<div class="row">
						<div class="col-lg-12">
							<h1>Produto:</h1>
						</div>
					</div>
					
					<div class="row">
							<div class="col-lg-6">
								<p style="font-size: 15px">Descrição</p>
								
								<select name="produtos" id="produtos" class="form-control" onChange="Valor(this.value)">
									<c:forEach var="produto" items="${produtos}">
										<option value="${produto.codigo},${produto.descricao},${produto.preco}">${produto.descricao}</option> 
									</c:forEach>	
								</select>
								
								<br>
								
								<p style="font-size: 15px">Preço Unitário: <b>R$<span id="precoUnitario" style="font-size: 15px"></span></b></p>
								<br>
								<p style="font-size: 15px">Código do Produto <b><span id="descricao" style="font-size: 15px"></span>:
								<span id="codigo" style="font-size: 15px"></span></b></p>
							
							</div>
							
							<div class="col-lg-6">
								<p style="font-size: 15px">Quantidade</p>
								<input type="text" class="form-control" name="quantidade" placeholder="Qtda" id="quantidade" onblur="calcular()" required>
								<br>
								<p style="font-size: 15px">Total do Item: <b> R$<span id="totalItem" style="font-size: 15px"></span></b></p>
								<br><br>
							</div>
					</div>
					<div class="row">
						<div class="col-md-offset-11">
							<button class="btn btn-success btn-sm" type="button"  onclick="adicionar()">Adicionar</button>
						</div>
					</div>
					<hr style="border-color: black;">
			<br>
			
			<div class="row">
				<div class="col-lg-12">
					<h1>Itens:</h1>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<p>Lista da Tabela: <b><span id="linhas">0</span></b></p>
					<table class="text-center table table-bordered table-responsive" id="produtos-tabela">
							<thead>
								<tr>
									<th class="text-center">Código do Produto </th>
									<th class="text-center">Descrição </th>
									<th class="text-center">Quantidade </th>
									<th class="text-center">Total R$ </th>
									<th class="text-center">Ação </th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-offset-9">
					<p style="font-size: 15px">Total da Venda: <b>R$: <span id="total" style="font-size: 15px"></span></b></p>				
				</div>
			</div>
			<div class="row">
				<div class="col-lg-offset-11">
					<br>
					<form id="InserirVenda" action="mvc?logica=InserirVenda" method="post">
						<button class="btn btn-danger btn-sm" type="submit">Finalizar Venda</button>	
					</form>		
				</div>
			</div>	
			
			<hr style="border-color: black;">
			
			<div class="row">
				<div class="col-lg-12">
					<center><h4>@2016 Avaliação Internet B, CSI/UFSM - Alisson Trindade Souza</h4></center>		
				</div>
			</div>
			
		</div>
	</body>
	
</html>