<!-- /.navbar-top-links -->

<%@page import="com.ipartek.formacion.backoffice.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${cookie.cIdioma.value==''?'eu_ES':cookie.cIdioma.value }" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18nmesages" /> 

<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			<li class="sidebar-search">
				<div class="input-group custom-search-form">

					<form method="get" action="<%=Constantes.CONTROLLER_SEARCH%>">

						<input type="text" id="criterio" name="criterio" class="form-control"
							placeholder="Buscar..." autofocus requiered min="1">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<i class="fa fa-search"></i>
							</button>
					</form>

					</span>
				</div> <!-- /input-group -->
			</li>
			<li><a href="<%=Constantes.VIEW_INDEX%>"><i class="fa fa-dashboard fa-fw"></i>
					<fmt:message key="index.lateral.principal"/></a></li>
			<li>
				<!-- vamos a llamar al controlador en lugar del jsp --> <a
				href="<%=Constantes.CONTROLLER_USUARIOS%>"><i class="fa fa-user fa-fw"></i> <fmt:message key="index.lateral.usuarios"/></a>
			</li>
			<li><a href="<%=Constantes.CONTROLLER_ROLES%>"><i
					class="fa fa-graduation-cap fa-fw"></i> <fmt:message key="index.lateral.roles"/></a></li>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
</nav>
<!-- End:Navigation -->