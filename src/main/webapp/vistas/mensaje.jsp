<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <title>Alerta</title>
    </head>
    <body>
        <div class="container mt-4">
            <div class="col-sm-8">
                <div class="${alert}" role="alert">
                    <h4 class="alert-heading">${mensaje}</h4>
                    <hr>
                    <a href="${URL}" class="btn btn-outline-danger">Regresar</a>
                </div>
            </div>
        </div>
    </body>
</html>
