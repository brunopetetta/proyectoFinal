
package com.controlador;

import com.modelo.topPedidos;
import com.modeloDAO.CompraDAO;
import com.modeloDAO.PrecioFotocopiaDAO;
import com.utils.Constants;
import com.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorReportes", urlPatterns = {"/ControladorReportes"})
public class ControladorReportes extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorReportes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorReportes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        CompraDAO comdao = new CompraDAO();
        String accion = request.getParameter("accion");
        switch(accion){
            case "Filtrar":
                try {
                    String reporte = request.getParameter("reporte");
                    switch(reporte){
                        case "1":
                            String fechaRango = request.getParameter("date_range");
                            String fechaDesde = fechaRango.substring(0, 10);
                            String fechaHasta = fechaRango.substring(13);
                            int cancelados = comdao.listarEstado("Cancelado", fechaDesde, fechaHasta);
                            int enproceso = comdao.listarEstado("En Proceso", fechaDesde, fechaHasta);
                            int solicitados = comdao.listarEstado("Solicitado", fechaDesde, fechaHasta);
                            int retirados = comdao.listarEstado("Retirado", fechaDesde, fechaHasta);
                            int listopararetirar = comdao.listarEstado("Listo para Retirar", fechaDesde, fechaHasta);
                            request.setAttribute("cancelados", cancelados);
                            request.setAttribute("enproceso", enproceso);
                            request.setAttribute("solicitados", solicitados);
                            request.setAttribute("retirados", retirados);
                            request.setAttribute("listopararetirar", listopararetirar);
                            request.setAttribute("fechaDesde", fechaDesde);
                            request.setAttribute("fechaHasta", fechaHasta);
                            Utils.distpatcherServlet(Constants.URL_VISTAREPORTES1, request, response);
                            break;
                            
                        case "2":
                            List<topPedidos> topCincoPed = new ArrayList<>();
                            String fechaRango2 = request.getParameter("date_range");
                            String fechaDesde2 = fechaRango2.substring(0, 10);
                            String fechaHasta2 = fechaRango2.substring(13);
                            int isi = comdao.listarCarrera("Ingeniería en Sistemas de Información", fechaDesde2, fechaHasta2);
                            int im = comdao.listarCarrera("Ingeniería Mecánica", fechaDesde2, fechaHasta2);
                            int ic = comdao.listarCarrera("Ingeniería Civil", fechaDesde2, fechaHasta2);
                            int ie = comdao.listarCarrera("Ingenieria Eléctrica", fechaDesde2, fechaHasta2);
                            int iq = comdao.listarCarrera("Ingeniería Química", fechaDesde2, fechaHasta2); 
                            double porcISI = (isi * 100) / (isi+im+ic+ie+iq);
                            double porcIM = (im * 100) / (isi+im+ic+ie+iq);
                            double porcIC = (ic * 100) / (isi+im+ic+ie+iq);
                            double porcIE = (ie * 100) / (isi+im+ic+ie+iq);
                            double porcIQ = (iq * 100) / (isi+im+ic+ie+iq);
                            topCincoPed = comdao.listarTopPedidos(fechaDesde2, fechaHasta2);
                            request.setAttribute("fechaDesde", fechaDesde2);
                            request.setAttribute("fechaHasta", fechaHasta2);
                            request.setAttribute("topPedidos", topCincoPed);
                            request.setAttribute("porcISI", porcISI);
                            request.setAttribute("porcIM", porcIM);
                            request.setAttribute("porcIC", porcIC);
                            request.setAttribute("porcIE", porcIE);
                            request.setAttribute("porcIQ", porcIQ);
                            Utils.distpatcherServlet(Constants.URL_VISTAREPORTES2, request, response);
                            break;
                            
                        case "3":
                            List valoresFotocopia = new ArrayList();
                            String fechaRango3 = request.getParameter("date_range");
                            String fechaDesde3 = fechaRango3.substring(0, 10);
                            String fechaHasta3 = fechaRango3.substring(13);
                            int cantHojas = comdao.listarHojas(fechaDesde3, fechaHasta3);
                            int cantAnillados = comdao.listarAnillados(fechaDesde3, fechaHasta3);
                            double recaudacion = comdao.listarRecaudado(fechaDesde3, fechaHasta3);
                            PrecioFotocopiaDAO prDAO = new PrecioFotocopiaDAO();
                            valoresFotocopia = prDAO.listar();
                            request.setAttribute("fechaDesde", fechaDesde3);
                            request.setAttribute("fechaHasta", fechaHasta3);
                            request.setAttribute("recaudacion", recaudacion);
                            request.setAttribute("cantHojas", cantHojas);
                            request.setAttribute("cantAnillados", cantAnillados);
                            request.setAttribute("valoresFotocopia", valoresFotocopia);
                            Utils.distpatcherServlet(Constants.URL_VISTAREPORTES3, request, response);                
                            break;
                    }
                    
                }
                catch (Exception e) {
                    
                }
                break;
        }
 
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
