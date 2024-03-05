package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import logica.Fabrica;
import logica.ISistema;

//@WebServlet("/getd")
public class getDepartamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getDepartamentos() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonRet = new JSONObject();
		JSONArray departamentos = new JSONArray();
		ISistema sis = Fabrica.getSistema();
		List<String> lista = sis.listarDepartamentos();
		lista.forEach(t -> {
			JSONObject i = new JSONObject();
			i.put("nombre", t);
			departamentos.add(i);
		});
		jsonRet.put("departamentos", departamentos);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());
		
	}


}
