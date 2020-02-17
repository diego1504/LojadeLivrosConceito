<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Titulos dos produtos...</title>
</head>
<body>
<!-- abaixo podemos ver que o form, esta acrescido do form:form ou seja, podemos usar o form do spring para gerennciar erros e regras de campos do lado do servidor --> 
	
	<form:form action= "${s:mvcUrl('PC#grava').build()}" commandName="produto">
		<div>
			<label>Titulo</label>
			 <form:input path="titulo" />
			<form:errors path="titulo" />
			 
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea rows="10" cols="20" path="descricao" />
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Paginas</label>
			<form:input path="paginas" />
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data de Lançamento</label>
			 <form:input path="dataLancamento" />
			<form:errors path="dataLancamento" />
		</div>
		
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
    		<div>
		        <label>${tipoPreco}</label>
		    	  <!--   <input type="text" name="precos[${status.index}].valor" >
		        <input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}" >  -->
		        <form:input path="precos[${status.index}].valor" /> 
          	    <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
   			 </div>
		</c:forEach>
			<button type = "submit"> Cadastrar</button>	
				
	</form:form>


</body>
</html>