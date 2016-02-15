<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../includes/head.jsp" %>     

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Cursos</h1>
                </div>
                <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-graduation-cap fa-fw"></i> Cursos impartidos
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        Actions
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a href="#"><i class="fa fa-plus fa-fw"></i> Añadir Curso</a>
                                        </li>
                                        <li><a href="#"><i class="fa fa-minus fa-fw"></i> Eliminar Curso</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="#"><i class="fa fa-filter fa-fw"></i> Filtro</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Curso</th>
                                                    <th>Inscripciones</th>
                                                    <th>Horas Lectivas</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td>Java J2EE</td>
                                                    <td>15</td>
                                                    <td>300</td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>HTML5</td>
                                                    <td>23</td>
                                                    <td>150</td>
                                                </tr>
                                                <tr>
                                                    <td>3</td>
                                                    <td>C++</td>
                                                    <td>12</td>
                                                    <td>120</td>
                                                </tr>
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

<%@include file="../includes/footer.jsp" %>   
