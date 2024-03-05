package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet("/listarUsuarios")
public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ListUsers() {  super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		req.getRequestDispatcher("/WEB-INF/user/listUsers.jsp").forward(req, res);
	}


}
