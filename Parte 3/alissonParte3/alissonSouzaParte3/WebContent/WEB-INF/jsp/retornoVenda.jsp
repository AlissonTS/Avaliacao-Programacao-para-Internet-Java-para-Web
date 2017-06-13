<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Venda Concretizada - Alisson</title>
			<link href="css/bootstrap.css" rel="stylesheet">
			<link href="css/login.css" rel="stylesheet">
			<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
		</head>
		
		<body>
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<br>
						<center><img src="http://sites.multiweb.ufsm.br/sisint/images/banner.png" alt="Sistemas para Internet" height="200" width="600"></center>
						<center><h2>Aplicação Programação para Internet B</h2></center>
						<center><h2>Alisson Trindade Souza - 201321762</h2></center>
						<br>
						<center><a href="index.jsp">Página Inicial</a></center>
						<center><a href="mvc?logica=RedirecionaProduto">Cadastrar Produtos</a></center>
						<center><a href="mvc?logica=RedirecionaFornecedor">Cadastrar Fornecedor</a></center>
						<center><a href="mvc?logica=RedirecionaVenda">Venda de Produtos</a></center>
						
						<br>
						<c:if test="${not empty msg}">
							<hr style="border-color: black;">
							<h1 style="text-align: center; font-size:20px">${msg}</h1>
							<hr style="border-color: black;">
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<hr style="border-color: black;">
						<c:if test="${not empty cliente && not empty data}">
							<center><h1 style="font-size:20px;">Cliente: ${cliente.codigo} - ${cliente.nome} -- Data da Venda: ${data}</h1></center>
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<hr style="border-color: black;">
						<c:if test="${not empty itens}">
							<center><h1 style="font-size:20px;">Itens:</h1></center>
							<br>
							
							<table class="text-center table table-bordered table-responsive" id="produtos-tabela">
								<thead>
									<tr>
										<th class="text-center">Código do Produto </th>
										<th class="text-center">Quantidade </th>
										<th class="text-center">Total R$ </th>
										<!-- <th class="text-center">Ação </th> -->
									</tr>
								</thead>
								<tbody>
									
									<c:forEach var="item" items="${itens}">
										<tr>	
											<td>${item.id_produto}</td>
											<td>${item.qtd}</td>
											<td>${item.totalItem}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${not empty total}">
								<center><h1 style="font-size:20px;">Total da Compra: R$ ${total}</h1></center>
							</c:if>
						</c:if>
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