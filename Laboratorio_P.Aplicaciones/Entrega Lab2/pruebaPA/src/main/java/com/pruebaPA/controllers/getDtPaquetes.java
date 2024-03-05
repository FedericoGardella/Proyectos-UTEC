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

import datatype.DtPaquete;
import logica.Fabrica;
import logica.ISistema;


public class getDtPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public getDtPaquetes() {
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
        String paq = null;
        try {
			jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
	        paq = (String) jsonRequest.get("nomPaq");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        ISistema sis = Fabrica.getSistema();
		DtPaquete paquete;
		float costoTotal;
		try {
			paquete = sis.imprimirInfoPaquete(paq);
			costoTotal = sis.obtenerCostoTotal(paq);
			jsonRet.put("nombre", paquete.getNombre());
			jsonRet.put("desc", paquete.getDesc());
			jsonRet.put("periodo", paquete.getPeriodoValidez());
			jsonRet.put("descuento", paquete.getDescuento());
			jsonRet.put("alta", paquete.getFechaAlta().format(formatoFecha));
			jsonRet.put("cantTur", paquete.getCant());
			jsonRet.put("cantTi", paquete.getTickets());
			jsonRet.put("costoTotal",costoTotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());

    }
}