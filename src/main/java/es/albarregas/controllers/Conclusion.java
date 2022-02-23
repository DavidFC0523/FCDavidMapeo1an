package es.albarregas.controllers;

import es.albarregas.beans.Ciclo;
import es.albarregas.beans.Modulo;
import es.albarregas.daofactory.DAOFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import es.albarregas.dao.IGenericoDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "Conclusion", urlPatterns = {"/conclusion"})
public class Conclusion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO pdao = daof.getGenericoDAO();

        Ciclo ciclo = new Ciclo();

        String url = null;
        switch (request.getParameter("op")) {

            case "update":
              try {
                //Creo una coleccion de correos de tipo LIST
                List<Modulo> modulos = new ArrayList<Modulo>();

                //Recupero los modulos y los id de esos modulos
                String[] coleccion = request.getParameterValues("modulo");
                String[] idmodulos = request.getParameterValues("idModulo");
                int contador = 0;
                //Los meto en bucle para que se asocien cada id con su correo
                for (String p : coleccion) {

                    if (p != null && !p.equals("")) {
                        Modulo modulo = new Modulo();
                        if (idmodulos != null) {
                            if (contador < idmodulos.length) {
                                modulo.setIdModulo(Integer.parseInt(idmodulos[contador]));
                            }
                        }

                        boolean repetido = false;
                        //Elimino las repeticiones
                        for (Modulo a : modulos) {
                            if (a.getDenominacion().equalsIgnoreCase(p)) {
                                repetido = true;
                            }
                        }

                        if (repetido) {

                        } else {
                            modulo.setDenominacion(p);
                            modulos.add(modulo);

                        }

                        repetido = false;
                    }
                    contador++;
                }
                //Creo el objeto profesor con la coleccion actualizado y lo mando por update
                BeanUtils.populate(ciclo, request.getParameterMap());
                BeanUtils.copyProperty(ciclo, "modulos", modulos);
            } catch (IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
            pdao.update(ciclo);
            url = "index.jsp";
            break;

            case "borrarCorreo":
                //Recupero el value del modulo seleccionado para eliminar
                String todo = request.getParameter("registro");
                String[] parts = todo.split(",");
                //Hago split para separar el id de ciclo y nombre del modulo
                //Recupero el ciclo sobre el que se quiere trabajar
                ciclo = (Ciclo) pdao.getOne(Ciclo.class, Integer.parseInt(parts[0]));
                //Recupero su coleccion de modulos
                List<Modulo> modulos = new ArrayList<Modulo>();
                modulos = ciclo.getModulos();
                //Creo un objeto Modulo
                Modulo modulo = new Modulo();

                modulo.setDenominacion(parts[1]);
                //Lo elimino de la coleccion y actualizo ciclo
                modulos.remove(modulo);
                ciclo.setModulos(modulos);
                pdao.update(ciclo);
                url = "index.jsp";
                break;

        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
