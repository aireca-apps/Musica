<%@page import="com.ipartek.formacion.proyecto.pojo.Usuario"%>
<%@page import="com.ipartek.formacion.proyecto.Constantes"%>
<%@page import="com.ipartek.formacion.proyecto.controladores.Mensaje"%>
<%@ page import="com.ipartek.formacion.proyecto.pojo.Grupo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${cookie.cIdioma.value==''?'eu_ES':cookie.cIdioma.value }" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18nmesages" /> 
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<!DOCTYPE html>
<html lang="${language}">
<%@ page contentType="text/html; charset=UTF-8" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- Para indicar que construya la ruta a partir del nombre del proyecto -->
    <base href="${pageContext.request.contextPath}/"/>

    <title><%=Constantes.APP_NAME%></title>

    <!-- Bootstrap Core CSS -->
    <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="js/bootstrap/css/responsive.bootstrap.min.css" rel="stylesheet">
    

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">   
    
    <!-- EL CSS de datatables-->
    <link href="js/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="js/datatables/css/dataTables.bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

	
	<script src="js/jquery.min.js"></script>
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Tu música</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                    <% Usuario usua = (Usuario)session.getAttribute("userlogged");%>
                        <li><a href="<%=Constantes.CONTROLLER_USUARIOS%>?op=<%=Constantes.OP_DETALLE%>&id=<%=usua.getId()%>"><i class="fa fa-user fa-fw"></i> Perfil de usuario</a>
                        </li>                        
                        <li class="divider"></li>
                        <li><a href="logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            
<%@include file="nav.jsp" %> 