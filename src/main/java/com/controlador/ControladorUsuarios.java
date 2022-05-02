package com.controlador;

import com.modelo.Rol;
import com.modelo.Usuario;
import com.modeloDAO.UsuarioDAO;
import com.utils.Constants;
import com.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {
    
    UsuarioDAO udao = new UsuarioDAO();
    Usuario usu = new Usuario();
    Rol rol = new Rol();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorUsuarios at " + request.getContextPath() + "</h1>");
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
            case "Ingresar":
                String user = request.getParameter("txtuser");
                String pass = request.getParameter("txtpass");
                HttpSession session = request.getSession(true);
                try {
                    usu = udao.validar(user, pass);
                } catch (Exception ex) {
                    ControladorImplements.response(Constants.URL_INICIOSESION, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    break;
                }
                if (usu.getEmail() != null) {
                    if(usu.getRol().getId()== 1){ //Es Admin
                        session.setAttribute("admin", usu);
                        Utils.distpatcherServlet(Constants.URL_HOME, request, response);
                    } else { //Es alumno
                        session.setAttribute("alumno", usu);
                        Utils.distpatcherServlet(Constants.URL_HOME, request, response);
                    }                    
                } else {
                    ControladorImplements.response(Constants.URL_INICIOSESION, "Usuario o Contraseña incorrecta", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }            
            break;
            
            case "Registrar":
                String legajo = request.getParameter("txtlegajo");
                String nombre = request.getParameter("txtnombre");
                String apellido = request.getParameter("txtapellido");
                String email = request.getParameter("txtemail");
                String password = request.getParameter("txtpass");
                if (legajo != null && nombre != null && apellido != null && email != null && password != null) {
                    rol.setId(2);
                    rol.setDescripcion("alumno");
                    usu.setLegajo(legajo);
                    usu.setNombre(nombre);
                    usu.setApellido(apellido);
                    usu.setEmail(email);
                    usu.setPassword(password);
                    usu.setRol(rol);
                    try {
                        udao.AgregarNuevoUsuario(usu);
                        Utils.distpatcherServlet(Constants.URL_LOGIN, request, response);
                    } catch (Exception ex) {
                        ControladorImplements.response(Constants.URL_REGISTRO, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    }
                } else {
                    ControladorImplements.response(Constants.URL_REGISTRO, "Faltan completar campos", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
            break;
            
            case "Actualizar":
            
            break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
