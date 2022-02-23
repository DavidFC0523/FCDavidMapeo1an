<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page pageEncoding="UTF-8" contentType="text/html" />
<c:url var="estilo" value="/CSS/estilo.css" scope="application"/>
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
         <link rel="stylesheet" type="text/css" href="${estilo}" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
  
    
    <body>
        <br>
        <br>
            <div class="row justify-content-center" >
        <div>
            <h1>Mapeo 1aN Ordenado</h1>
            <h2>Elige una opci&oacute;n</h2>
                     
            <fieldset>
            <form action="eleccion" method="POST" class="">
                <select name="op" class="custom-select">
                <option value="add">Añadir</option>
                <option value="delete" selected>Eliminar</option>
                <option value="list">Listar</option>
                <option value="eliminarModulo">EliminarModulo</option>
                <option value="update">Actualizar</option>
              </select> 
                <br><br>
                <input type="submit" value="enviar" class="btn-primary">
            </form>
            </fieldset>
            
        </div>
</div>
    </body>
</html>