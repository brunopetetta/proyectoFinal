<%@page import="com.controlador.Controlador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <title>Mis Apuntes</title>       
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${sessionScope.alumno != null}">
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
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-circle-user"></i> ${sessionScope.alumno.getNombre()} </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="./Controlador?accion=PerfilUsuario&id=${sessionScope.alumno.getId()}">Perfil</a></li>
                                <li><a class="dropdown-item" href="./Controlador?accion=PedidosUsuario&id=${sessionScope.alumno.getId()}">Mis Pedidos</a></li>
                                <li><a class="dropdown-item" href="./Controlador?accion=Salir">Salir</a></li>     
                            </ul>
                        </li>                       
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <div class ="d-flex">
                <h4>Mis Apuntes</h4>
            </div>
            <div class ="d-flex mr-5 mb-2">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalNuevoApunte">Agregar nuevo apunte</button>
            </div>
            <div class="row">
                <div class="col-lg-11">
                    <table class="table table-responsive">
                        <thead class="table-primary">
                            <tr class="text-center">
                                <th>Cod</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Carrera</th>
                                <th>Materia</th>
                                <th>Cantidad de Páginas</th>
                                <th>Acción</th>
                            </tr>    
                        </thead>
                        <tbody>
                            <c:forEach var="a" items="${apuntesAlumno}">
                                <tr class="text-center tr">
                                <td>${a.getId()}</td>
                                <td>${a.getNombre()}</td>
                                <td>${a.getDescripcion()}</td>
                                <td>${a.getCarrera()}</td>
                                <td>${a.getMateria()}</td>
                                <td>${a.getCantPaginas()}</td>
                                <td><a href="./Controlador?accion=editarApunte&id=${a.getId()}" class="btn-outline-warning btn-sm"><i class="fas fa-edit"></i></a>
                                    <input type="hidden" id="apu" value="${a.getId()}">
                                    <a id="deleteApunte" href="#" class="btn-outline-danger btn-sm"><i class="fas fa-trash-alt"></i></a>
                                </td>
                            </tr>
                        </c:forEach>                            
                        </tbody>    
                    </table>    
                </div>                
            </div>
        </div>
        <div class="modal fade" id="modalNuevoApunte" tabindex="-1" role="dialog" aria-labelledby="ModalNuevoLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="ModalNuevoLabel">Nuevo Apunte</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="ControladorApuntes?accion=Agregar" class="form-sign" method="post" enctype="multipart/form-data">
                        <div class="modal-body">
                            <div class="form-group mb-1">
                                <label>Archivo</label>
                                <input type="file" name="fileApunte" class="form-control">
                            </div>
                            <div class="form-group mb-1">
                                <label>Descripción</label>
                                <input type="text" name="txtDescripcion" class="form-control">
                            </div>
                            <div class="form-group mb-1">
                                <label>Carrera</label>
                                <select name="cboCarrera" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                    <option value="Ingenierí­a Civil">Ingeniería Civil</option>
                                    <option value="Ingenieria Eléctrica">Ingenieria Eléctrica</option>
                                    <option value="Ingeniería Mecánica">Ingeniería Mecánica</option>
                                    <option value="Ingeniería Química">Ingeniería Química</option>
                                    <option value="Ingeniería en Sistemas de Información">Ingeniería en Sistemas de Información</option>
                                </select>
                            </div>
                            <div class="form-group mb-1">
                                <label>Materia</label>
                                <input type="text" name="txtMateria" class="form-control">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <input type="submit" name="accion" value="Agregar" class="btn btn-primary btn-block">
                        </div>                
                </div>
                </form> 
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>                
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/funciones.js" type="text/javascript"></script>  
        </c:if>
    </body>
</html>
         