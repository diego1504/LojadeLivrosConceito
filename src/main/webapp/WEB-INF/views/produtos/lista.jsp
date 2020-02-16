<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Titulos dos produtos...</title>
</head>
<body>
	<h1>LISTA DE PRODUTOS QUE VOCE CADASTROU</h1>
	
	<p>${sucesso }</p>
	
	<table> 
		<tr> 
			<td>Titulo</td>
			<td>Descricao</td>
			<td>Paginas</td>
		<tr>

		<c:forEach items="${produtos }" var="produto">	
				<tr>
					<td>${produto.titulo }</td>
					<td>${produto.descricao }</td>
					<td>${produto.paginas }</td> 
				<tr>
		</c:forEach>	
	</table>

</body>
</html>