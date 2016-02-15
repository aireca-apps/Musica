<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page
	import="com.ipartek.formacion.backoffice.Constantes"%>
<%@page import="com.ipartek.formacion.backoffice.pojo.Rol"%>
<%@include file="/includes/head.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">

			<%	//La línea de abajo equivaldría a: Rol pojo = (Rol) request.getAttribute("pojo");%>
			<c:set var="pojo" value="${requestScope.pojo}" scope="request" />
			<%	//La línea de abajo equivaldría a: boolean isNew = (pojo.getId() == -1);%>
			<c:set var="isNew" value="${pojo.id == -1 ? true: false}" />
			
			<h1 class="page-header">${pojo.nombre}</h1>
		</div>

		<!-- Formularios -->
		<form method="post" id="auxiliar-detalle" action="<%=Constantes.CONTROLLER_ROLES%>">

			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"
					style="margin: 10px 0;">ID</label>
				<div class="col-sm-10">
					<label for="id" class="col-sm-2 control-label"
						style="margin: 10px 0;">${pojo.id}</label>
				</div>
			</div>

			<div class="form-group">
				<label for="nombre" class="col-sm-2 control-label"
					style="margin: 10px 0;">Nombre</label>
				<div class="col-sm-10">
					<input class="form-control" id="nombre" type="text" name="nombre" placeholder="Escribe tu nombre" max="50"
						value="${pojo.nombre}" required
						pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" style="margin: 10px 0;"
						autofocus>
				</div>
			</div>

			<div class="form-group">
				<label for="codigo"class="col-sm-2 control-label"
					style="margin: 10px 0;">Código</label>
				<div class="col-sm-10">
					<input class="form-control" id="codigo" type="text" name="codigo" placeholder="Escribe el código"
						value="${pojo.codigo}" required style="margin: 10px 0;">
				</div>
			</div>
			
			<div class="form-group">
				<label for="observaciones" class="col-sm-2 control-label"
					style="margin: 80px 0;">Descripción</label>
				<div class="col-sm-10">
					<textarea class="form-control" cols="20" rows="4" max="255" name="descripcion"
						placeholder="Escribe algo" style="margin: 10px 0;">${pojo.descripcion}</textarea>
				</div>
			</div>


			<input type="hidden" name="id" value="${pojo.id}"> <input
				type="hidden" name="nombre" value="${pojo.nombre}"> <input
				type="hidden" name="codigo" value="${pojo.codigo}"> <input
				type="hidden" name="descripcion" value="${pojo.descripcion}"><input
				type="hidden" name="op"
				value="<%=Constantes.OP_MODIFICAR%>">
				
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				
				<c:if test="${isNew }">					
					<button id="enviar" type="submit" class="btn btn-primary">Crear</button>
				</c:if>
				<c:if test="${!isNew }">					
					<button id="enviar" type="submit" class="btn btn-primary">Modificar</button>
					<button type="button" class="btn btn-danger" data-toggle="modal"
						data-target="#eliminar">Eliminar</button>
				</c:if>				
				</div>
			</div>
		</form>
		
		<!-- Pop Up de eliminación-->
		<div class="modal fade" id="eliminar" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Cerrar</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Eliminación de
							registro</h4>
					</div>
					<div id="nuevaAventura" class="modal-body">
						<form method="post" action="back/roles">
							<div class="form-group">
								<input type="hidden" name="op"
									value="4"> <input
									type="hidden" name="id" value="${pojo.id}"> <label
									for="aviso">¿Está seguro que desea eliminar el registro?
									Esta acción no se puede deshacer.</label>
								<button type="submit" class="btn btn-danger" id="eliminacion"
									style="margin: 10px 0;">Eliminar</button>
								<button type="button" class="btn btn-success"
									data-dismiss="modal" style="margin: 10px 0;">Cerrar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- fin Pop Up de eliminación-->
	</div>
	
	<!-- END <div class="row"> -->
</div>
<!-- END <div id="page-wrapper"> -->

<script>
$(document).ready(function() {
	checkFields();
	
	$('input').keyup(function() {
		checkFields();
	});
	
	function checkFields() {
		if (
			$('#nombre').val().trim() == "" ||
			$('#codigo').val().trim() == ""
		) {
			$('#enviar').prop('disabled', true);
		} else {
			$('#enviar').prop('disabled', false);
		}
	}
});
</script>


<%@include file="/includes/footer.jsp"%>