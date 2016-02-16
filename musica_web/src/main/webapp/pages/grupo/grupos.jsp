<%@ page contentType="text/html; charset=UTF-8"%>

<%@include file="/includes/head.jsp"%>

<div id="page-wrapper">
	<%@include file="/includes/mensaje.jsp"%>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Grupos</h1>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-user fa-fw"></i> Últimos 500 grupos inscritos.
				<div class="pull-right">
					<div class="btn-group">
						<a href="<%=Constantes.CONTROLLER_GRUPOS%>?op=<%=Constantes.OP_NUEVO%>"
								title="Crear registro"> <i class="fa fa-plus fa-fw"></i>
								Añadir Grupo
							</a>
					</div>
				</div>
			</div>
			<%@include file="/includes/listaGrupos.jsp"%>

		</div>
	</div>
</div>
<!-- /#page-wrapper -->

<%@include file="/includes/footer.jsp"%>
