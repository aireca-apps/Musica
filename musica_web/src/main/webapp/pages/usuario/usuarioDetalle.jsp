<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="com.ipartek.formacion.proyecto.Constantes"%>
<%@page import="com.ipartek.formacion.proyecto.pojo.Usuario"%>
<%@page import="java.util.Calendar"%>

<link href="css/datepicker.css" rel="stylesheet">
<%
	//recoger "atributo persona de la request
	Usuario per = (Usuario) request.getAttribute("usuario");
	boolean isNew = (per.getId() == -1);
	if (!isNew) {
%>
<%@include file="/includes/head.jsp"%>
<%
	} else {
%>
<%@include file="/includes/headUsuarioNuevo.jsp"%>
<%
	}
%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><%=per.getNick()%></h1>
		</div>

		<!-- Formularios -->
		<form method="post" id="usuario-detalle"
			action="<%=Constantes.CONTROLLER_USUARIOS%>">

			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"
					style="margin: 10px 0;">ID</label>
				<div class="col-sm-10">
					<label for="id" class="col-sm-2 control-label"
						style="margin: 10px 0;"><%=per.getId()%></label>
				</div>
			</div>

			<div class="form-group">
				<label for="nick" class="col-sm-2 control-label"
					style="margin: 10px 0;">Nick</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="nick" name="nick"
						placeholder="Escribe tu nick" value="<%=per.getNick()%>" required
						style="margin: 10px 0;" autofocus> <span id="nick-error"
						style="color: tomato; display: none;"><i
						class="fa fa-times"></i> El Nick introducido ya existe.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="col-sm-2 control-label"
					style="margin: 10px 0;">Email</label>
				<div class="col-sm-10">
					<input class="form-control" type="email" id="email" name="email"
						placeholder="Escribe tu correo" value="<%=per.getEmail()%>"
						required style="margin: 10px 0;"> <span id="email-error"
						style="color: tomato; display: none;"><i
						class="fa fa-times"></i> El Email introducido ya existe.</span>
				</div>
			</div>

			<div class="form-group">
				<label for="pass" class="col-sm-2 control-label"
					style="margin: 10px 0;">Pass</label>
				<div class="col-sm-10">
					<input class="form-control" type="password" id="pass" name="pass"
						placeholder="Escribe tu clave" value="<%=per.getPass()%>" required
						style="margin: 10px 0;">
				</div>
			</div>

			<input type="hidden" name="id" value="<%=per.getId()%>"> <input
				type="hidden" name="nick-hid" id="nombre-hid"
				value="<%=per.getNick()%>"> <input type="hidden"
				name="email-hid" id="email-hid" value="<%=per.getEmail()%>">
			<input type="hidden" name="pass-hid" value="<%=per.getPass()%>">
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
						<form method="post" action="<%=Constantes.CONTROLLER_USUARIOS%>">
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
	$(document).ready(
			function() {

				$('#nick').focusout(function() {
					if ($(this).val().trim() != "")
						checkUserData('nick', $(this).val());
				});

				$('#email').focusout(function() {
					if ($(this).val().trim() != "")
						checkUserData('email', $(this).val());
				});

				$('input').keyup(function() {
					checkFields();
				});

				function checkFields() {
					if ($('#nick').val().trim() == "")
						console.log("Falla en nick");
					if ($('#email').val().trim() == "")
						console.log("Falla en email");
					if ($('#pass').val().trim() == "")
						console.log("Falla en pass");

					if ($('#nombre').val().trim() == ""
							|| $('#email').val().trim() == ""
							|| $('#pass').val().trim() == "") {
						$('#enviar').prop('disabled', true);
					} else {
						$('#enviar').prop('disabled', false);
					}
				}

				function checkUserData(campo, valor) {
					$.ajax({
						url : "checkuser",
						dataType : "json",
						data : {
							p1 : campo,
							p2 : valor
						},
					}).done(
							function(data) {
								if (data == true
										&& $('#' + campo).val() != $(
												'#' + campo + '-hid').val()) {
									$('#' + campo).css({
										borderColor : "tomato"
									});
									$('#' + campo + '-error').show();
									$('#enviar').prop('disabled', true);
								} else {
									$('#' + campo).css({
										borderColor : ""
									});
									$('#' + campo + '-error').hide();
								}
							});
				}
			});
</script>

<%@include file="/includes/footer.jsp"%>