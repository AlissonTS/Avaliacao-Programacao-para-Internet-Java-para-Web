<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Cadastro de Fornecedores - Alisson</title>
			<link href="css/bootstrap.css" rel="stylesheet">
			<link href="css/login.css" rel="stylesheet">
			<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
		</head>
		
		<body>
			<div class="container">
				<br>
				<center><img src="http://sites.multiweb.ufsm.br/sisint/images/banner.png" alt="Sistemas para Internet" height="200" width="600"></center>
				<center><h2>Aplicação Programação para Internet B</h2></center>
				<center><h2>Alisson Trindade Souza - 201321762</h2></center>
				<center><h2>Página de Cadastro - Fornecedor</h2></center>
				<br>
				<center><a href="index.jsp">Index</a></center>
				<center><a href="mvc?logica=RedirecionaProduto">Cadastrar Produto</a></center>
				<center><a href="mvc?logica=RedirecionaFornecedor">Cadastrar Fornecedor</a></center>
				<center><a href="mvc?logica=RedirecionaVenda">Venda de Produtos</a></center>
				
				<form action="mvc?logica=InserirFornecedor" method="post">
					<hr style="border-color: black;">
					
					<c:if test="${not empty msg}">
						<h2 style="text-align: center">${msg}!</h2>
						<hr style="border-color: black;">
					</c:if>
					
					<c:if test="${not empty fornecedor}">
						<h2 style="text-align: center;">Alterar Fornecedor: ${fornecedor.razaoS}</h2>
						<p><input type="hidden" name="codigo" value="${fornecedor.codigo}" /></p>
					</c:if>
					
					<c:if test="${empty fornecedor}">
						<center><h2>Adicionar Fornecedor:</h2></center>
					</c:if>
					
					<hr style="border-color: black;">
					<div class="form-group">
						<center><p><label for="descricao">Razão Social: </label><input type="text"
							id="descricao" name="descricao"
							value="${fornecedor.razaoS}" placeholder="Nome do Fornecedor"></p></center>
					</div>
					
					<br>
					<p style="text-align: center;"><button type="submit">Adicionar Fornecedor >></button></p>
					<br>
					<hr style="border-color: black;">
					
				</form>	
				
				<br>
				
				<div class="container">
					<c:if test="${not empty produtoF}">
					
							<h2 style="text-align: center">Fornecedor: ${f.codigo} - ${f.razaoS}</h2>	
							<h2 style="text-align: center">Lista de Produtos: </h2>
							<table class="text-center table table-bordered table-responsive">
								<thead>
									<tr>
										<th class="text-center">Código </th>
										<th class="text-center">Descrição </th>
										<th class="text-center">Preço </th>
										<th class="text-center"><i class="fa fa-cog"></i></th>
									</tr>
								</thead>
								<tbody>
									
									<c:forEach var="produto" items="${produtoF}">
										<tr>
											<td>${produto.codigo}</td>
											<td>${produto.descricao}</td>
											<td>${produto.preco}</td>
											
											<td><a class="btn btn-success"
												href="mvc?logica=BuscarProduto&&codigo=${produto.codigo}"
												title="Alterar Produto"><i class="fa fa-refresh"></i></a> <i
												class="fa fa-arrows-h"></i> <a class="btn btn-danger"
												href="mvc?logica=RemoverProdutoF&&codigo=${produto.codigo}"
												title="Deletar Produto"><i class="fa fa-trash"></i></a></td>
										</tr>
									</c:forEach>
									
								</tbody>
							</table>	
					</c:if>
					
					<br>
					<h2 style="text-align: center">Lista de Fornecedores: </h2>
					<table class="text-center table table-bordered table-responsive">
						<thead>
							<tr>
								<th class="text-center">Código </th>
								<th class="text-center">Razão Social </th>
								<th class="text-center">Alterar/Excluir </th>
								<th class="text-center"><i class="fa fa-cog"></i></th>
							</tr>
						</thead>
						<tbody>
							
							<c:forEach var="forn" items="${fornecedores}">
								<tr>
									<td>${forn.codigo}</td>
									<td>${forn.razaoS}</td>
									
									<td><a class="btn btn-success"
												href="mvc?logica=BuscarFornecedor&&codigo=${forn.codigo}"
												title="Alterar Fornecedor"><i class="fa fa-refresh"></i></a> <i
												class="fa fa-arrows-h"></i> <a class="btn btn-danger"
												href="mvc?logica=RemoverFornecedor&&codigo=${forn.codigo}"
												title="Deletar Fornecedor"><i class="fa fa-trash"></i></a></td>
									
									<td><a class="btn btn-success"
									href="mvc?logica=BuscarProdutosF&&codigo=${forn.codigo}"
									title="Listar Produtos"><i class="fa fa-eye"></i></a></td>
								</tr>	
							</c:forEach>
							
						</tbody>
					</table>
				</div>
				<br>
			
			</div>
		</body>
</html>