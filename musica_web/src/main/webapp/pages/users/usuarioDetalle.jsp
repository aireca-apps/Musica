<%@page import="com.ipartek.formacion.backoffice.pojo.Rol"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.backoffice.Constantes"%>
<%@page import="com.ipartek.formacion.backoffice.pojo.Persona"%>
<%@page import="java.util.Calendar"%>
<%@include file="/includes/head.jsp"%>
<link href="css/datepicker.css" rel="stylesheet">




<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<%
				//recoger "atributo persona de la request
					Persona per = (Persona) request.getAttribute("persona");
					boolean isNew = (per.getId() == -1);
			%>
			<h1 class="page-header"><%=per.getNombre()%></h1>
		</div>

		<!-- Formularios -->
		<form method="post" id="usuario-detalle" action="back/usuarios">

			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"
					style="margin: 10px 0;">ID</label>
				<div class="col-sm-10">
					<label for="id" class="col-sm-2 control-label"
						style="margin: 10px 0;"><%=per.getId()%></label>
				</div>
			</div>

			<div class="form-group">
				<label for="rol" class="col-sm-2 control-label"
					style="margin: 10px 0;">Rol</label>
				<div class="col-sm-10">
					<select class="form-control" name="rol" 
						style="margin: 10px 0;">
						<%
							for (Rol rol : (ArrayList<Rol>) request.getAttribute("roles")){
						%>
						<option value="<%=rol.getId()%>"
							<%try{ if (rol.getNombre().equals(per.getRol().getNombre())) {%>
							selected <%}}catch(Exception e){}%> style="margin: 10px 0;">
							<%=rol.getNombre()%></option>
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
				<label for="dni" class="col-sm-2 control-label"
					style="margin: 10px 0;">DNI</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="dni" name="dni" placeholder="Escribe tu DNI"
						value="<%=per.getDni()%>" required style="margin: 10px 0;">
					<span id="dni-error" style="color: tomato; display: none;"><i class="fa fa-times"></i> El DNI introducido ya existe.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="pass" class="col-sm-2 control-label"
					style="margin: 10px 0;">Pass</label>
				<div class="col-sm-10">
					<input class="form-control" type="password" id="pass" name="pass" placeholder="Escribe tu clave"
						value="<%=per.getPass()%>" required style="margin: 10px 0;">
				</div>
			</div>

			<div class="form-group">
				<label for="observaciones" class="col-sm-2 control-label"
					>Puesto de trabajo</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="observaciones" rows="3" name="observaciones"
						placeholder="Escribe algo sobre ti" style="margin: 10px 0;"><%=per.getObservaciones()%></textarea>
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="col-sm-2 control-label"
					style="margin: 10px 0;">Email</label>
				<div class="col-sm-10">
					<input class="form-control" type="email" id="email" name="email" placeholder="Escribe tu correo"
						value="<%=per.getEmail()%>" required style="margin: 10px 0;">
					<span id="email-error" style="color: tomato; display: none;"><i class="fa fa-times"></i> El Email introducido ya existe.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="fecha" class="col-sm-2 control-label"
					style="margin: 10px 0;">Fecha de nacimiento</label>
				<div class="col-sm-10">
					<%
						GregorianCalendar calendar = new GregorianCalendar(1900, 0, 1);
						Date fechaNacimiento = new Date(calendar.getTimeInMillis());
						if (per.getFechaNacimiento().equals(fechaNacimiento)) {
					%>
					<input class="form-control col-xl-6" type="text" id="fecha" name="fecha" placeholder="Ejem.: 1982-08-10"
						required
						pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
						style="margin: 10px 0;">
					<% 
					} else {
					%>
					<input type="text" name="fecha" id="fecha" 
						class="form-control"
						value="<%=per.getFechaNacimiento()%>" required
						pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
						style="margin: 10px 0;">
					<%
						}
					%>
				</div>
			</div>
						
			<input type="hidden" name="id" value="<%=per.getId()%>">
			<input type="hidden" name="nombre-hid" id="nombre-hid" value="<%=per.getNombre()%>">
			<input type="hidden" name="dni-hid" id="dni-hid" value="<%=per.getDni()%>">
			<input type="hidden" name="pass-hid" value="<%=per.getPass()%>">
			<input type="hidden" name="email-hid" id="email-hid" value="<%=per.getEmail()%>">
			<input type="hidden" name="observaciones-hid" value="<%=per.getObservaciones()%>">
			<input type="hidden" name="fecha-hid" value="<%=per.getFechaNacimiento()%>">
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
							usuario</h4>
					</div>
					<div id="nuevaAventura" class="modal-body">
						<form method="post" action="back/usuarios">
							<div class="form-group">
								<input type="hidden" name="op"
									value="<%=Constantes.OP_ELIMINAR%>"> <input
									type="hidden" name="id" value="<%=per.getId()%>"> <label
									for="aviso">¿Está seguro que desea eliminar al usuario?
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
	var checkout = $('#fecha').datepicker({
        format: 'yyyy-mm-dd'
    }).on('changeDate', function(ev) {
		checkFields();
    });
    
	checkFields();
	
	$('#nombre').focusout(function() {
		if ( $(this).val().trim() != "" )
			checkUserData( 'nombre', $(this).val() );
	});
            
	$('#dni').focusout(function() {
		if ( $(this).val().trim() != "" )
			checkUserData( 'dni', $(this).val() );
	});

	$('#email').focusout(function() {
		if ( $(this).val().trim() != "" )
			checkUserData( 'email', $(this).val() );
	});

	$('input').keyup(function() {
		checkFields();
	});
	
	function checkFields() {
		if( $('#nombre').val().trim() == "" ) console.log("Peta en nombre");
		if( $('#pass').val().trim() == "" ) console.log("Peta en pass");
		if( $('#fecha').val().trim() == "" ) console.log("Peta en fecha");
		if( $('#email').val().trim() == "" ) console.log("Peta en email");
		if( $('#dni').val().trim() == "" ) console.log("Peta en dni");
		
		if (
			$('#nombre').val().trim() == "" ||
			$('#dni').val().trim() == "" ||
			$('#pass').val().trim() == "" ||
			$('#fecha').val().trim() == "" ||
			$('#email').val().trim() == ""
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