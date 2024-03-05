package com.pruebaPA.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import com.pruebaPA.config.ListURL;
import com.pruebaPA.models.EstadosLogin;

import datatype.DtSalidaTuristica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.Fabrica;
import logica.ISistema;


@MultipartConfig
public class Salida extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Salida() {super();}
    
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

    
	private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/salida/index.jsp").forward(req, res);
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
        	String actividad = extractFormDataValue(req.getPart("actividad"));
        	String nombre = extractFormDataValue(req.getPart("nombre"));
            String hora = extractFormDataValue(req.getPart("hora"));
            String lugar = extractFormDataValue(req.getPart("lugar"));
            String fecha = extractFormDataValue(req.getPart("fecha"));
            String cantidad = extractFormDataValue(req.getPart("cantidad"));
            Integer cant = Integer.parseInt(cantidad);
            
            ISistema sis = Fabrica.getSistema();
            
            String hayIm = extractFormDataValue(req.getPart("hayImage"));
						
            
    		LocalDate nuevaFecha = null;
            try {
            	nuevaFecha = LocalDate.parse(fecha, formatoFecha);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            LocalTime nuevaHora = null;
            try {
            	nuevaHora = LocalTime.parse(hora, formatoHora);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Formato de hora inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DtSalidaTuristica sali = new DtSalidaTuristica(nombre,nuevaFecha,nuevaHora,lugar,cant, LocalDate.now());

    		sis.confirmarAltaSalTur(actividad, sali);
    		

    		//Me fijo si me subieron una imagen:
    		
			if(hayIm.equals("true")) {
				Part filePart = req.getPart("image");
				String extensionFile = getFileExtension(filePart);
				InputStream contentFile = filePart.getInputStream();
				
	    		try {	
					
					Path destinoAbsoluto = Paths.get(getServletContext().getRealPath("/media/img/sals/"));
					
					if (!Files.exists(destinoAbsoluto)) {
					    Files.createDirectories(destinoAbsoluto); // Crea el directorio y cualquier directorio intermedio si no existen
					}
					
					
					Files.copy(contentFile, destinoAbsoluto.resolve(nombre + "." + extensionFile), StandardCopyOption.REPLACE_EXISTING);
					sis.guardarImagenSalida(departamento, actividad, nombre, nombre + "." + extensionFile);
					
					req.getSession(false).setAttribute("urlImgSalida", ListURL.ImagesURL.getURL()+"sals/"+ nombre + "." + extensionFile);
	    		
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
