<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Lista de Usuarios">
<c:url value="/resources/imagens" var="imgPath" />

	<div class="container">
		<p class="nav-item"><a href="${s:mvcUrl('UC#form').build()}">Novo Usuário</a></p>
		<h1>Lista de Usuarios</h1>
		<p>${sucesso}</p>
		<p>${falha}</p>

		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
				<th></th>
			</tr>
			<c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome }</td>
					<td>${usuario.email }</td>
					<td>
					<c:forEach var="role" items="${usuario.roles }" varStatus="s" >
						${role}
					</c:forEach>
					</td>
					
					<td><a href="${s:mvcUrl('UC#detalhe').arg(0, usuario.id).build()}">
							<img src="${imgPath}/adicionar.png" alt="Editar"> 
						</a>
					</td>
				</tr>
				
			</c:forEach>
		</table>
	</div>
</tags:pageTemplate>