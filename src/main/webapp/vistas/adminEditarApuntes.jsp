<%@page import="com.controlador.Controlador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <title>Admin Apuntes</title>       
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
            <div class ="d-flex">
                <h4>Editar Apunte</h4>
            </div>
            <div class="row">
                <form action="ControladorApuntes?accion=Actualizar&id=${apunte.getId()}" class="form-sign" method="post" enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="form-group mb-1">
                                <label>Archivo</label>
                                <input type="file" name="fileApunte" class="form-control">
                                <input type="hidden" value="${apunte.getNombre()}" name="txtNombre">
                            </div>
                            <div class="form-group mb-1">
                                <label>Descripcion</label>
                                <input type="text" name="txtDescripcion" value="${apunte.getDescripcion()}" class="form-control">
                            </div>
                            <div class="form-group mb-1">
                                <label>Carrera</label>
                                <select name="cboCarrera" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                    <option selected>${apunte.getCarrera()}</option>
                                    <option value="Ingenierí­a Civil">Ingenierí­a Civil</option>
                                    <option value="Ingenieria Eléctrica">Ingeniería Eléctrica</option>
                                    <option value="Ingeniería Mecánica">Ingeniería Mecánica</option>
                                    <option value="Ingeniería Química">Ingeniería Química</option>
                                    <option value="Ingeniería en Sistemas de Información">Ingeniería en Sistemas de Información</option>
                                </select>
                            </div>
                            <div class="form-group mb-1">
                                <label>Materia</label>
                                <input type="hidden" name="numCantPaginas" value="${apunte.getCantPaginas()}">
                                <input type="text" name="txtMateria" value="${apunte.getMateria()}" class="form-control">
                            </div>
                        </div>
                        <div class="card-footer">
                            <input type="submit" name="accion" value="Actualizar" class="btn btn-primary btn-block">
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
         