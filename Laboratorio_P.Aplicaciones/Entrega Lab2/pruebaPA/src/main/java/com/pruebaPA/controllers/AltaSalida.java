package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pruebaPA.models.EstadosLogin;

//@WebServlet("/altasalida")
public class AltaSalida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AltaSalida() {super();}


	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesion = req.getSession(false);
		
        if (sesion != null && sesion.getAttribute("estado_sesion") == EstadosLogin.LOGIN)
        	req.getRequestDispatcher("/WEB-INF/altasalida/index.jsp").forward(req, res);
		else
			res.sendRedirect(req.getContextPath() + "/home");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
