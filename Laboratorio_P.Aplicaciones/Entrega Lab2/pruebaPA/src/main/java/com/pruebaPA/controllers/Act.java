package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.Fabrica;
import logica.ISistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import com.pruebaPA.config.ListURL;
import com.pruebaPA.models.EstadosLogin;

import datatype.DtActTur;

@MultipartConfig
public class Act extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Act() {super();}

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
		HttpSession sesion = req.getSession(false);
		
        if (sesion != null && sesion.getAttribute("estado_sesion") == EstadosLogin.LOGIN && sesion.getAttribute("userType").equals("Proveedor"))
        	req.getRequestDispatcher("/WEB-INF/act/index.jsp").forward(req, res);
		else
			res.sendRedirect(req.getContextPath() + "/home");
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesion = req.getSession(false);
		
		if (sesion == null || sesion.getAttribute("estado_sesion") == null || sesion.getAttribute("estado_sesion") != EstadosLogin.LOGIN) {
			res.sendRedirect(req.getContextPath() + "/home"); // Redirige a la página de inicio de sesión o a donde desees
			return;
		}

		
		JSONObject jsonRet = new JSONObject();
        try {

        	String departamento = extractFormDataValue(req.getPart("departamento"));
        	String nombre = extractFormDataValue(req.getPart("nombre"));
            String desc = extractFormDataValue(req.getPart("desc"));
            String dur = extractFormDataValue(req.getPart("dur"));
            Integer durac = Integer.parseInt(dur);
            String costo = extractFormDataValue(req.getPart("costo"));

            String ciudad = extractFormDataValue(req.getPart("ciudad"));
            String categoriasString = extractFormDataValue(req.getPart("categorias"));
            List<String> categoriasList = Arrays.asList(categoriasString.split(","));
            
            ISistema sis = Fabrica.getSistema();
            
            String hayIm = extractFormDataValue(req.getPart("hayImage"));
            
            Float cost = null;
            try {
                cost = Float.parseFloat(costo);
            } catch (NumberFormatException e) {}
            
            HttpSession objSesion = req.getSession();
            String nick = (String) objSesion.getAttribute("userNick");
            
            DtActTur act = new DtActTur(nombre,desc,durac,cost,ciudad, LocalDate.now());

    		sis.altaActividad(nick, departamento, act, categoriasList);
    		

    		//Me fijo si me subieron una imagen:
    		
			if(hayIm.equals("true")) {
				Part filePart = req.getPart("image");
				String extensionFile = getFileExtension(filePart);
				InputStream contentFile = filePart.getInputStream();
				
	    		try {	
					
					Path destinoAbsoluto = Paths.get(getServletContext().getRealPath("/media/img/acts/"));
					
					if (!Files.exists(destinoAbsoluto)) {
					    Files.createDirectories(destinoAbsoluto); // Crea el directorio y cualquier directorio intermedio si no existen
					}
					
					
					Files.copy(contentFile, destinoAbsoluto.resolve(nombre + "." + extensionFile), StandardCopyOption.REPLACE_EXISTING);
					sis.guardarImagenAct(departamento, nombre, nombre + "." + extensionFile);
					
					req.getSession(false).setAttribute("urlImgAct", ListURL.ImagesURL.getURL()+"acts/"+ nombre + "." + extensionFile);
	    		
	    		} catch (Exception e) {
	    			jsonRet.put("successEditImg", false);
	    		}
				
			}
        
            jsonRet.put("success", true);
            jsonRet.put("message", "Registrado exitosamente");
            jsonRet.put("redirect", req.getContextPath() + "/home");
            
		} catch (Exception e) {
			jsonRet.put("success", false);
			jsonRet.put("message", e.getMessage());			
		} finally {
			req.setCharacterEncoding("UTF-8");
			res.setContentType("application/json");
			res.getWriter().write(jsonRet.toJSONString());
		}	
	}
}
