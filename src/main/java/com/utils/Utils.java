/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class Utils {
    public static void distpatcherServlet(String url, HttpServletRequest request, HttpServletResponse reponse) throws  ServletException, IOException{
        request.getRequestDispatcher(url).forward(request, reponse);
    }
}
