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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.json.simple.JSONObject;

import com.pruebaPA.config.ListURL;
import com.pruebaPA.exceptions.UserExceptions.UserInfoError;
import com.pruebaPA.exceptions.UserExceptions.UserNotFound;
import com.pruebaPA.models.EstadosLogin;
import com.pruebaPA.models.UserModel;

import datatype.DtModUser;
import datatype.DtUsuario;

@MultipartConfig
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Profile() { super(); }
    
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
		
        if (sesion != null && sesion.getAttribute("estado_sesion") == EstadosLogin.LOGIN)
        	req.getRequestDispatcher("/WEB-INF/user/profile.jsp").forward(req, res);
		else
			res.sendRedirect(req.getContextPath() + "/home"); // Redirige a la página de inicio de sesión o a donde desees
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesion = req.getSession(false);
				
		if (sesion == null || sesion.getAttribute("estado_sesion") == null || sesion.getAttribute("estado_sesion") != EstadosLogin.LOGIN) {
			res.sendRedirect(req.getContextPath() + "/home"); // Redirige a la página de inicio de sesión o a donde desees
			return;
		}

		
		
		JSONObject jsonRet = new JSONObject();
		try {
			String nick = (String)sesion.getAttribute("userNick");
			String nombreUser = extractFormDataValue(req.getPart("nombre"));
			String apellido = extractFormDataValue(req.getPart("apellido"));
			String fechaNac = extractFormDataValue(req.getPart("fechaNac"));
			String userType = extractFormDataValue(req.getPart("userType"));
			String link = null;
			String desc = null;
			String nacionalidad = null;

			
			if (userType.equals("Proveedor")) {				
				link = extractFormDataValue(req.getPart("enlace"));
				desc = extractFormDataValue(req.getPart("desc"));
			}
			if (userType.equals("Turista")) {
				nacionalidad = extractFormDataValue(req.getPart("nacionalidad"));				
			}
			LocalDate nuevaFecha = LocalDate.parse(fechaNac, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			DtModUser newData;
    		if (userType.equals("Proveedor"))
    			newData = new DtModUser(nombreUser, apellido, nuevaFecha, desc, link);    			
    		else if (userType.equals("Turista"))
    			newData = new DtModUser(nombreUser, apellido, nuevaFecha, nacionalidad);
    		else  throw new UserInfoError("El usuario debe ser proveedor o turista");
    		
    		
    		try {
    			DtUsuario newInfo = UserModel.editUser(nick, newData);
    			sesion.setAttribute("dtUser", newInfo);
    			jsonRet.put("successEditText", true);
    		} catch (UserNotFound e) {
    			jsonRet.put("successEditText", false);
    		} catch (Exception e) {
    			jsonRet.put("successEditText", false);
    		}
			Part filePart = req.getPart("image");
			String extensionFile = getFileExtension(filePart);
			if(!extensionFile.equals("")) {	
	    		try {
	    			InputStream contentFile = filePart.getInputStream();
					String urlImage = UserModel.editUserImage(nick, getServletContext().getRealPath("/media/img/users/"), extensionFile, contentFile);
					if (urlImage == null) {
						req.getSession(false).setAttribute("urlImgProfile", ListURL.ImagesURL.getURL()+"profile_pic.webp");	
					} else {
						req.getSession(false).setAttribute("urlImgProfile", ListURL.ImagesURL.getURL()+"users/"+urlImage);
					}
					jsonRet.put("urlImage", ListURL.ImagesURL.getURL() + "users/" + urlImage);
	    			jsonRet.put("successEditImg", true);
	    		} catch (UserNotFound e) {
	    			jsonRet.put("successEditImg", false);
	    		} catch (Exception e) {
	    			jsonRet.put("successEditImg", false);
	    		}
			} else {
				
				jsonRet.put("urlImage", sesion.getAttribute("urlImgProfile"));
    			jsonRet.put("successEditImg", true);
			}
    		
			

			jsonRet.put("success", true);
			jsonRet.put("message", "Editado correctamente");
		} catch (DateTimeParseException e) {
			jsonRet.put("success", false);
			jsonRet.put("message", "Formato de fecha de nacimiento invalida");
		} catch (UserInfoError e) {
			jsonRet.put("success", false);
			jsonRet.put("message", e.getMessage());
		} finally {
			req.setCharacterEncoding("UTF-8");
			res.setContentType("application/json");
			res.getWriter().write(jsonRet.toJSONString());
		}	
	}

}
