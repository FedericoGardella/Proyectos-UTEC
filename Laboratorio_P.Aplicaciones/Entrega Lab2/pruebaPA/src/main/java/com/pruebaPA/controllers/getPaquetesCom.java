package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import logica.Fabrica;
import logica.ISistema;

//@WebServlet("/getpcom")
public class getPaquetesCom extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getPaquetesCom() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonRet = new JSONObject();
		JSONArray paquetes = new JSONArray();
		
		HttpSession sesion = request.getSession();
		String tur = (String) sesion.getAttribute("userNick");
		
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarPaquetesExistentesNoCompradosConActividad(tur);
		lista.forEach(t -> {
			JSONObject i = new JSONObject();
			i.put("nombre", t);
			paquetes.add(i);
		});
		jsonRet.put("paquetes", paquetes);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());
		
	}


}