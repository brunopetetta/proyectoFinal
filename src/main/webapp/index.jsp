<%@page import="com.controlador.Controlador"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>       
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
                    <ul class="navbar-nav btn-group my-2 my-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="Controlador?accion=Login" tabindex="-1" aria-disabled="true"><i class="fas fa-user-tie"></i> Acceder</a>
                       </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-3">
            <div class="input-group justify-content-end mb-3">
                <div class="form-outline">
                    <input type="search" class="form-control" />
                </div>
                    <button type="button" class="btn btn-success"> <i class="fas fa-search"></i> </button>
            </div>
            <div class="row">
                <div class="col-lg-12">                    
                    <table class="table table-responsive">
                        <thead class="table-primary">
                            <tr class="text-center">
                                <th>Nombre</th>
                                <th>Descripcion</th>
                                <th>Carrera</th>
                                <th>Materia</th>
                                <th></th>
                            </tr>    
                        </thead>
                        <tbody>
                        <c:forEach var="a" items="${apuntespublicos}">
                            <tr class="text-center tr">
                                <td>${a.getNombre()}</td>   
                                <td>${a.getDescripcion()}</td>
                                <td>${a.getCarrera()}</td>
                                <td>${a.getMateria()}</td>                           
                                <td class="text-center">
                                    <a href="#" class="btn-outline-success btn-sm"><i class="fas fa-eye"></i></a>
                                    <a href="./Controlador?accion=AgregarCarrito&id=${a.getId()}" class="btn-outline-success btn-sm"><i class="fas fa-cart-plus"></i></a>
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
    
    </body>
</html>
