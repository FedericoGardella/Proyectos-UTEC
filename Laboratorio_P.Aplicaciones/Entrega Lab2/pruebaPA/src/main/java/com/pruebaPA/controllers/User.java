package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.pruebaPA.exceptions.UserExceptions.UserExist;
import com.pruebaPA.exceptions.UserExceptions.UserInfoError;

import com.pruebaPA.models.EstadosLogin;
import com.pruebaPA.models.UserModel;

import datatype.DtUsuario;

//@WebServlet("/user")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public User() {super();}


	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession sesion = req.getSession(false);
		if (sesion == null || sesion.getAttribute("estado_sesion") == null || sesion.getAttribute("estado_sesion") != EstadosLogin.LOGIN)
			req.getRequestDispatcher("/WEB-INF/user/index.jsp").forward(req, res);
		 else
			res.sendRedirect(req.getContextPath() + "/home");
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        // Analizar el JSON del cuerpo de la solicitud
        JSONParser parser = new JSONParser();
        JSONObject jsonRequest;
        JSONObject jsonRet = new JSONObject();
        try {
            jsonRequest = (JSONObject) parser.parse(stringBuilder.toString());
            String nick = (String) jsonRequest.get("nickName");
            String email = (String) jsonRequest.get("email");
            String nombreUser = (String) jsonRequest.get("nombre");
            String apellido = (String) jsonRequest.get("apellido");
            String fechaNac = (String) jsonRequest.get("fechaNac");
            String userType = (String) jsonRequest.get("userType");
            String nacionalidad = (String) jsonRequest.get("nacionalidad");
            String link = (String) jsonRequest.get("link");
            String desc = (String) jsonRequest.get("desc");
            String password = (String) jsonRequest.get("password");
            String passwordConfirmation = (String) jsonRequest.get("passwordConfirmation");
            
    		LocalDate nuevaFecha = LocalDate.parse(fechaNac, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            
            DtUsuario dtUser = new DtUsuario(nick, nombreUser, apellido, email, nuevaFecha, desc, link, nacionalidad);
            
            boolean esProveedor;
            
            if (userType.equals("Proveedor"))
            	esProveedor = true;
            else if (userType.equals("Turista"))
            	esProveedor = false;
            else throw new UserInfoError("El usuario debe ser proveedor o turista");

            if (password.length() < 8)
            	throw new UserInfoError("La contraseña debe tener al menos 8 caracteres");
            
            if (!password.equals(passwordConfirmation))
            	throw new UserInfoError("La contraseña y su verificacion no coinciden");
                    
            UserModel.registerUser(dtUser, esProveedor, password);
            
			jsonRet.put("success", true);
			jsonRet.put("message", "Registrado exitosamente");
			jsonRet.put("redirect", req.getContextPath() + "/home");

        } catch (org.json.simple.parser.ParseException e) {
        	jsonRet.put("success", false);
        	jsonRet.put("message", "Error al parsear");
//          Manejar errores de análisis del JSON
        } catch (UserExist u) {
			jsonRet.put("success", false);
			jsonRet.put("message", u.getMessage());
//			Devolver de alguna manera al /login diciendo que el user no existe
		} catch(DateTimeParseException e) {
			jsonRet.put("success", false);
			jsonRet.put("message", "Formato de fecha de nacimiento invalida");
//			Maneja cuando el formato de fecha falla
		} catch(UserInfoError u) {
			jsonRet.put("success", false);
			jsonRet.put("message", u.getMessage());
//			Maneja genericamente los errores respecto a los campos de registro
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
