package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pruebaPA.models.EstadosLogin;

/**
 * Servlet implementation class Inscribirse
 */
public class Inscribirse extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Inscribirse() { super(); }
	
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesion = req.getSession(false);
		if (sesion == null || sesion.getAttribute("estado_sesion") != EstadosLogin.LOGIN || !sesion.getAttribute("userType").equals("Turista")) {
			res.sendRedirect(req.getContextPath() + "/home"); // Redirige a la página de inicio de sesión o a donde desees
			return;
		}		
    	req.getRequestDispatcher("/WEB-INF/salida/inscribirte.jsp").forward(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
