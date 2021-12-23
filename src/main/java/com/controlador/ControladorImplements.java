/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controlador;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PC
 */
public class ControladorImplements {
    
    public static void response(String url, String message, String alert,HttpServletRequest request) {
        request.setAttribute("URL", url);
        request.setAttribute("mensaje", message);
        request.setAttribute("alert", alert);
    }
    
}
