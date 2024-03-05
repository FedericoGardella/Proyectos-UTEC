package com.pruebaPA.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
 Esta es la implementacion de un servlet
 Cuando se ingrese a /home con los metodos GET, POST, etc se ejecutara dependiendo si sobreescribimos el metodo
*/

//@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Home() {super();}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		ISistema sis = (ISistema)req.getServletContext().getAttribute("sis");
		req.getRequestDispatcher("/WEB-INF/home/index.jsp").forward(req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Invocaste el metodo POST desde /home");
	}
}