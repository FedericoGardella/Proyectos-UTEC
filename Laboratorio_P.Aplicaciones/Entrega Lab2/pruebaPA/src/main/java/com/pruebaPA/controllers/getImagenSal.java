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

public class getImagenSal extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getImagenSal() {
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
        String doc = null;
        String act = null;
        String sal = null;
        try {
			jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
	        doc = (String) jsonRequest.get("DoC");
	        act = (String) jsonRequest.get("nombAct");
	        sal = (String) jsonRequest.get("nombSal");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ISistema sis = Fabrica.getSistema();
		String image = sis.getImagenSal(doc, act, sal);
		if (image != null)
			jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "sals/" + image);
		else
			jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "amiguis.png");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());
		
	}


}