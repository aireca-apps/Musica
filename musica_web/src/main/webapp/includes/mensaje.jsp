<%@page import="com.ipartek.formacion.proyecto.controladores.Mensaje"%>
<%
	Mensaje msg = (Mensaje) request.getAttribute("msj");
	if (msg != null) {
%>
<div class="row">
	<div class="alert alert-<%=msg.getTipo()%> alert-dismissible"
		role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong><%=msg.getTexto()%></strong>
	</div>
</div>
<%
	} //No hay mensaje
%>