<%@ page import="com.ipartek.formacion.backoffice.listeners.DBListener"%>
<%@ page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.ipartek.formacion.backoffice.Constantes"%>
<%@ page errorPage="pages/error/error.jsp"%>

<%@ page import="com.ipartek.formacion.backoffice.controladores.Mensaje"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="language" value="${cookie.cIdioma.value==''?'eu_ES':cookie.cIdioma.value }" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18nmesages" /> 

<% 
	if (DBListener.error == true){
		throw new Exception("No hay conexión con la base de datos");
	}	
%>

<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ipartek SB_Admin</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="fonts/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="css/style.css">
<base href="${pageContext.request.contextPath}/" />


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="img/fav.png">


</head>



<body>
<div>
<img src="img/bg.png" style="text-align: left;width: 160px" >
</div>
	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">

			<div class="container">

				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1 style="text-align: center">
							<strong><fmt:message key="login.acceso.cabecera" /></strong>
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3><fmt:message key="login.info.enter"/></h3>
								<p><fmt:message key="login.info"/></p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form name="login" role="form" method="post" class="login-form"
								action="<%=Constantes.CONTROLLER_LOGIN%>" accept-charset="utf-8">

								<div class="form-group">
									<label class="sr-only" for="form-username">Email</label> <input
										type="email" id="email" name="email" placeholder="tuemail@email.com"
										class="form-username form-control"
										value="${cookie.cEmail.value}" required>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Contraseña</label> <input
										id="password" type="password" name="password" placeholder="Contraseña..."
										class="form-password form-control" required>
								</div>
								<div>
									<label for="idioma"><fmt:message key="login.idioma" /></label>

									<select class="form-control" name="idioma" id="idioma">
										<option value="es_ES"
											<c:if test="${language == 'es_ES'}">selected</c:if>>Castellano</option>
										<option value="eu_ES"
											<c:if test="${language == 'eu_ES'}">selected</c:if>>Euskara</option>
										<option value="en_EN"
											<c:if test="${language == 'en_EN'}">selected</c:if>>English</option>
									</select>
								</div>
								<input type="checkbox" id="recuerdame" checked name="recuerdame"
									value="1"> <fmt:message key="login.remember"/> <input
									id="submit" type="submit" class="btn center-block"
									style="margin-top: 20px; width: 50%;" value=<fmt:message key="login.boton"/> />
								<%
									Mensaje mensaje = (Mensaje) request.getAttribute("msj");
									if (session.getAttribute("msj") != null) {
										mensaje = (Mensaje) session.getAttribute("msj"); //recoger
										session.setAttribute("msj", null); // limpiar mensaje
									}
									if (mensaje != null) {
								%>
								<div>
									<p class="error"><%=mensaje.getTexto()%></p>
								</div>
								<%
									}
								%>

							</form>
						</div>

					</div>
				</div>


			</div>
		</div>
	</div>

	<!-- Javascript -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<script src="js/jquery.backstretch.min.js"></script>
	<script src="js/scripts.js"></script>

</body>

</html>
