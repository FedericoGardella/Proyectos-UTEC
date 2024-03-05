<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pruebaPA.config.ListURL" %>

<!DOCTYPE html>
<html lang="es" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link rel="stylesheet" href="<%= ListURL.BootstrapURL.getURL() %>">
    <link rel="stylesheet" href="<%= ListURL.headerStylesURL.getURL() %>">
    <style>
        body {
            background-image: url('https://www.10wallpaper.com/wallpaper/1366x768/1604/Thailand_Travel_Vacation_Nature_Scenery_HD_Wallpaper_09_1366x768.jpg'); /* Cambia 'ruta_de_tu_imagen.jpg' por la ruta de tu imagen de fondo */
            background-size: cover; /* Ajusta el tamaño de la imagen al tamaño de la ventana del navegador */
            background-repeat: no-repeat;
            background-attachment: fixed; /* Fija la imagen de fondo para que no se desplace con el desplazamiento del contenido */
            color: #ffffff; /* Cambia el color del texto para que sea legible en el fondo */
        }

        .container {
            padding-top: 100px; /* Ajusta la distancia del contenido desde la parte superior */
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/template/header.jsp" />
    <div class="container">
        <h1>Bienvenido a nuestro sitio de viajes y turismo</h1>
        <!-- Puedes agregar más contenido aquí según lo desees -->
    </div>
</body>
<script src="<%=ListURL.JSFilesURL.getURL()%>bootstrap.bundle.min.js"></script>
<script src="<%= ListURL.headerJSURL.getURL() %>"></script>
</html>
