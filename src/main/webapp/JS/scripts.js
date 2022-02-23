document.addEventListener("DOMContentLoaded", asignarEventos);

function asignarEventos() {
    //Asignamos lo eventos cuando este cargado el dom
    document.getElementById("Anadir").addEventListener('click', Detectar);
    ;
}

/**
 * No he conseguido cambiar los colores del circulo el color de fondo del otro grafico si pero los del circulo
 * creo que estan configurado en los archivos de la libreria y no he conseguido encontrar donde estan definido exactamente
 */

function Detectar() {

    let opt;
    opt = `
  <tr>
                        <td>Nombre Modulo</td>
                        <td><input type="text" name="modulo" required/></td>
                    </tr>
    `;
    //Lo insertamos
    document.getElementById("formulario").insertAdjacentHTML("beforeend", opt);
    //Le asignamos el evento de Eliminar

}

