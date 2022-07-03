package com.controlador;

import com.configuracion.Fecha;
import com.modelo.Apunte;
import com.modelo.Carrito;
import com.modelo.Compra;
import com.modelo.Pago;
import com.modelo.PrecioFotocopia;
import com.modelo.Rol;
import com.modelo.Usuario;
import com.modeloDAO.ApunteDAO;
import com.modeloDAO.CompraDAO;
import com.modeloDAO.PagoDAO;
import com.modeloDAO.PrecioFotocopiaDAO;
import com.modeloDAO.RolDAO;
import com.modeloDAO.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.utils.Constants;
import com.utils.Utils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class Controlador extends HttpServlet {

    ApunteDAO apudao = new ApunteDAO();
    UsuarioDAO udao = new UsuarioDAO();
    CompraDAO comdao = new CompraDAO();
    RolDAO rdao = new RolDAO();
    Apunte apu = new Apunte();
    Rol r = new Rol();
    List apuntes = new ArrayList();
    List usuarios = new ArrayList();
    List miscompras = new ArrayList();
    List compAdmin = new ArrayList();
    List detallePedido = new ArrayList();
    List<Carrito> listaCarrito = new ArrayList<>();
    Double totalPagar = 0.0;
    Double subtotal = 0.0;
    int ida;
    int idd;
    int idc;
    int editFlag;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Login":
                request.setAttribute("contador", listaCarrito.size());
                Utils.distpatcherServlet(Constants.URL_LOGIN, request, response);
                break;
            
            case "Registro":
                Utils.distpatcherServlet(Constants.URL_REGISTRO, request, response);
                break;
            
            case "MostrarPDF":
                ida = Integer.parseInt(request.getParameter("id"));
                apu = apudao.listarId(ida);
                String nombreApunte = apu.getNombre();
                request.setAttribute("nombreApunte", nombreApunte);                
                Utils.distpatcherServlet(Constants.URL_PDF, request, response);
                break;
                
            case "Home":
                apuntes = apudao.listar();
                request.setAttribute("contador", listaCarrito.size());
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
                
            case "DetallePedidoUsuario":
                idd = Integer.parseInt(request.getParameter("id"));
                detallePedido = comdao.listarDetallePedido(idd);
                request.setAttribute("detallePedido", detallePedido);
                request.setAttribute("idPedido", idd);
                Utils.distpatcherServlet(Constants.URL_VISTAUSUARIODETALLEPEDIDO, request, response);
                break;
            
            case "EditarPedidoUsuario":
                int idPedido;
                editFlag = 1;
                idc = Integer.parseInt(request.getParameter("id"));
                listaCarrito = comdao.listarDetallePedido(idc);
                idPedido = idc;
                request.setAttribute("contador", listaCarrito.size());
                request.getSession(true).setAttribute("editFlag", editFlag);                
                request.getSession(true).setAttribute("idPedido", idPedido);
                Utils.distpatcherServlet(Constants.URL_CARRITO, request, response);
                break;
                
            case "CancelarPedidoBD":
                idc = Integer.valueOf(request.getParameter("id"));
                Compra comp = comdao.listarId(idc);
                comp.setEstado("Cancelado");
                comdao.ActualizarCompra(comp);
                break;
                
            case "PedidosAdmin":
                compAdmin = comdao.listar();
                PrecioFotocopiaDAO precioDAO = new PrecioFotocopiaDAO();
                PrecioFotocopia preciofo = new PrecioFotocopia();
                preciofo = precioDAO.listarMax();
                Double precio = preciofo.getValorFotocopia();
                if (compAdmin.isEmpty() == true) {
                    ControladorImplements.response(Constants.URL_HOME, "No hay pedidos realizados", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                } else {
                    request.setAttribute("compras", compAdmin);
                    request.setAttribute("precioFotocopia", precio);
                    Utils.distpatcherServlet(Constants.URL_VISTAPEDIDOS, request, response);
                }
                break;
            
            case "DetallePedidoAdmin":
                idd = Integer.parseInt(request.getParameter("id"));
                detallePedido = comdao.listarDetallePedido(idd);
                request.setAttribute("detallePedido", detallePedido);
                request.setAttribute("idPedido", idd);
                Utils.distpatcherServlet(Constants.URL_VISTAADMINDETALLEPEDIDO, request, response);
                break;
            
            case "ApuntesAdmin":
                apuntes = apudao.listar();
                if (apuntes.isEmpty() == true) {
                    ControladorImplements.response(Constants.URL_HOME, "Error al intentar listar los apuntes", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                } else {
                    request.setAttribute("apuntes", apuntes);
                    Utils.distpatcherServlet(Constants.URL_VISTAAPUNTES, request, response);
                }
                break;
            
            case "ListaUsuarios":
                usuarios = udao.listar();
                if (usuarios.isEmpty() == true) {
                    ControladorImplements.response(Constants.URL_HOME, "Error al intentar listar los usuarios.", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                } else {
                    request.setAttribute("usuarios", usuarios);
                    Utils.distpatcherServlet(Constants.URL_VISTAADMINUSUARIOS, request, response);
                }
                break;
            
            case "agregarAdminBD":
                ida = Integer.valueOf(request.getParameter("id"));
                usu = udao.listarId(ida);
                r = rdao.listarId(1);
                usu.setRol(r);
                udao.ActualizarUsuario(usu);
                break;

            case "eliminarAdminBD":
                ida = Integer.valueOf(request.getParameter("id"));
                usu = udao.listarId(ida);                
                r = rdao.listarId(2);
                usu.setRol(r);
                udao.ActualizarUsuario(usu);
                break;
                
            case "editarApunte":
                ida = Integer.parseInt(request.getParameter("id"));
                apu = apudao.listarId(ida);
                request.setAttribute("apunte", apu);
                Utils.distpatcherServlet(Constants.URL_VISTAEDITARAPUNTE, request, response);
                break;
                
            case "eliminarApunteBD":
                ida = Integer.valueOf(request.getParameter("id"));
                apudao.BorrarApunte(ida);
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
            
            case "SubirApuntes":
                ida = Integer.valueOf(request.getParameter("id"));
                apuntes = apudao.listarAlumno(ida);
                request.setAttribute("contador", listaCarrito.size());
                request.setAttribute("apuntesAlumno", apuntes);
                request.getRequestDispatcher(Constants.URL_VISTAUSUARIOAPUNTES).forward(request, response);
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
                    Compra compra = new Compra(u, pago, Fecha.FechaBD(), totalPagar, "Solicitado", listaCarrito);
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
            
            case "ActualizarPedido":
                idc = Integer.valueOf(request.getParameter("id"));
                CompraDAO compradao = new CompraDAO();
                Compra compra = compradao.listarId(idc);
                Pago pago = new Pago();
                PagoDAO pdao = new PagoDAO();
                pago = compra.getPago();
                totalPagar = (double) Math.round(totalPagar * 100) / 100d;
                pago.setMonto(totalPagar);
                pdao.ActualizarPago(pago);
                compra.setPago(pago);
                compra.setMonto(totalPagar);
                compra.setDetallecompras(listaCarrito);
                int res = compradao.ActualizarDetalleCompra(compra);
                if (res != 0 && totalPagar > 0) {
                    HttpSession session2 = request.getSession(false);
                    if (session2 != null) {
                        session2.removeAttribute("editFlag");
                        session2.removeAttribute("idPedido");
                        listaCarrito = new ArrayList();
                        ControladorImplements.response(Constants.URL_HOME, Constants.MESSAGE_SUCCESS, Constants.CONFIG_ALERT_SUCCESS, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    }
                } else {
                    ControladorImplements.response(Constants.URL_HOME, Constants.MESSAGE_WARNING_CARRITO, Constants.CONFIG_ALERT_WARNING, request);
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
