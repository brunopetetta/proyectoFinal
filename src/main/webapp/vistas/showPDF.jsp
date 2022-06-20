<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>${nombreApunte}</title>
    </head>
    <body>
        <iframe id="ifrm" src="apuntes/${nombreApunte}" ></iframe>
    </body>
    <style type="text/css">
        html, body, div, iframe { margin:0; padding:0; height:100%; }
        iframe { display:block; width:100%; border:none; }
    </style>
</html>
