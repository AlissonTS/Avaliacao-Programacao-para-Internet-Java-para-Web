<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Página Principal - Index</title>
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
						
						<br><br>
						<c:if test="${not empty msg}">
							<hr style="border-color: black;">
							<h2 style="text-align: center">${msg}</h2>
							<hr style="border-color: black;">
						</c:if>
					</div>
				</div>
			</div>
		</body>
</html>