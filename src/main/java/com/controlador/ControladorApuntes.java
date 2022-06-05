
package com.controlador;

import com.modelo.Apunte;
import com.modeloDAO.ApunteDAO;
import com.utils.Constants;
import com.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.pdfbox.pdmodel.PDDocument;


@WebServlet(name = "ControladorApuntes", urlPatterns = {"/ControladorApuntes"})
public class ControladorApuntes extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorApuntes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorApuntes at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException{
        String accion = request.getParameter("accion");        
        switch(accion){
            case "Agregar":
                Apunte a = new Apunte();
                ApunteDAO adao = new ApunteDAO();
                boolean flagAlumno = false;
                int idAlumno = 0;
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException ex) {
                    //ControladorImplements.response(Constants.URL_ADMINAPUNTES, "Error al procesar el apunte", Constants.CONFIG_ALERT_WARNING, request);
                    //Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                for (Object item : items) {
                    FileItem uploaded = (FileItem) item;
                    // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero                
                    if (!uploaded.isFormField()) {
                        // No es campo de formulario, guardamos el fichero
                        File fichero = new File("C:\\Users\\PC\\Documents\\NetBeansProjects\\apunteca\\src\\main\\webapp\\apuntes\\", uploaded.getName());
                        try {
                            uploaded.write(fichero);
                            PDDocument doc = PDDocument.load(fichero);
                            int cantPaginas = doc.getNumberOfPages();                        
                            a.setCantPaginas(cantPaginas);
                            a.setNombre(uploaded.getName());
                        } catch (Exception ex) {
                             //ControladorImplements.response(Constants.URL_ADMINAPUNTES, "Error al procesar el apunte", Constants.CONFIG_ALERT_WARNING, request);
                            //Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                        }
                    } else {
                        // es un campo de formulario,
                        if ("txtDescripcion".equals(uploaded.getFieldName())) {
                            a.setDescripcion(uploaded.getString());
                        }
                        if ("cboCarrera".equals(uploaded.getFieldName())) {
                            a.setCarrera(uploaded.getString());
                        }
                        if ("txtMateria".equals(uploaded.getFieldName())) {
                            a.setMateria(uploaded.getString());
                        }
                        if ("hideAlumno".equals(uploaded.getFieldName())) {
                            idAlumno = Integer.parseInt(uploaded.getString());
                            a.setIdAlumno(idAlumno);
                            flagAlumno = true;
                            a.setUpload(Boolean.TRUE);                            
                        }
                    }
                }
                if (flagAlumno == false){
                    a.setUpload(Boolean.FALSE);                
                    a.setIdAlumno(1);
                    try {
                        adao.AgregarNuevoApunte(a);
                        Utils.distpatcherServlet(Constants.URL_ADMINAPUNTES, request, response);
                    } catch (Exception ex) {
                        ControladorImplements.response(Constants.URL_ADMINAPUNTES, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    }
                }
                else{
                    try {
                        adao.AgregarNuevoApunte(a);
                        Utils.distpatcherServlet("./Controlador?accion=SubirApuntes&id="+idAlumno, request, response);
                    } catch (Exception ex) {
                        ControladorImplements.response("./Controlador?accion=SubirApuntes&id="+idAlumno, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    }
                }
            break;

            case "Actualizar":
                int ida = Integer.parseInt(request.getParameter("id"));
                boolean flagFile = false;
                Apunte a2 = new Apunte();
                ApunteDAO a2dao = new ApunteDAO();
                FileItemFactory factory2 = new DiskFileItemFactory();
                ServletFileUpload upload2 = new ServletFileUpload(factory2);
                List items2 = null;
                try {
                    items2 = upload2.parseRequest(request);
                } catch (FileUploadException ex) {
                    ControladorImplements.response(Constants.URL_ADMINAPUNTES, "Error al procesar el apunte", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                for (Object item : items2) {
                    FileItem uploaded2 = (FileItem) item;
                    // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero                
                    if (!uploaded2.isFormField()) {
                        // No es campo de formulario, guardamos el fichero
                        if (uploaded2.getSize() > 0) { //Si actualizó el archivo del apunte
                            flagFile = true;
                            File fichero = new File("C:\\Users\\PC\\Documents\\NetBeansProjects\\apunteca\\src\\main\\webapp\\apuntes\\", uploaded2.getName());
                            try {
                                uploaded2.write(fichero);
                                PDDocument doc = PDDocument.load(fichero);
                                int cantPaginas = doc.getNumberOfPages();                        
                                a2.setCantPaginas(cantPaginas);
                                a2.setNombre(uploaded2.getName());
                            } catch (Exception ex) {
                                ControladorImplements.response(Constants.URL_ADMINAPUNTES, "Error al procesar el apunte", Constants.CONFIG_ALERT_WARNING, request);
                                Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                            }
                        }
                    } else {
                        // es un campo de formulario,
                        if("txtNombre".equals(uploaded2.getFieldName()) && flagFile == false){
                            a2.setNombre(uploaded2.getString());                            
                        }
                        if ("txtDescripcion".equals(uploaded2.getFieldName())) {
                            a2.setDescripcion(uploaded2.getString());
                        }
                        if ("cboCarrera".equals(uploaded2.getFieldName())) {
                            a2.setCarrera(uploaded2.getString());
                        }
                        if ("txtMateria".equals(uploaded2.getFieldName())) {
                            a2.setMateria(uploaded2.getString());
                        } 
                        if ("numCantPaginas".equals(uploaded2.getFieldName()) && flagFile == false) {
                            a2.setCantPaginas(Integer.parseInt(uploaded2.getString()));
                        }
                    }
                }
                a2.setId(ida);
                a2.setUpload(Boolean.FALSE);
                a2.setIdAlumno(1);
                try {
                    a2dao.ActualizarApunte(a2);
                    Utils.distpatcherServlet(Constants.URL_ADMINAPUNTES, request, response);
                } catch (Exception ex) {
                    ControladorImplements.response(Constants.URL_ADMINAPUNTES, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
            break;
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
