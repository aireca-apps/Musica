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
			${sessionScope.userlogged}</h1>
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
                                    <div><fmt:message key="index.detalle.grupo"/></div>
                                </div>
                            </div>
                        </div>
                        <a data-toggle="collapse" data-target="#tablaGrupos">
                            <div class="panel-footer">
                                <span class="pull-left"><fmt:message key="index.grupo"/></span>
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
</div>
<!-- /#page-wrapper -->
<%@include file="includes/footer.jsp"%>





