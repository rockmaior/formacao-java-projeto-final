<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Editar Roles de Usuários">


	<div class="container">
		<h1>Cadastro de permissões para ${ usuario.nome}</h1>
		
<%-- 		<form:form action="${s:mvcUrl('UC#atualizarRoles').build() }" method="post" commandName="usuario"> --%>

			<form:form action="" method="post" commandName="usuario">
			
			<div class="form-group">
				<form:label path="roles">Permissões: </form:label>
				<form:checkboxes path="roles"  items="${roles}" cssClass="form-control"/>
				<form:errors path="roles" />
			</div>
			
			<button type="submit" class="btn btn-primary">Atualizar</button>
		</form:form>
	</div>
</tags:pageTemplate>