package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pruebaPA.models.EstadosLogin;

/**
 * Servlet implementation class Logout
 */
//@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Logout() { super(); }
	
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	 HttpSession sesion = req.getSession(false);
         if (sesion != null && sesion.getAttribute("estado_sesion") == EstadosLogin.LOGIN) {
        	 sesion.invalidate(); // Invalida la sesión
         }
         res.sendRedirect(req.getContextPath() + "/home"); // Redirige a la página de inicio de sesión o a donde desees
    }

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
