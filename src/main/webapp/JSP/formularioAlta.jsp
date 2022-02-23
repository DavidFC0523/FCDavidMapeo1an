<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page pageEncoding="UTF-8" contentType="text/html" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta</title>
        <link rel="stylesheet" type="text/css" href="${estilo}" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    </head>
    <body>
        <h2>Agregar datos</h2>
        <h3>Ciclos y modulos</h3>
        <div class="row justify-content-center">
            <form method="post" action="control">
                <input type="hidden" name="op" value="add">
                <table class="table table-striped" style="width: 500px" id="formulario">
                    <tr>
                        <td>Nombre Ciclo</td>
                        <td><input type="text" name="nombre" required/></td>
                    </tr>
                    <tr>
                        <td>Modulo</td>
                        <td><input type="text" name="modulo" required/></td>
                    </tr>
                </table>

                <button type="button" name="Anadir" id="Anadir" class="boton">Añadir</button>
                <tr>
                    <td colspan="2"><input type="submit" name="enviar" value="Enviar" class="btn-primary" /></td>
                </tr> 
            </form>
            <script src="./JS/scripts.js"></script>    
        </div>
    </body>
</html>