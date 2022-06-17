<%@page import="com.controlador.Controlador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <title>Carrito</title>       
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><img src="imagenes/logoutnwhite.png" width="25" height="25"> Apunteca</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item active">
                            <a class="nav-link" href="./Controlador?accion=Home"><i class="fas fa-home"></i> Home<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                    <ul class="navbar-nav btn-group my-2 my-lg-0">
                        <c:if test="${sessionScope.admin == null && sessionScope.alumno == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="Controlador?accion=Login" tabindex="-1" aria-disabled="true"><i class="fas fa-user-tie"></i> Acceder</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.alumno != null}">     
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-circle-user"></i> ${sessionScope.alumno.getNombre()} </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="./Controlador?accion=PerfilUsuario&id=${sessionScope.alumno.getId()}">Perfil</a></li>
                                    <li><a class="dropdown-item" href="./Controlador?accion=PedidosUsuario&id=${sessionScope.alumno.getId()}">Mis Pedidos</a></li>
                                    <li><a class="dropdown-item" href="./Controlador?accion=Salir">Salir</a></li>     
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container-sm mt-4">
            <div class="row">
                <div class="col-sm">                    
                    <div class="table-responsive-sm">
                        <table class="table">
                            <thead class="table-primary">
                                <tr class="text-center">
                                    <th>Apunte</th>
                                    <th>Descripción</th>
                                    <th>Pagina Desde</th>
                                    <th>Pagina Hasta</th>
                                    <th>Anillado</th>
                                    <th>Tipo Impresión</th>
                                    <th>Copias</th>
                                    <th>Total</th>
                                    <th>Observaciones</th>  
                                    <th></th>
                                </tr>    
                            </thead>
                            <tbody>
                            <c:forEach var="car" items="${Carrito}">
                                <tr class="text-center tr">
                                    <td>${car.getNombre()}</td>   
                                    <td>${car.getDescripcion()}</td>
                                    <td>
                                        <input type="hidden" id="itemPagDesde" value="${car.getIdApunte()}">
                                        <input type="number" id="PagDesde" min="1" max="${car.getPaginaHasta()}" value="${car.getPaginaDesde()}">
                                    </td>
                                    <td>
                                        <input type="hidden" id="itemPagHasta" value="${car.getIdApunte()}">
                                        <input type="number" id="PagHasta" min="${car.getPaginaDesde()}" max="${car.getCantPaginas()}" value="${car.getPaginaHasta()}">
                                    </td>
                                    <td>
                                        <input type="hidden" id="itemAnillado" value="${car.getIdApunte()}">
                                        <input type="checkbox" id="anillado" name="chkAnillado" ${car.getAnillado()}>
                                    </td>
                                    <td>
                                        <input type="hidden" id="itemTipoImpresion" value="${car.getIdApunte()}">
                                        <input type="checkbox" id="tipoImpresion" name="chkTipoImp" ${car.getTipoImpresion()}> Simple Faz
                                    </td>
                                    <td>
                                        <input type="hidden" id="itemCantCo" value="${car.getIdApunte()}">
                                        <input type="number" min="1" max="200" id="CantCopias" class=" form-control text-center" value="${car.getCantidadCopias()}">
                                    </td>
                                    <td>${car.getSubtotal()}</td>
                                    <td>
                                        <input type="hidden" id="itemObs" value="${car.getIdApunte()}">
                                        <input type="text" id="observaciones" value="${car.getObservaciones()}">  
                                    </td>
                                    <td>
                                        <input type="hidden" id="item" value="${car.getIdApunte()}">
                                        <a id="deleteItem" href="#" class="btn-outline-danger btn-sm"><i class="fas fa-trash-alt"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>                            
                            </tbody>    
                        </table>    
                    </div>                    
                </div>
                <div class="col-sm">
                    <div class="card">
                        <div class="card-header">
                            <h4>Generar Pedido</h4>
                        </div>
                        <div class="card-body">
                            <label>Total a Pagar:</label>                           
                            <a class="form-control text-center"><i class="fas fa-dollar-sign h4 primary"> <fmt:formatNumber  maxFractionDigits="2" value="${totalPagar}"/></i></a>
                        </div>
                        <div class="card-footer">
                            <a class="btn btn-success" href="./Controlador?accion=GenerarPedido"> Realizar Pedido </a>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
    </body>
</html>
