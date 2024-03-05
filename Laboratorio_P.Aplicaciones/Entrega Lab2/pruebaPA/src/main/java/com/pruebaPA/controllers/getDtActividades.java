package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pruebaPA.config.ListURL;

import datatype.DtActTurEst;
import logica.Fabrica;
import logica.ISistema;

//@WebServlet("/getdt")
public class getDtActividades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public getDtActividades() {
        super();

    }

    
//    DtActTurEst actividad = sis.seleccionarActividadDtEst(selectedItem);
//    tFNomCActividad.setText(actividad.getNombre());
//    tADescCActividad.setText(actividad.getDesc());
//    tFCiudadCActividad.setText(actividad.getCiudad());
//    tFCostoCActividad.setText(String.valueOf(actividad.getCosto()));
//    tFDuracionCActividad.setText(String.valueOf(actividad.getDuracion()));
//    tFFechaActividad.setText(actividad.getFecha().format(formatoFecha));
//    tFEstadoAct.setText(String.valueOf(actividad.getEstado()));
    
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
        String act = null;
        try {
			jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
	        act = (String) jsonRequest.get("nomAct");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ISistema sis = Fabrica.getSistema();
		DtActTurEst actividad = sis.seleccionarActividadDtEst(act);
		jsonRet.put("nombre", actividad.getNombre());
		jsonRet.put("duracion", actividad.getDuracion());
		jsonRet.put("costo", actividad.getCosto());
		jsonRet.put("ciudad", actividad.getCiudad());
		jsonRet.put("descripcion", actividad.getDesc());
		jsonRet.put("fecha", actividad.getFecha().format(formatoFecha));
		String image = sis.getImagenDeActividad(act);
		if (image != null)
			jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "acts/" + image);
		else
			jsonRet.put("imagen", ListURL.ImagesURL.getURL() + "consact.png");
		JSONArray categorias = new JSONArray();
		List<String> listaCat = sis.listarCategoriasDeActividad(act);
		listaCat.forEach(t -> {
			JSONObject i = new JSONObject();
			i.put("nombre", t);
			categorias.add(i);
		});
		jsonRet.put("categorias", categorias);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(jsonRet.toJSONString());
		
	}


}
