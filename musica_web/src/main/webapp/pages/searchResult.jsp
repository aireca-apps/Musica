<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/includes/head.jsp"%>

<div id="page-wrapper">
	<div id="search-result">
		<div class="row">
			<div class="col-lg-12">

				<div class="panel panel-default">

					<div class="panel-heading">
						<h1 class="page-header">Criterio buscado:
							${requestScope.criterio}</h1>
					</div>
				</div>
			</div>
			<%@include file="/includes/listaGrupos.jsp"%>
		</div>
		<!-- div id="search-result" -->
	</div>
</div>
<!--/#page-wrapper -->

<%@include file="/includes/footer.jsp"%>
