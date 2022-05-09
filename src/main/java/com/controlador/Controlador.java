package com.controlador;

import com.configuracion.Fecha;
import com.modelo.Carrito;
import com.modelo.Compra;
import com.modelo.Pago;
import com.modelo.Usuario;
import com.modeloDAO.ApunteDAO;
import com.modeloDAO.CompraDAO;
import com.modeloDAO.PagoDAO;
import com.modeloDAO.UsuarioDAO;
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
import javax.servlet.http.HttpSession;

public class Controlador extends HttpServlet {

    ApunteDAO apudao = new ApunteDAO();
    UsuarioDAO udao = new UsuarioDAO();
    CompraDAO comdao = new CompraDAO();
    List apuntes = new ArrayList();
    List miscompras = new ArrayList();
    List compAdmin = new ArrayList();
    List<Carrito> listaCarrito = new ArrayList<>();
    Double totalPagar = 0.0;
    Double subtotal = 0.0;
    int ida;
    
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
            
            case "PerfilUsuario":
                ida = Integer.parseInt(request.getParameter("id"));
                Usuario usu = udao.listarId(ida);
                request.setAttribute("alumno", usu);
                Utils.distpatcherServlet(Constants.URL_PERFIL, request, response);
                break;
                
            case "PedidosUsuario":
                ida = Integer.parseInt(request.getParameter("id"));
                miscompras = comdao.listarPorUsuario(ida);
                if (miscompras.isEmpty() == true) {
                    ControladorImplements.response(Constants.URL_HOME, "No ha realizado pedidos aún", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                } else {
                    request.setAttribute("miscompras", miscompras);
                    Utils.distpatcherServlet(Constants.URL_USUARIOPEDIDOS, request, response);
                }
                break;
                
            case "PedidosAdmin":
                compAdmin = comdao.listar();
                if (compAdmin.isEmpty() == true) {
                    ControladorImplements.response(Constants.URL_HOME, "No hay pedidos realizados", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                } else {
                    request.setAttribute("compras", compAdmin);
                    Utils.distpatcherServlet(Constants.URL_ADMINPEDIDOS, request, response);
                }
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
            
            case "ActualizarObservaciones":
                ControladorImplements.actualizarObservaciones(Integer.valueOf(request.getParameter("id")), String.valueOf(request.getParameter("obs")), listaCarrito);
            break;
            
            case "GenerarPedido":
                HttpSession sesion = request.getSession(true);
                Usuario u = (Usuario) sesion.getAttribute("alumno");
                
                if (u != null) {
                    CompraDAO codao = new CompraDAO();
                    Pago pago = new Pago();
                    totalPagar = (double) Math.round(totalPagar * 100) / 100d;
                    pago.setMonto(totalPagar);
                    PagoDAO padao = new PagoDAO();
                    int idpago = padao.GenerarPago(pago);
                    pago.setId(idpago);                    
                    Compra compra = new Compra(u, pago, Fecha.FechaBD(), totalPagar, "En Proceso", listaCarrito);
                    int res = codao.GenerarCompra(compra);
                    if (res != 0 && totalPagar > 0) {
                        listaCarrito = new ArrayList<>();
                        ControladorImplements.response(Constants.URL_HOME, Constants.MESSAGE_SUCCESS, Constants.CONFIG_ALERT_SUCCESS, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    } else {
                        ControladorImplements.response(Constants.URL_HOME, Constants.MESSAGE_WARNING_CARRITO, Constants.CONFIG_ALERT_WARNING, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    }
                } else {
                    ControladorImplements.response(Constants.URL_LOGIN, "Debe iniciar sesión para realizar el pedido", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                break;
            
            case "Salir":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.removeAttribute("admin");
                    session.removeAttribute("alumno");
                    session.invalidate();
                    listaCarrito = new ArrayList();
                    Utils.distpatcherServlet(Constants.URL_LOGIN, request, response);
                }
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
