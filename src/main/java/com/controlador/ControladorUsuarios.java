package com.controlador;

import com.configuracion.SendMail;
import com.modelo.Rol;
import com.modelo.Usuario;
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {
    
    UsuarioDAO udao = new UsuarioDAO();
    Usuario usu = new Usuario();
    Usuario usu2 = new Usuario();
    Rol rol = new Rol();
    String dni;
    String legajo;
    String nombre;
    String apellido;
    String email;
    String password;
    String esadmin;
    
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
                        Utils.distpatcherServlet(Constants.URL_ADMINPEDIDOS, request, response);
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
                dni = request.getParameter("txtdni");
                legajo = request.getParameter("txtlegajo");
                nombre = request.getParameter("txtnombre");
                apellido = request.getParameter("txtapellido");
                email = request.getParameter("txtemail");
                password = request.getParameter("txtpass");
                esadmin = request.getParameter("hideadmin");
                if (dni != "" && legajo != "" && nombre != "" && apellido != "" && email != "" && password != "") {
                    rol.setId(2);
                    rol.setDescripcion("alumno");
                    usu2.setDni(dni);
                    usu2.setLegajo(legajo);
                    usu2.setNombre(nombre);
                    usu2.setApellido(apellido);
                    usu2.setEmail(email);
                    usu2.setPassword(password);
                    usu2.setRol(rol);
                    if(esadmin.equals("1")){
                        try {
                            udao.AgregarNuevoUsuario(usu2);
                            ControladorImplements.response("./Controlador?accion=ElegirAlumno", "Se registro su usuario con Éxito!", Constants.CONFIG_ALERT_SUCCESS, request);
                            Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                        } catch (Exception ex) {
                            ControladorImplements.response(Constants.URL_REGISTRO, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                            Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                        }
                    }
                    else{
                        try {
                        udao.AgregarNuevoUsuario(usu2);
                            ControladorImplements.response(Constants.URL_LOGIN, "Se registro su usuario con Éxito!", Constants.CONFIG_ALERT_SUCCESS, request);
                            Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                        } catch (Exception ex) {
                            ControladorImplements.response(Constants.URL_REGISTRO, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                            Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                        }
                    }                    
                } else {
                    ControladorImplements.response("./Controlador?accion=Registro", "Faltan completar campos", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                break;
            
            case "Actualizar":
                int idu = Integer.parseInt(request.getParameter("id"));
                dni = request.getParameter("txtdni");
                legajo = request.getParameter("txtlegajo");
                nombre = request.getParameter("txtnombre");
                apellido = request.getParameter("txtapellido");
                email = request.getParameter("txtemail");
                password = request.getParameter("txtpass");
                if (dni != "" && legajo != "" && nombre != "" && apellido != "" && email != "" && password != "") {
                    usu.setId(idu);
                    usu.setDni(dni);
                    usu.setLegajo(legajo);
                    usu.setNombre(nombre);
                    usu.setApellido(apellido);
                    usu.setEmail(email);
                    usu.setPassword(password);
                    try {
                        udao.ActualizarUsuario(usu);
                        ControladorImplements.response(Constants.URL_HOME, "Se actualizaron los campos con Éxito!", Constants.CONFIG_ALERT_SUCCESS, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    } catch (Exception ex) {
                        ControladorImplements.response(Constants.URL_PERFIL, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                        Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                    }
                } else {
                    ControladorImplements.response(Constants.URL_PERFIL, "Faltan completar campos", Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                break;
            
            case "Recuperar":
                email = request.getParameter("txtemail");
                try {
                    usu = udao.listarEmail(email);
                    usu.setPassword("mg19fayt91b0");
                    udao.ActualizarUsuario(usu);
                    String destinatario = email;
                    String asunto = "Nueva Contraseña";
                    String mensaje = "Hola "+usu.getNombre()+", su nueva contraseña es mg19fayt91b0. Puede ingresar y cambiarla si lo desea.";
                    SendMail.enviarEmail(destinatario, asunto, mensaje);
                    ControladorImplements.response(Constants.URL_LOGIN, "Se ha enviado su nueva contraseña a su correo", Constants.CONFIG_ALERT_SUCCESS, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                } catch (Exception ex) {
                    ControladorImplements.response(Constants.URL_LOGIN, ex.getMessage(), Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }            
               
                break;
            
            case "Elegir":
                int id = Integer.parseInt(request.getParameter("cboAlumno"));                
                HttpSession sesion = request.getSession(true);
                try {
                    usu = udao.listarId(id);
                    sesion.setAttribute("alumnoPedido", usu);
                    Utils.distpatcherServlet(Constants.URL_HOME, request, response);
                } catch (Exception ex) {

                }               
                break;



            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
