<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Lista de Usuarios">

	<div class="container">
		<p class="nav-item"><a href="${s:mvcUrl('UC#form').build()}">Novo Usuário</a></p>
		<h1>Lista de Usuarios</h1>
		<p>${sucesso}</p>
		<p>${falha}</p>

		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
			</tr>
			<c:forEach items="${usuarios }" var="u">
				<tr>
					<td>${u.nome }</td>
					<td>${u.email }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</tags:pageTemplate>