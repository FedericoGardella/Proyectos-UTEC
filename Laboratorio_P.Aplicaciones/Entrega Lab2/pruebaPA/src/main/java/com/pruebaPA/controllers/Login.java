package com.pruebaPA.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import com.pruebaPA.config.ListURL;
import com.pruebaPA.exceptions.UserExceptions.UserNotFound;
import com.pruebaPA.models.EstadosLogin;
import com.pruebaPA.models.UserModel;

import datatype.DtUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//@WebServlet("/login")
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public Login() { super(); }
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
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
            String nick = (String) jsonRequest.get("nickNameLogin");
            String password = (String) jsonRequest.get("passwordLogin");
            DtUsuario userInfo = UserModel.canLogin(nick, password);
            if (userInfo != null) {	
				HttpSession objSesion = req.getSession();
				String tipoUser = userInfo.getNacion() == null ? "Proveedor" : "Turista";
				objSesion.setAttribute("userNick", userInfo.getNick());
				objSesion.setAttribute("dtUser", userInfo);
				String urlImage = UserModel.getURLImageUser(userInfo.getNick());
				if (urlImage == null)
					objSesion.setAttribute("urlImgProfile", ListURL.ImagesURL.getURL()+"profile_pic.webp");
				else
					objSesion.setAttribute("urlImgProfile", ListURL.ImagesURL.getURL()+"users/"+urlImage);
				objSesion.setAttribute("userType", tipoUser);
				objSesion.setAttribute("estado_sesion", EstadosLogin.LOGIN);
				jsonRet.put("success", true);
				jsonRet.put("message", "Login Correcto");
				jsonRet.put("redirect", req.getContextPath() + "/home");
			} else {
//				Devolver de alguna manera al /login diciendo que la password es incorrecta
				jsonRet.put("success", false);
				jsonRet.put("message", "Password Incorrecta");
			}
        } catch (org.json.simple.parser.ParseException e) {
        	jsonRet.put("success", false);
        	jsonRet.put("message", "Error al parsear");
//          Manejar errores de análisis del JSON
            e.printStackTrace();
        } catch (UserNotFound u) {
			jsonRet.put("success", false);
			jsonRet.put("message", "Nick no existe");
//			Devolver de alguna manera al /login diciendo que el user no existe
		} finally {
			req.setCharacterEncoding("UTF-8");
			res.setContentType("application/json");
			res.getWriter().write(jsonRet.toJSONString());
		}			
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/home");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
}
