<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registro</title>
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
                        <li class="nav-item">
                            <a class="nav-link" href="./Controlador?accion=Carrito"><i class="fas fa-cart-shopping">  <label style="color: ghostwhite">${contador}</label></i>  Carrito</a>
                        </li>
                    </ul>                       
                </div>
            </div>
        </nav>
        <div class="container mt-4 col-lg-4">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form class="form-sign" action="ControladorUsuarios" method="POST">
                        <div class="form-group text-center">
                            <h3>Registro</h3>
                        </div>
                        <div class="form-group mb-1">
                            <label>Legajo</label>
                            <input type="text" name="txtlegajo" class="form-control">
                        </div>
                        <div class="form-group mb-1">
                            <label>Nombre</label>
                            <input type="text" name="txtnombre" class="form-control">
                        </div>
                        <div class="form-group mb-1">
                            <label>Apellido</label>
                            <input type="text" name="txtapellido" class="form-control">
                        </div>
                        <div class="form-group mb-1">
                            <label>Email</label>
                            <input type="email" name="txtemail" class="form-control">
                        </div>
                        <div class="form-group mb-3">
                            <label>Contraseña</label>
                            <input type="password" name="txtpass" class="form-control">
                        </div>
                        <input type="submit" name="accion" value="Registrar" class="btn btn-primary btn-block">
                    </form>
                </div>
            </div>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>                
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/funciones.js" type="text/javascript"></script>    
    </body>
</html>
