package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.ISistema;

import java.io.IOException;


//@WebServlet("/paquete")
public class Paquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Paquete() {super();}

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	ISistema sis = Fabrica.getSistema();
    	HttpSession sesion = req.getSession();
    	sesion.setAttribute("paquetes",sis.listarPaquetesExistentes());
		req.getRequestDispatcher("/WEB-INF/paquete/index.jsp").forward(req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Invocaste el metodo POST desde /paquete");
	}
}