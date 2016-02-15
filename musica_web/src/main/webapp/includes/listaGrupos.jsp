<%@page import="com.ipartek.formacion.proyecto.Constantes"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.proyecto.pojo.Grupo"%>
<%@page
	import="com.ipartek.formacion.proyecto.Constantes"%>
<%@page import="java.util.Calendar"%>

<!-- /.panel-heading -->
<div class="panel-body">
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive " >
				<table
					class="datatable table table-bordered table-hover table-striped display responsive no-wrap " width="100%">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>DNI</th>
							<th>Email</th>							
							<th>Fecha de Nac.</th>
							<th>Estilo</th>
						</tr>
					</thead>
					<tbody>
						<%
							//recoger "atributo listado personas de la request
							ArrayList<Grupo> lista = (ArrayList<Grupo>) request.getAttribute("listaGrupos");
							if (lista == null)
								lista = new ArrayList<Grupo>();

							for (int i = 1; i <= lista.size(); i++) {
								Grupo per = lista.get(i - 1);
						%>
						<tr>
							<td><a
								href="<%=Constantes.CONTROLLER_GRUPOS%>?op=<%=Constantes.OP_DETALLE%>&id=<%=per.getId()%>"
								title="Ir al detalle de <%=per.getNombre()%>"><%=per.getNombre()%></a></td>
							<td><%=per.getDni()%></td>
							<td><%=per.getEmail()%></td>
							<td><%=per.getFechaNacimiento()%></td>
							<%
								Calendar cal = Calendar.getInstance();
									cal.setTime(per.getFechaNacimiento());
							%>
							<td><a href="<%=Constantes.CONTROLLER_ESTILOS%>?op=<%=Constantes.OP_DETALLE%>&id=<%=per.getEstilo().getId()%>"
								title="Ir al detalle de <%=per.getEstilo().getNombre()%>" style="margin: 10px 0;"><%=per.getEstilo().getNombre()%></td>
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