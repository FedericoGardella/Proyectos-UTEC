package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.ISistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pruebaPA.config.ListURL;
import com.pruebaPA.models.EstadosLogin;

import datatype.DtSalidaTuristica;

public class verPaquetesDispo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public verPaquetesDispo() {  super(); }
 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonRequest;
        JSONObject jsonRet = new JSONObject();
        String sal = null;
        try {
			jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
	        sal = (String) jsonRequest.get("nomSal");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		HttpSession sesion = req.getSession();
		String tur = (String) sesion.getAttribute("userNick");
        
        ISistema sis = Fabrica.getSistema();
        
        
		List<String> lista = sis.listarPaquetesCompradosTur(tur,sal);
		
		JSONArray paquetes = new JSONArray();
		lista.forEach(t -> {
			JSONObject i = new JSONObject();
			i.put("nombre", t);
			paquetes.add(i);
		});
		
		if (lista.size() == 0) {
			jsonRet.put("vacio", "true");
		}else {
			jsonRet.put("vacio", "false");
			jsonRet.put("paquetes", paquetes);
		}
        
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.getWriter().write(jsonRet.toJSONString());
	}

}
