<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Titulos dos produtos...</title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link                  href="${cssPath}/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<script src="${cssPath}/js/bootstrap.min.js"></script>
<style type="text/css">
        body{
            padding: 60px 0px;
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



<!-- abaixo podemos ver que o form, esta acrescido do form:form ou seja, podemos usar o form do spring para gerennciar erros e regras de campos do lado do servidor 
	podemos ver tambem o uso do enctype para avisar ao action que teremos um upload de arquivos


--> 

<div class="container">

	<h1>Cadastro de Novos Produtos</h1>
	
	<form:form action= "${s:mvcUrl('PC#grava').build()}" commandName="produto" enctype="multipart/form-data">
	           
		<div class="form-group">
			<label>Titulo</label>
			 <form:input path="titulo"  cssClass="form-control"/>
			<form:errors path="titulo" /> <!-- form:erros linka com o arquivo message.properties -->
			 
		</div>
		<div class="form-group">
			<label>Descrição</label>
			<form:textarea  path="descricao" cssClass="form-control" />
			<form:errors path="descricao" />
		</div>
		<div class="form-group">
			<label>Paginas</label>
			<form:input path="paginas" cssClass="form-control"/>
			<form:errors path="paginas" />
		</div>
		<div class="form-group">
			<label>Data de Lançamento</label>
			 <form:input path="dataLancamento" cssClass="form-control"/>
			<form:errors path="dataLancamento" />
		</div>
		
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
    		<div class="form-group">
		        <label>${tipoPreco}</label>
		    	  <!--   <input type="text" name="precos[${status.index}].valor" >
		        <input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}" >  -->
		        <form:input path="precos[${status.index}].valor" cssClass="form-control" /> 
          	    <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
   			 </div>
		</c:forEach>
			<div class="form-group">
				<label>Sumário</label>
				<input name="sumario" type="file" class="form-control"> 
			</div>
		
			<button type = "submit" class="btn btn-primary"> Cadastrar</button>	
				
	</form:form>
</div>

</body>
</html>