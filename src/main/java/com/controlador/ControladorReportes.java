
package com.controlador;

import com.modeloDAO.CompraDAO;
import com.utils.Constants;
import com.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
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
        String accion = request.getParameter("accion");
        switch(accion){
            case "Filtrar":
                try {
                    String fechaRango = request.getParameter("date_range");
                    CompraDAO comdao = new CompraDAO();
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
                    Utils.distpatcherServlet(Constants.URL_VISTAREPORTES, request, response);
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
