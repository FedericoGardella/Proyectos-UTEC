package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

import com.pruebaPA.config.ListURL;

import logica.Fabrica;
import logica.ISistema;

//@WebServlet("/getacp")
public class getActividadesCP extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getActividadesCP() {
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
        String paqs = null;
        try {
			jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
	        paqs = (String) jsonRequest.get("nomPaq");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONArray actividades = new JSONArray();
		ISistema sis = Fabrica.getSistema();
		List<String> lista = new ArrayList<>();
		List<String> list = new ArrayList<>();
		try {
			lista = sis.verActividadesCP(paqs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lista.forEach(t -> {
			JSONObject i = new JSONObject();
			i.put("nombre", t);
			actividades.add(i);
		});
		list = sis.actividadURL(paqs, lista);
		
		JSONArray jsonArray = new JSONArray(); // Para almacenar objetos JSON

		for (String image : list) {
		    JSONObject jsonObject = new JSONObject(); // Objeto JSON para cada elemento
		    
		    if ("nulo".equals(image)) {
		        jsonObject.put("imagen", ListURL.ImagesURL.getURL() + "consact.png");
		    } else {
		        jsonObject.put("imagen", ListURL.ImagesURL.getURL() + "acts/" + image);
		    }
		    
		    jsonArray.add(jsonObject); // Agrega el objeto JSON al arreglo
		}

		// Ahora tienes un JSONArray con objetos JSON que contienen URLs de imagen
		String jsonString = jsonArray.toJSONString(); // Convierte el JSONArray a una cadena JSON

		
		
		jsonRet.put("actividades", actividades);
		jsonRet.put("imagenes", jsonString);
		/* jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "consact.png"); */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());
		
	}


}