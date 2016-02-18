<%@page import="com.ipartek.formacion.proyecto.Constantes"%>
<%@page import="com.ipartek.formacion.proyecto.pojo.Usuario"%>
<%@page import="java.util.Calendar"%>

<%@page
	import="com.ipartek.formacion.proyecto.listeners.SessionListener"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>



<%@include file="includes/head.jsp"%>

<%Usuario usuarioLogueado = (Usuario)session.getAttribute("userlogged");%>
<!-- Este es un comentario para comprobar cómo se unen ramas -->

<div id="page-wrapper">
	<div class="row">
		<h1 class="page-header">
			<fmt:message key="index.cabecera" />
		<!-- ${sessionScope.userlogged.nick}  -->
		<%=usuarioLogueado.getNick()%>
		</h1>	
			
	</div>

</div>
<!-- /#page-wrapper -->
<%@include file="includes/footer.jsp"%>