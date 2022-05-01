package com.controlador;

import com.modelo.Carrito;
import com.modeloDAO.ApunteDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.utils.Constants;
import com.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador extends HttpServlet {

    ApunteDAO apudao = new ApunteDAO();
    List apuntes = new ArrayList();
    List<Carrito> listaCarrito = new ArrayList<>();
    Double totalPagar = 0.0;
    Double subtotal = 0.0;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Login":
                Utils.distpatcherServlet(Constants.URL_LOGIN, request, response);
                break;
            
            case "Registro":
                Utils.distpatcherServlet(Constants.URL_REGISTRO, request, response);
                break; 
                
            case "Home":
                apuntes = apudao.listar();
                request.setAttribute("apuntespublicos", apuntes);
                request.getRequestDispatcher(Constants.URL_INDEX).forward(request, response);
                break;
            
            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("Carrito", listaCarrito);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubtotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                Utils.distpatcherServlet(Constants.URL_CARRITO_INICIAL, request, response);
                break;
                
            case "AgregarCarrito":
                ControladorImplements.agregarCarrito(request, listaCarrito, subtotal);
                request.setAttribute("contador", listaCarrito.size());
                Utils.distpatcherServlet(Constants.URL_HOME, request, response);
                break;
                
            case "BorrarApunteCarrito":
                ControladorImplements.eliminarApunte(Integer.valueOf(request.getParameter("id")), listaCarrito);
                break;
                
            case "ActualizarPaginaDesde":
                ControladorImplements.actualizarPaginaDesde(Integer.valueOf(request.getParameter("id")), Integer.valueOf(request.getParameter("cantidad")), listaCarrito);
                break;
                
            case "ActualizarPaginaHasta":
                ControladorImplements.actualizarPaginaHasta(Integer.valueOf(request.getParameter("id")), Integer.valueOf(request.getParameter("cantidad")), listaCarrito);
                break;    
            
            case "ActualizarCantidadCopias":
                ControladorImplements.actualizarCantidad(Integer.valueOf(request.getParameter("id")), Integer.valueOf(request.getParameter("cantidad")), listaCarrito);
                break;
            
            case "ActualizarAnillado":
                ControladorImplements.actualizarAnillado(Integer.valueOf(request.getParameter("id")), Integer.valueOf(request.getParameter("anillado")), listaCarrito);
                break;
            
            case "ActualizarTipoImpresion":
                ControladorImplements.actualizarTipoImpresion(Integer.valueOf(request.getParameter("id")), Integer.valueOf(request.getParameter("ti")), listaCarrito);
            break;
                
            default:
                request.setAttribute("apuntes", apuntes);
                request.getRequestDispatcher(Constants.URL_INDEX).forward(request, response);
        }
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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
