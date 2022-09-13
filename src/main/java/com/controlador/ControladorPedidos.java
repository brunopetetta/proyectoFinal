
package com.controlador;

import com.configuracion.Fecha;
import com.configuracion.SendMail;
import com.modelo.Compra;
import com.modelo.Pago;
import com.modelo.PrecioFotocopia;
import com.modelo.Usuario;
import com.modeloDAO.CompraDAO;
import com.modeloDAO.PagoDAO;
import com.modeloDAO.PrecioFotocopiaDAO;
import com.modeloDAO.UsuarioDAO;
import com.utils.Constants;
import com.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControladorPedidos", urlPatterns = {"/ControladorPedidos"})
public class ControladorPedidos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch(accion){
            case "Actualizar":
                try {
                    String fechac = request.getParameter("txtfecha");
                    Double montoc = Double.parseDouble(request.getParameter("txtmonto"));
                    String estado = request.getParameter("cboEstado");
                    int id = Integer.parseInt(request.getParameter("txtidcomp"));
                    int idu = Integer.parseInt(request.getParameter("txtidusu"));
                    int idp = Integer.parseInt(request.getParameter("txtidpago"));
                    if("Cancelado".equals(estado)){
                        request.setAttribute("fechaCompra", fechac);
                        request.setAttribute("monto", montoc);
                        request.setAttribute("estado", estado);
                        request.setAttribute("idCompra", id);
                        request.setAttribute("idAlumno", idu);
                        request.setAttribute("idpago", idp);
                        Utils.distpatcherServlet(Constants.URL_VISTAADMINCANCELAR, request, response);
                    }
                    else{
                        Compra com = new Compra();
                        CompraDAO compdao = new CompraDAO();
                        UsuarioDAO udao = new UsuarioDAO();
                        Usuario usu = udao.listarId(idu);
                        PagoDAO pdao = new PagoDAO();
                        Pago p = pdao.listarId(idp);
                        com.setId(id);
                        com.setUsuario(usu);
                        com.setPago(p);
                        com.setFecha(fechac);
                        com.setMonto(montoc);
                        com.setEstado(estado);
                        compdao.ActualizarCompra(com);
                        if("Listo para Retirar".equals(estado)){
                            String destinatario = usu.getEmail();
                            String asunto = "Tu pedido n� "+id+" est� listo para retirar";
                            String mensaje = "Hola "+usu.getNombre()+", el pedido "+id+" est� listo para ser retirado. Recorda que el monto es $"+montoc+" y se retira en fotocopiadora del 2do piso.";
                            SendMail.enviarEmail(destinatario, asunto, mensaje);
                        }
                        if("En Proceso".equals(estado)){
                            String destinatario = usu.getEmail();
                            String asunto = "Tu pedido n� "+id+" ha sido confirmado";
                            String mensaje = "Hola "+usu.getNombre()+", el pedido "+id+" fue confirmado y est� siendo procesado. Recorda que no debes ir a retirar tu pedido hasta que recibas un mail de confirmaci�n de retiro.";
                            SendMail.enviarEmail(destinatario, asunto, mensaje);
                        }
                            ControladorImplements.response(Constants.URL_ADMINPEDIDOS, Constants.MESSAGE_SUCCESS, Constants.CONFIG_ALERT_SUCCESS, request);
                            Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    }                    
                } catch (Exception e) {
                    ControladorImplements.response(Constants.URL_ADMINPEDIDOS, e.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                break;
            case "Actualizar Valor":
                if (request.getParameter("precioFoto").equals("")){
                    ControladorImplements.response(Constants.URL_ADMINPEDIDOS, "Debe ingresar un valor para la fotocopia", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                else{
                    Double valor = Double.valueOf(request.getParameter("precioFoto"));
                    PrecioFotocopia precioF = new PrecioFotocopia();
                    precioF.setValorFotocopia(valor);
                    precioF.setFechaDesdePrecio(Fecha.FechaBD());
                    PrecioFotocopiaDAO prDao = new PrecioFotocopiaDAO();

                    try {
                        prDao.ActualizarPrecio(precioF);
                        ControladorImplements.response(Constants.URL_ADMINPEDIDOS, Constants.MESSAGE_SUCCESS, Constants.CONFIG_ALERT_SUCCESS, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    } catch (Exception ex) {
                        ControladorImplements.response(Constants.URL_ADMINPEDIDOS, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    }
                }
                break;
            case "Enviar":
                try {
                    String fechac = request.getParameter("hidefecha");
                    Double montoc = Double.parseDouble(request.getParameter("hidemonto"));
                    String estado = request.getParameter("hideestado");
                    int id = Integer.parseInt(request.getParameter("hideidCompra"));
                    int idu = Integer.parseInt(request.getParameter("hideidAlumno"));
                    int idp = Integer.parseInt(request.getParameter("hideidpago"));
                    String motivo = request.getParameter("txtmotivo");
                    Compra com = new Compra();
                    CompraDAO compdao = new CompraDAO();
                    UsuarioDAO udao = new UsuarioDAO();
                    Usuario usu = udao.listarId(idu);
                    PagoDAO pdao = new PagoDAO();
                    Pago p;
                    p = pdao.listarId(idp);
                    com.setId(id);
                    com.setUsuario(usu);
                    com.setPago(p);
                    com.setFecha(fechac);
                    com.setMonto(montoc);
                    com.setEstado(estado);
                    compdao.ActualizarCompra(com);
                    String destinatario = usu.getEmail();
                    String asunto = "Tu pedido n� "+id+" ha sido cancelado";
                    String mensaje = "Hola "+usu.getNombre()+", el pedido "+id+" que ten�a un importe de $"+montoc+" ha sido cancelado. La raz�n es la siguiente: "+motivo+"";
                    SendMail.enviarEmail(destinatario, asunto, mensaje);
                    ControladorImplements.response(Constants.URL_ADMINPEDIDOS, Constants.MESSAGE_SUCCESS, Constants.CONFIG_ALERT_SUCCESS, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);                    
                } catch (Exception ex) {
                    ControladorImplements.response(Constants.URL_ADMINPEDIDOS, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }               
                
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

