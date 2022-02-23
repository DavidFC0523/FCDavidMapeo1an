package es.albarregas.controllers;

import es.albarregas.beans.Ciclo;
import es.albarregas.beans.Modulo;
import es.albarregas.daofactory.DAOFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
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
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {

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
            throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO pdao = daof.getGenericoDAO();

        Ciclo ciclo = new Ciclo();
        String url = null;
        switch (request.getParameter("op")) {
            case "add":
                
                try {
                //Creo una coleccion list
                List<Modulo> modulos = new ArrayList<Modulo>();
                //Recupero las coleccion de Modulos
                String[] coleccion = request.getParameterValues("modulo");

                boolean repetido = false;
                //Recorro las colecciones iterando el modulo y añadiendo el nuevo objeto Modulo a la coleccion
                for (String p : coleccion) {
                    if (p != null && p != "") {
                        //Eliminamos repeticiones
                        for (Modulo a : modulos) {
                            if (a.getDenominacion().equalsIgnoreCase(p)) {
                                repetido = true;
                            }
                        }

                        if (repetido) {

                        } else {
                            Modulo modulo = new Modulo();
                            modulo.setDenominacion(p);
                            modulos.add(modulo);

                        }
                        repetido = false;
                    }
                }

                BeanUtils.populate(ciclo, request.getParameterMap());
                BeanUtils.copyProperty(ciclo, "modulos", modulos);

            } catch (IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
            pdao.add(ciclo);
            url = "index.jsp";
            break;

            case "delete":
                //Sacamos el codigo que es la pk
                String Cod = request.getParameter("registro");
                ciclo = (Ciclo) pdao.getOne(ciclo.getClass(), Integer.parseInt(Cod));
                pdao.delete(ciclo);
                url = "index.jsp";
                break;
            case "update":
                Cod = request.getParameter("registro");
                //Sacamos el ciclo especifico y lo mandamos como atributo
                ciclo = (Ciclo) pdao.getOne(ciclo.getClass(), Integer.parseInt(Cod));
                request.setAttribute("ciclo", ciclo);
                url = "JSP/formularioActualizar.jsp";

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
        try {
            processRequest(request, response);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
