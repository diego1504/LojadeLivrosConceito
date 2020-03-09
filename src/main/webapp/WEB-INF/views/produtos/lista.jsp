<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Titulos dos produtos...</title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link                  href="${cssPath}/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<script src="${cssPath}/js/bootstrap.min.js"></script>
<style type="text/css">
        body{
            padding-top: 60px;
        }
    </style>
</head>
<body>
<header>
	
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Casa do Codigo</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="${s:mvcUrl('PC#listar').build()}">Listar Produtos <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${s:mvcUrl('PC#form').build()}">Cadastro de Produtos</a>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" href="#">Disabled</a>
            </li>
          </ul>
          <form class="form-inline mt-2 mt-md-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
      </nav>
    </header>



<div class="container"> <!-- centralizar itens no meio da tela -->
	<h1>LISTA DE PRODUTOS QUE VOCE CADASTROU</h1>
	
	<p>${sucesso }</p>
	
	
	
	
	<table class="table table-bordered table-striped table-hover"> <!-- comando uso bootstrap, simpllificar uso de borda -->
		<tr> 
			<th>Titulo</th>
			<th>Descricao</th>
			<th>Paginas</th>
		<tr>

		<c:forEach items="${produtos }" var="produto">	
				<tr>
					<td>            
						<a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}"> ${produto.titulo }</a>
					</td>
					<td>${produto.descricao }</td>
					<td>${produto.paginas }</td> 
				<tr>
		</c:forEach>	
	</table>
</div>
</body>
</html>