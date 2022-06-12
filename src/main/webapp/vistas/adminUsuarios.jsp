<%@page import="com.controlador.Controlador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <title>Admin Usuarios</title>       
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css">
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
                        <li class="nav-item">
                            <a class="nav-link" href="./Controlador?accion=PedidosAdmin">Pedidos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./Controlador?accion=ApuntesAdmin">Apuntes</a>
                        </li> 
                        <li class="nav-item">
                            <a class="nav-link" href="./Controlador?accion=ListaUsuarios">Usuarios</a>
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
            <div class ="d-flex">
                <h4>Lista Usuarios</h4>
            </div>
            <div class="row">
                <div class="col-lg-11">
                    <table class="table table-responsive" id="tablaAdminUsuarios">
                        <thead class="table-primary">
                            <tr class="text-center">
                                <th>Cod Usuario</th>
                                <th>Legajo</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Email</th>
                                <th>Rol</th>
                                <th>Acción</th>
                            </tr>    
                        </thead>
                        <tbody>
                            <c:forEach var="u" items="${usuarios}">
                                <tr class="text-center tr">
                                    <td>${u.getId()}</td>   
                                    <td>${u.getLegajo()}</td>
                                    <td>${u.getNombre()}</td>
                                    <td>${u.getApellido()}</td>
                                    <td>${u.getEmail()}</td>   
                                    <td>${u.getRol().getDescripcion()}</td> 
                                    <td class="text-center">
                                        <input type="hidden" id="adm" value="${u.getId()}">
                                        <a id="addAdmin" href="#" class="btn-outline-success btn-sm"><i class="fas fa-user-plus"></i></a> <a id="deleteAdmin" href="#" class="btn-outline-danger btn-sm"><i class="fas fa-user-minus"></i></a>
                                    </td>
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
        <script src="js/funciones.js" type="text/javascript"></script>  
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
        <script>
            $(document).ready( function () {
                $('#tablaAdminUsuarios').DataTable({
                    "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                    }
                });
            });
        </script>
        </c:if>
    </body>
</html>
               