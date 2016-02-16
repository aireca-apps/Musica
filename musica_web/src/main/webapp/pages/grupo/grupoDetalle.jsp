<%@page import="com.ipartek.formacion.proyecto.pojo.Estilo"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.proyecto.Constantes"%>
<%@page import="com.ipartek.formacion.proyecto.pojo.Grupo"%>
<%@page import="java.util.Calendar"%>
<%@include file="/includes/head.jsp"%>
<link href="css/datepicker.css" rel="stylesheet">

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<%
				//recoger "atributo persona de la request
					Grupo per = (Grupo) request.getAttribute("grupo");
					boolean isNew = (per.getId() == -1);
			%>
			<h1 class="page-header"><%=per.getNombre()%></h1>
		</div>

		<!-- Formularios -->
		<form method="post" id="grupo-detalle" action="musica/grupos">

			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"
					style="margin: 10px 0;">ID</label>
				<div class="col-sm-10">
					<label for="id" class="col-sm-2 control-label"
						style="margin: 10px 0;"><%=per.getId()%></label>
				</div>
			</div>

			<div class="form-group">
				<label for="estilo" class="col-sm-2 control-label"
					style="margin: 10px 0;">Estilo</label>
				<div class="col-sm-10">
					<select class="form-control" name="estilo" 
						style="margin: 10px 0;">
						<%
							for (Estilo estilo : (ArrayList<Estilo>) request.getAttribute("estilos")){
						%>
						<option value="<%=estilo.getId()%>"
							<%try{ if (estilo.getNombre().equals(per.getEstilo().getNombre())) {%>
							selected <%}}catch(Exception e){}%> style="margin: 10px 0;">
							<%=estilo.getNombre()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="nombre" class="col-sm-2 control-label"
					style="margin: 10px 0;">Nombre</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="nombre" name="nombre" placeholder="Escribe tu nombre"
						value="<%=per.getNombre()%>" required
						style="margin: 10px 0;" autofocus>
					<span id="nombre-error" style="color: tomato; display: none;"><i class="fa fa-times"></i> El Nombre introducido ya existe.</span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="componentes" class="col-sm-2 control-label"
					>Componentes</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="componentes" rows="3" name="componentes"
						placeholder="Componentes" style="margin: 10px 0;"><%=per.getComponentes()%></textarea>
				</div>
			</div>

			<div class="form-group">
				<label for="fechaInicio" class="col-sm-2 control-label"
					style="margin: 10px 0;">Fecha de Inicio</label>
				<div class="col-sm-10">
					<%
						GregorianCalendar calendar = new GregorianCalendar(1900, 0, 1);
						Date fechaInicio = new Date(calendar.getTimeInMillis());
						if (per.getFechaInicio().equals(fechaInicio)) {
					%>
					<input class="form-control col-xl-6" type="text" id="fechaInicio" name="fechaInicio" placeholder="Ejem.: 1982-08-10"
						required
						pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
						style="margin: 10px 0;">
					<% 
					} else {
					%>
					<input type="text" name="fechaInicio" id="fechaInicio" 
						class="form-control"
						value="<%=per.getFechaInicio()%>" required
						pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
						style="margin: 10px 0;">
					<%
						}
					%>
				</div>
			</div>
			
			<div class="form-group">
				<label for="fechaFin" class="col-sm-2 control-label"
					style="margin: 10px 0;">Fecha de Separación</label>
				<div class="col-sm-10">
					<%
					if(per.getFechaFin() == null || per.getFechaFin().equals(fechaInicio)){
					%>
					<input class="form-control col-xl-6" type="text" id="fechaFin" name="fechaFin" placeholder="Ejem.: 1982-08-10"
						style="margin: 10px 0;">
					<% 
					} else {
					%>
					<input type="text" name="fechaFin" id="fechaFin" 
						class="form-control"
						value="<%=per.getFechaFin()%>" required
						style="margin: 10px 0;">
					<%
						}
					%>
				</div>
			</div>
						
			<input type="hidden" name="id" value="<%=per.getId()%>">
			<input type="hidden" name="op" value="<%=Constantes.OP_MODIFICAR%>">

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<%
						if (isNew) {
					%>
					<button type="submit" id="enviar" class="btn btn-primary">Crear</button>
					<%
						} else {
					%>
					<button type="submit" id="enviar" class="btn btn-primary">Modificar</button>
					<button type="button" class="btn btn-danger" data-toggle="modal"
						data-target="#eliminar">Eliminar</button>
					<%
						}
					%>
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
							grupo</h4>
					</div>
					<div id="nuevaAventura" class="modal-body">
						<form method="post" action="musica/grupos">
							<div class="form-group">
								<input type="hidden" name="op"
									value="<%=Constantes.OP_ELIMINAR%>"> <input
									type="hidden" name="id" value="<%=per.getId()%>"> <label
									for="aviso">¿Está seguro que desea eliminar al grupo?
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

<script src="js/bootstrap/bootstrap-datepicker.js"></script>
<script>
$(document).ready(function() {	
	var checkout = $('#fechaInicio').datepicker({
        format: 'yyyy-mm-dd'
    }).on('changeDate', function(ev) {
		checkFields();
    });
	
	var checkout2 = $('#fechaFin').datepicker({
        format: 'yyyy-mm-dd'
    }).on('changeDate', function(ev) {
		checkFields();
    });
	   
	checkFields();
	
	$('#nombre').focusout(function() {
		if ( $(this).val().trim() != "" )
			checkUserData( 'nombre', $(this).val() );
	});
          
	$('input').keyup(function() {
		checkFields();
	});
	
	function checkFields() {
		if( $('#nombre').val().trim() == "" ) console.log("Peta en nombre");
		if( $('#fechaInicio').val().trim() == "" ) console.log("Peta en fecha");
		
		if (
			$('#nombre').val().trim() == "" ||
			$('#fechaInicio').val().trim() == "" 
		) {
			$('#enviar').prop('disabled', true);
		} else {
			$('#enviar').prop('disabled', false);
		}
	}
	
	function checkUserData(campo, valor) {
		$.ajax({
			  url: "checkuser",
			  dataType: "json",
			  data: { p1: campo, p2: valor },
			}).done(function(data) {
				if ( data == true && $('#'+campo).val() != $('#'+campo+'-hid').val() ) {
			  		$('#'+campo).css({borderColor: "tomato"});
			  		$('#'+campo+'-error').show();
			  		$('#enviar').prop('disabled', true);
				} else {
			  		$('#'+campo).css({borderColor: ""});
			  		$('#'+campo+'-error').hide();
				}
			});
	}
});
</script>

<%@include file="/includes/footer.jsp"%>