
package com.controlador;

import com.configuracion.Fecha;
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
                    ControladorImplements.response(Constants.URL_ADMINPEDIDOS, Constants.MESSAGE_SUCCESS, Constants.CONFIG_ALERT_SUCCESS, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                } catch (Exception e) {
                    ControladorImplements.response(Constants.URL_ADMINPEDIDOS, e.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                break;
            case "Actualizar Valor":
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
                break;       
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

