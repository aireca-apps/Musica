<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.ipartek.formacion.proyecto.Constantes"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.proyecto.pojo.Usuario"%>
<%@include file="/includes/head.jsp"%>

<div id="page-wrapper">
	<%@include file="/includes/mensaje.jsp"%>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Usuarios</h1>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-user fa-fw"></i> Listado de Usuarios				
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="table-responsive">
							<table
								class="datatable table table-bordered table-hover table-striped">
								<thead>
									<tr>
										<th>#</th>
										<th>Nick</th>
										<th>Email</th>
									</tr>
								</thead>
								<tbody>
									<%
										//recoger "atributo listado personas de la request
										ArrayList<Usuario> lista = (ArrayList<Usuario>) request.getAttribute("listaUsuarios");
										if (lista == null)
											lista = new ArrayList<Usuario>();

										for (int i = 1; i <= lista.size(); i++) {
											Usuario usu = lista.get(i - 1);
									%>
									<tr>
										<td><%=i%></td>
										<%
											if (usu.equals(session.getAttribute("userlogged"))) {
										%>
										<td><a
											href="<%=Constantes.CONTROLLER_USUARIOS%>?op=<%=Constantes.OP_DETALLE%>&id=<%=usu.getId()%>"
											title="Ir al detalle de <%=usu.getNick()%>"><%=usu.getNick()%></a></td>
										<%
											} else {
										%>
										<td><%=usu.getNick()%></td>
										<%
											}
										%>
										<td><%=usu.getEmail()%></td>
									</tr>
									<%
										} //end for
									%>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.col-lg-4 (nested) -->
					<div class="col-lg-8">
						<div id="morris-bar-chart"></div>
					</div>
					<!-- /.col-lg-8 (nested) -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.panel-body -->

		</div>
	</div>
</div>
<!-- /#page-wrapper -->

<%@include file="/includes/footer.jsp"%>