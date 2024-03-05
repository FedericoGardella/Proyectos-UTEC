package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pruebaPA.config.ListURL;

import datatype.DtSalidaTuristica;
import logica.Fabrica;
import logica.ISistema;

//@WebServlet("/getdts")
public class getDtSalidas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
    public getDtSalidas() {
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
        String sal = null;
        try {
			jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
	        sal = (String) jsonRequest.get("nomSal");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        ISistema sis = Fabrica.getSistema();
		DtSalidaTuristica salida = sis.getDtSalida(sal);
		String image = sis.getImagenSal(sal);
		if (image != null)
			jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "sals/" + image);
		else
			jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "amiguis.png");
		jsonRet.put("nombre", salida.getNombreSalida());
		jsonRet.put("fecha", salida.getFechaSalida().format(formatoFecha));
		jsonRet.put("hora", salida.getHoraSalida().format(formatoHora));
		jsonRet.put("lugar", salida.getLugarSalida());
		jsonRet.put("cant", salida.getCantMaxTur());
		jsonRet.put("alta", salida.getFechaAlta().format(formatoFecha));
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());
    }
    
    
}
    