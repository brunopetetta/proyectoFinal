<%-- 
    Document   : index
    Created on : 22 dic 2021, 17:15:10
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>       
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
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
                            <a class="nav-link" href="./Controlador?accion=Nuevo"><i class="fas fa-home"></i> Home<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./Controlador?accion=Carrito"><i class="fas fa-cart-plus">(<label style="color: orange">${contador}</label>)</i> Carrito</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav btn-group my-2 my-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="./Controlador?accion=Login" tabindex="-1" aria-disabled="true"><i class="fas fa-user-tie"></i> Acceder</a>
                       </li>
                    </ul>    
                        <%--
                         
                        <%
                            Cliente u = (Cliente) session.getAttribute("usuario");
                            if(u != null){
                                if(u.getPassword().equals("admin123")){ //ADMIN
                                    out.println("<li class=\"nav-item\">");
                                    out.println("<a class=\"nav-link\" href=\"./Controlador?accion=ComprasAdmin\">Compras</a>");
                                    out.println("</li>");
                                    out.println("<li class=\"nav-item\">");
                                    out.println("<a class=\"nav-link\" href=\"./Controlador?accion=ListaProductos\">Productos</a>");
                                    out.println("</li>"); 
                                    out.println("<li class=\"nav-item\">");
                                    out.println("<a class=\"nav-link\" href=\"./Controlador?accion=ListaUsuarios\">Usuarios</a>");
                                    out.println("</li>");                                    
                                }
                            }
                        %>
                    </ul>
                    <ul class="navbar-nav btn-group my-2 my-lg-0">
                        <%  
                            Cliente c = (Cliente) session.getAttribute("usuario");
                            if(c != null){
                                if(c.getPassword().equals("admin123")){ //el admin
                                    out.println("<li class=\"nav-item dropdown\">");
                                    out.println("<a class=\"nav-link dropdown-toggle\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"> Admin <img src=\"imagenes/admin.png\" alt=\"20\" height=\"20\"/></a>");
                                    out.println("<ul class=\"dropdown-menu\">");
                                    out.println("<li><a class=\"dropdown-item\" href=\"./Controlador?accion=Salir\"> <i class=\"fas fa-arrow-right\">Salir</i></a></li>");     
                                    out.println("</ul>");
                                    out.println("</li>");
                                } 
                                else{ //cliente                                    
                                    out.println("<li class=\"nav-item dropdown\">");
                                    out.println("<a class=\"nav-link dropdown-toggle\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">"+ c.getNombre()+ "<img src=\"imagenes/user.png\" alt=\"20\" height=\"20\"/></a>");
                                    out.println("<ul class=\"dropdown-menu\">");
                                    out.println("<li><a class=\"dropdown-item\" href=\"./Controlador?accion=PerfilCliente&id="+c.getId()+"\">Perfil</a></li>");
                                    out.println("<li><a class=\"dropdown-item\" href=\"./Controlador?accion=ComprasCliente&id="+c.getId()+"\">Mis Compras</a></li> ");
                                    out.println("<li><a class=\"dropdown-item\" href=\"./Controlador?accion=Salir\"> <i class=\"fas fa-arrow-right\">Salir</i></a></li>");     
                                    out.println("</ul>");
                                    out.println("</li>");                                   
                                }
                            }
                            else
                            {
                                out.println("<li class=\"nav-item\">");
                                out.println("<a class=\"nav-link\" href=\"./Controlador?accion=Login\" tabindex=\"-1\" aria-disabled=\"true\"><i class=\"fas fa-user-tie\"></i> Acceder</a>");
                                out.println("</li>");
                            }
                        %>
                    </ul>
                        --%>
                </div>
            </div>
        </nav>
        <%--
        <div class="container mt-4">
            <div class="row">
                <c:forEach var="p" items="${productos}">
                    <div class="col-sm-4">
                        <div class="card">
                            <div class="card-header">
                                <label class="col-lg-12">${p.getNombres()}</label>                                 
                            </div>
                            <div class="card-body text-center d-flex">
                                    <label><i class="fas fa-dollar-sign">${p.getPrecio()}</i></label>
                                     <img src="imagenes/${p.getFoto()}" width="200" height="200">
                                </div>
                            <div class="card-footer">
                                <div class="col-lg-12">
                                    <label>${p.getDescripcion()}</label>                                   
                                </div>
                                <div class=" col-lg-12 text-center">                                
                                   <a href="./Controlador?accion=AgregarCarrito&id=${p.getId()}" class="btn btn2 btn-outline-primary">Agregar a Carrito<i class="fas fa-cart-plus"></i></a>
                                   <a href="./Controlador?accion=Comprar&id=${p.getId()}" class="btn btn-danger">Comprar</a>
                                </div>                     
                            </div>
                        </div>
                    </div>
                </c:forEach>                    
            </div>
        </div>
         --%>               
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    
    </body>
</html>
