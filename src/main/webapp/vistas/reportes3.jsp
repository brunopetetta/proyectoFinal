<%@page import="com.controlador.Controlador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <title>Reportes Insumos</title>
        <style>
            @media print{
                .parte01, .btn-primary, .navbar-brand{
                    display:none;
                }
            }
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css"> 
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.admin != null}">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><img src="imagenes/logoutnwhite.png" width="25" height="25"> Apunteca</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Pedidos
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="./Controlador?accion=PedidosAdmin">Lista Pedidos</a></li>
                                <li><a class="dropdown-item" href="./Controlador?accion=ElegirAlumno">Cargar Pedido</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./Controlador?accion=ApuntesAdmin">Apuntes</a>
                        </li> 
                        <li class="nav-item">
                            <a class="nav-link" href="./Controlador?accion=ListaUsuarios">Usuarios</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Reportes
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="./Controlador?accion=Reportes1">Pedidos</a></li>
                                <li><a class="dropdown-item" href="./Controlador?accion=Reportes2">Carreras</a></li>
                                <li><a class="dropdown-item" href="./Controlador?accion=Reportes3">Insumos</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="navbar-nav btn-group my-2 my-lg-0">   
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user-gear"></i> ${sessionScope.admin.getNombre()} </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="./Controlador?accion=Salir"> Salir </a></li>     
                            </ul>
                        </li>                        
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <div class="container-sm mb-2 mt-2 parte01">
                <a href="#" class="btn-success btn-sm" onclick="print()"><i class="fas fa-print"></i> Generar Reporte </a>
            </div>
            <div class="container-sm mb-2">
                <form action="ControladorReportes" method="post">
                    <input type="text" id="date_range" name="date_range">
                    <input type="submit" name="accion" value="Filtrar" class="btn-primary btn-sm">
                    <input type="hidden" name="reporte" value="3">
                </form>
            </div>
            <div class="card-group">
                <div class="card text-center bg-success text-white mb-2">
                    <div class="card-body">                    
                        <i class="fa fa-sack-dollar fa-3x"></i>
                        <h2 class="card-title">$${recaudacion}</h2>
                        <h5>Recaudación</h5>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-2">
                    <div class="card-body">
                        <i class="fa fa-book fa-3x"></i>
                        <h2 class="card-title">${cantAnillados}</h2>
                        <h5>Cantidad de anillados</h5>
                    </div>
                </div>  
                <div class="card text-center bg-success text-white mb-2">
                    <div class="card-body">
                        <i class="fa fa-file-lines fa-3x"></i>
                        <h2 class="card-title">${cantHojas}</h2>
                        <h5>Cantidad de hojas</h5>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-2">
                    <div class="card-body">
                        <i class="fa fa-file fa-3x"></i>
                        <h2 class="card-title">${cantSimple}</h2>
                        <h5>Copias Simple Faz</h5>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-2">
                    <div class="card-body">
                        <i class="fa fa-copy fa-3x"></i>
                        <h2 class="card-title">${cantDoble}</h2>
                        <h5>Copias Doble Faz</h5>
                    </div>
                </div>
            </div>
            <div class ="d-flex">
                <h5>Histórico valor fotocopia</h5>
            </div>
            <div class="row">
                <div class="col-lg-11">
                    <table class="table table-responsive" id="tablaFotocopia">
                        <thead class="table-primary">
                            <tr class="text-center">
                                <th>Fecha Desde</th>
                                <th>Valor fotocopia</th>
                            </tr>    
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${valoresFotocopia}">
                                <tr class="text-center tr">
                                <td>${p.getFechaDesdePrecio()}</td>
                                <td>$${p.getValorFotocopia()}</td>                                
                            </tr>
                        </c:forEach>                            
                        </tbody>    
                    </table>    
                </div>                
            </div>
        </div> 
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>                
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <script src="js/funciones.js" type="text/javascript"></script>  
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
        <script type="text/javascript">
            $(function () {
                $('#date_range').daterangepicker({
                    "locale": {
                        "format": "DD-MM-YYYY",
                        "separator": " - ",
                        "applyLabel": "Aplicar",
                        "cancelLabel": "Cancelar",
                        "fromLabel": "Desde",
                        "toLabel": "Hasta",
                        "customRangeLabel": "Personalizar",
                        "daysOfWeek": [
                            "Do",
                            "Lu",
                            "Ma",
                            "Mi",
                            "Ju",
                            "Vi",
                            "Sa"
                        ],
                        "monthNames": [
                            "Enero",
                            "Febrero",
                            "Marzo",
                            "Abril",
                            "Mayo",
                            "Junio",
                            "Julio",
                            "Agosto",
                            "Setiembre",
                            "Octubre",
                            "Noviembre",
                            "Diciembre"
                        ],
                        "firstDay": 1
                    },
                    "startDate": "${fechaDesde}",
                    "endDate": "${fechaHasta}",
                    "opens": "center"
                });
            });
        </script>        
        </c:if>
    </body>
</html>
