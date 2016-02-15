<%@page import="com.ipartek.formacion.proyecto.Constantes"%>
<%@page import="com.ipartek.formacion.proyecto.pojo.Grupo"%>
<%@page import="java.util.Calendar"%>

<%@page
	import="com.ipartek.formacion.proyecto.listeners.SessionListener"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>



<%@include file="includes/head.jsp" %>  


<!-- Este es un comentario para comprobar cómo se unen ramas -->

<div id="page-wrapper">
	<div class="row">
		<h1 class="page-header"><fmt:message key="index.cabecera"/>
			${sessionScope.userlogged.nombre}</h1>
		<div class="row">
			
			<div class="col-lg-6 col-md-9">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-calendar fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">
                                    	<jsp:useBean id="dateValue" class="java.util.Date" />
										<jsp:setProperty name="dateValue" property="time"
											value="${cookie.cLastVisit.value}" />
										<fmt:formatDate value="${dateValue}" pattern="dd-MM-yyyy HH:mm" />
									</div>
                                    <div><fmt:message key="index.detalle.visita"/></div>
                                </div>
                            </div>
                        </div>
                   </div>
                   <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-bar-chart-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${applicationScope.visitantes}</div>
                                    <div><fmt:message key="index.datalle.grafico"/></div>
                                </div>
                            </div>
                        </div>
                        <a>
                            <div class="panel-footer" id="botonActualizacoinGrafica">
                                <span class="pull-left"><fmt:message key="index.grafico"/></span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-user fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><%= SessionListener.listaUsariosLogeados.size() %></div>
                                    <div><fmt:message key="index.detalle.usuario"/></div>
                                </div>
                            </div>
                        </div>
                        <a data-toggle="collapse" data-target="#tablaUsuarios">
                            <div class="panel-footer">
                                <span class="pull-left"><fmt:message key="index.usuario"/></span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
            </div>
            

			<div class="col-lg-4">
				
			</div>
			<div class="col-lg-6">
			<br><br><br>
				<div class="canvas-holder">
					<canvas id="chart-area" class="center-block" width="300"
						height="300"></canvas>
				</div>
			</div>
			

		</div>
		<!-- /.col-lg-12 -->
	</div>
	<hr>
	<!-- /.row -->
	<div id="tablaUsuarios" class="collapse">
		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table
						class="datatable table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Email</th>
								<th>Rol</th>
							</tr>
						</thead>
						<tbody id="lista_user_logged">
							<%
								for ( Grupo per : SessionListener.listaUsariosLogeados ){
							%>
							<tr>
								<td><a
									href="<%=Constantes.CONTROLLER_USUARIOS%>?op=<%=Constantes.OP_DETALLE%>&id=<%=per.getId()%>"
									title="Ir al detalle de <%=per.getNombre()%>"><%=per.getNombre()%></a></td>
								<td><%=per.getEmail()%></td>
								<td><a
									href="<%=Constantes.CONTROLLER_ROLES%>?op=<%=Constantes.OP_DETALLE%>&id=<%=per.getRol().getId()%>"
									title="Ir al detalle de <%=per.getRol().getNombre()%>"
									style="margin: 10px 0;"><%=per.getRol().getNombre()%></td>
							</tr>
							<%
								} //end for
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->
<%@include file="includes/footer.jsp"%>



<script>

	/* Se ejecuta cuando la pagina esta cargada totalmente */
	$(function() {		
	 	
	 	setInterval( refreshUserLogged , 5000);
	 	tratarDatos();
	 	
	 	$('#botonActualizacoinGrafica').on('click',tratarDatos);
	});

	var controllerUsuarios = "<%=Constantes.CONTROLLER_USUARIOS%>";
	var controllerRoles = "<%=Constantes.CONTROLLER_ROLES%>";
	var operacionDetalle = "<%=Constantes.OP_DETALLE%>";
	
	var chartData = null;
	
	function tratarDatos(){
	
		$.ajax("<%=Constantes.CONTROLLER_USUARIOS_LOGEADOS%>", {
			"type" : "get",
			"success" : function(result) {
				chartData = {};				
				//chart['c'] = (chart['c'] == null) ? 1 : chart['c']++
				$.each(result, function(key, value) {
				var aux = chartData[value.rol.nombre]
				
				if (aux == undefined){
					aux = 1;
				}else{
					aux++;
				}
				chartData[value.rol.nombre] = aux;

				});
				printChart(chartData);
			},
			"error" : function(result) {
				console.error("Este callback maneja los errores", result);
			},
			// "data": { p1 : "Volando vengo"},
			"async" : true,
		});

	}
	
	function printChart(data){
		var finalData = [];
		var ctx = document.getElementById("chart-area").getContext("2d");
		$.each(data, function(key, value) {
			finalData.push(
					{
						value: value,
					 	label: key
					});
		});
//		Chart.defaults.global.responsive = true;
		window.myPie = new Chart(ctx).Pie(finalData);
	}
	
	/*
	   Llamda Ajax para mostrar los usuarios logeados
	*/
	function refreshUserLogged(){
		
		
		$('#lista_user_logged').html('');
		
		//url => loggeduser
		
		$.ajax("<%=Constantes.CONTROLLER_USUARIOS_LOGEADOS%>", {
			"type" : "get",
			"success" : function(result) {
				console.log("Llego el contenido y no hubo error", result);

				$.each(result,
						function(key, value) {

							//http://localhost:8080/BackofficeSbAdminBoostrap/undefined?op=undefined&id=Zulema%20Vargo%20La%20L%C3%ADnea%20De%20La%20Concepci%C3%B3n
							var item = "<tr>";
							item += "<td><a ";
							item += "href='" + controllerUsuarios + "?op="
									+ operacionDetalle + "&id=" + value.id
									+ "' ";
							item += "title='Ir al detalle de " + value.nombre
									+ "'";
							item += "style='margin: 10px 0;'>" + value.nombre
									+ "</td>";
							//var item = "<li>" + value.nombre + "</li>";
							item += "<td>" + value.email + "</td>";
							item += "<td><a "
							item += "href='" + controllerRoles + "?op="
									+ operacionDetalle + "&id=" + value.rol.id
									+ "' ";
							item += "title='Ir al detalle de "
									+ value.rol.nombre + "' '";
							item += "style='margin: 10px 0;'>"
									+ value.rol.nombre + "</td>";
							item += "</tr>"

							$('#lista_user_logged').append(item);

						});

			},
			"error" : function(result) {
				console.error("Este callback maneja los errores", result);
			},
			// "data": { p1 : "Volando vengo"},
			"async" : true,
		});

	}
</script>



