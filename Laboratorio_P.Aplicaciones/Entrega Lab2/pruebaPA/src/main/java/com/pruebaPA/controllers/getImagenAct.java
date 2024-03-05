package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pruebaPA.config.ListURL;

import logica.Fabrica;
import logica.ISistema;

//@WebServlet("/getia")
public class getImagenAct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getImagenAct() {
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
        String cod = null;
        String act = null;
        try {
			jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
	        cod = (String) jsonRequest.get("CoD");
	        act = (String) jsonRequest.get("nomAct");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ISistema sis = Fabrica.getSistema();
		String image = sis.getImagenAct(cod,act);
		if (image != null)
			jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "acts/" + image);
		else
			jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "consact.png");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());
		
	}


}
