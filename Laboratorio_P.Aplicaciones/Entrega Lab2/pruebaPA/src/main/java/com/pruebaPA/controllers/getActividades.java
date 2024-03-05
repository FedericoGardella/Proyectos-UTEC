package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import logica.Fabrica;
import logica.ISistema;

public class getActividades extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getActividades() {
        super();

    }

    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonRequest;
        JSONObject jsonRet = new JSONObject();
        String dep = null;
        try {
			jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
	        dep = (String) jsonRequest.get("nomDep");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONArray actividades = new JSONArray();
		ISistema sis = Fabrica.getSistema();
		List<String> lista = new ArrayList<>();
		try {
			lista = sis.listarActividadesConfirmadasDeDepartamento(dep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lista.forEach(t -> {
			JSONObject i = new JSONObject();
			i.put("nombre", t);
			actividades.add(i);
		});
		jsonRet.put("actividades", actividades);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());
		
	}


}
