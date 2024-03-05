package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;

import com.pruebaPA.models.EstadosLogin;

import logica.Fabrica;
import logica.ISistema;




@MultipartConfig
public class Comprapaq extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Comprapaq() {super();}

    private String extractFormDataValue(Part part) throws IOException {
        try (InputStream is = part.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr)) {
            return reader.readLine();
        }
    }
    
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "unknown";
    }
    
    private String getFileExtension(Part part) {
        String fileName = getFileName(part);
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1).toLowerCase();
        }
        return "";
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/comprapaq/index.jsp").forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		HttpSession sesion = req.getSession(false);
		
		if (sesion == null || sesion.getAttribute("estado_sesion") == null || sesion.getAttribute("estado_sesion") != EstadosLogin.LOGIN) {
			res.sendRedirect(req.getContextPath() + "/home"); // Redirige a la página de inicio de sesión o a donde desees
			return;
		}
		
		JSONObject jsonRet = new JSONObject();
		try {
			ISistema sis = Fabrica.getSistema();
	        HttpSession objSesion = req.getSession();
	        String nombreTur = (String) objSesion.getAttribute("userNick");
	        
	        Part nombrePaqPart = req.getPart("nombrePaq");
	        String nombrePaq;
	        if (nombrePaqPart != null) {
	            nombrePaq = extractFormDataValue(nombrePaqPart);
	            // Resto del código...
	        } else {
	            return;
	        }
			
			
			try {
				sis.turistaCompraPaquete(nombreTur, nombrePaq);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} finally {
			res.sendRedirect("http://localhost:8080/pruebaPA/comprapaq");
			req.setCharacterEncoding("UTF-8");
			res.setContentType("application/json");
			res.getWriter().write(jsonRet.toJSONString());
		}

		
	}
}