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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.pruebaPA.models.EstadosLogin;

import datatype.DtSalidaTuristica;

public class InscribirseSalida extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InscribirseSalida() {  super(); }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesion = req.getSession(false);
		JSONObject jsonRet = new JSONObject();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		if (sesion == null || sesion.getAttribute("estado_sesion") != EstadosLogin.LOGIN) {			
			jsonRet.put("success", false);
        	jsonRet.put("message", "Debe iniciar sesion");
        	res.getWriter().write(jsonRet.toJSONString());
        	return;
		}
		
		
		String esDep = req.getParameter("esDep");
		String nombreDoC = req.getParameter("DoC");
		String nombreActividad = req.getParameter("act");
		String nombreSalida = req.getParameter("sal");
		if (esDep == null || nombreDoC == null || nombreActividad == null || nombreSalida == null) {
			jsonRet.put("success", false);
        	jsonRet.put("message", "No se recibieron todos los parametros");
        	res.getWriter().write(jsonRet.toJSONString());
        	return;
		}
		
		if (sesion.getAttribute("userType").equals("Proveedor")) {
			jsonRet.put("success", false);
        	jsonRet.put("message", "Debe iniciar sesion con una cuenta del tipo Turista");
        	res.getWriter().write(jsonRet.toJSONString());
        	return;
		}
		

		
		ISistema sis = Fabrica.getSistema();
		
		DtSalidaTuristica dt = sis.getDtSalida(nombreSalida);
		
		if (dt == null) {
			jsonRet.put("success", false);
        	jsonRet.put("message", "La salida no existe");
        	res.getWriter().write(jsonRet.toJSONString());
        	return;
		}
		
		sesion.setAttribute("esDep", esDep);
		sesion.setAttribute("nombreDoC", nombreDoC);
		sesion.setAttribute("nombreActividad", nombreActividad);
		sesion.setAttribute("dataSalida", dt);
			
		jsonRet.put("success", true);
    	jsonRet.put("redirect",  req.getContextPath() + "/inscribirse");
    	res.getWriter().write(jsonRet.toJSONString());
		
	}
 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesion = req.getSession(false);
		
		JSONObject jsonRet = new JSONObject();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		if (sesion == null || sesion.getAttribute("estado_sesion") != EstadosLogin.LOGIN) {			
			jsonRet.put("success", false);
        	jsonRet.put("message", "Debe iniciar sesion");
        	res.getWriter().write(jsonRet.toJSONString());
        	return;
		}
		if (((String)sesion.getAttribute("userType")).equals("Proveedor")) {
			jsonRet.put("success", false);
			jsonRet.put("message", "Debe iniciar con una cuenta del tipo Turista");
			res.getWriter().write(jsonRet.toJSONString());
			return;
		}
		
		BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        // Analizar el JSON del cuerpo de la solicitud
        JSONParser parser = new JSONParser();
        JSONObject jsonRequest;
        try {
        	jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
        	String esDep = (String)jsonRequest.get("esDep");
        	String DoC = (String) jsonRequest.get("DoC");
        	String Act = (String) jsonRequest.get("act");
        	String Sal = (String) jsonRequest.get("sal");
        	String cant = (String) jsonRequest.get("cant");
        	Integer canti = Integer.parseInt(cant);
        	ISistema sis = Fabrica.getSistema();
        	
        	if (esDep.equals("true"))
        		sis.altaInscripcionWebDep(DoC, Act, Sal, (String)sesion.getAttribute("userNick"), canti);
        	else
        		sis.altaInscripcionWebCat(DoC, Act, Sal, (String)sesion.getAttribute("userNick"), canti);
        	
        	jsonRet.put("success", true);
        	jsonRet.put("message", (String)sesion.getAttribute("userNick") + " inscripto correctamente en " + Sal);
        } catch (org.json.simple.parser.ParseException e) {
        	jsonRet.put("success", false);
        	jsonRet.put("message", "Error al parsear");
        } catch (Exception e) {
        	jsonRet.put("success", false);
        	jsonRet.put("message", e.getMessage());
		} finally {
			res.getWriter().write(jsonRet.toJSONString());
		}	
	}

}
