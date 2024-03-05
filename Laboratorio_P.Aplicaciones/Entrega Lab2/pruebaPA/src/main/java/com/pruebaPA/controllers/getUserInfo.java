package com.pruebaPA.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.ISistema;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pruebaPA.config.ListURL;
import com.pruebaPA.exceptions.UserExceptions.UserNotFound;
import com.pruebaPA.models.EstadosLogin;
import com.pruebaPA.models.UserModel;

import datatype.DtActTurEst;
import datatype.DtSalidaTuristica;
import datatype.DtUsuario;

public class getUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public getUserInfo() { super(); }
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		JSONObject jsonRet = new JSONObject();
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		String nickname = req.getParameter("nick");
		ISistema sis = Fabrica.getSistema();
				
		if (nickname == null || nickname.equals("") || !sis.checkUserNickExist(nickname)) {
			jsonRet.put("success", false);	
			jsonRet.put("message", "Usuario no existe");
			res.getWriter().write(jsonRet.toJSONString());
			return;
		}
		HttpSession sesion = req.getSession(false);
		DtUsuario info = null;
		if (sesion != null && sesion.getAttribute("estado_sesion") != null && sesion.getAttribute("estado_sesion") == EstadosLogin.LOGIN && sesion.getAttribute("userNick").equals(nickname)) {
			info = sis.getDtUserWebPropias(nickname);
		} else {
			info = sis.getDtUserWeb(nickname);			
		}
		
		
		jsonRet.put("success", true);	
		jsonRet.put("message", "Usuario si existe");
		
		jsonRet.put("nick", info.getNick());
		jsonRet.put("email", info.getEmail());
		jsonRet.put("nombre", info.getNom());
		jsonRet.put("apellido", info.getApe());
		jsonRet.put("fechaNac", info.getFechaNac().toString());
		jsonRet.put("nacion", info.getNacion());
		jsonRet.put("link", info.getLink());
		jsonRet.put("desc", info.getDesc());
		String urlImage;
		try {
			urlImage = UserModel.getURLImageUser(nickname);
			if (urlImage == null)
				jsonRet.put("urlImgProfile", ListURL.ImagesURL.getURL()+"profile_pic.webp");
			else
				jsonRet.put("urlImgProfile", ListURL.ImagesURL.getURL()+"users/"+urlImage);
		} catch (UserNotFound e) {
			jsonRet.put("urlImgProfile", ListURL.ImagesURL.getURL()+"profile_pic.webp");
		}

		List<String> actividades = info.getNombreActividades();
		if (actividades != null) {
			JSONArray ar = new JSONArray();
			actividades.forEach(t -> {
				DtActTurEst data = sis.seleccionarActividadDtEst(t);
				JSONObject actInfo = new JSONObject();
				actInfo.put("nombre", data.getNombre());
				actInfo.put("estado", data.getEstado().toString());
				ar.add(actInfo);
			});
			jsonRet.put("actividades", ar);
		}
		JSONArray sa = new JSONArray();
		info.getNombreSalidas().forEach(t -> {
			DtSalidaTuristica dt = sis.getDtSalida(t);
			JSONObject salidaInfo = new JSONObject();
			salidaInfo.put("nombre", dt.getNombreSalida());
			salidaInfo.put("lugar", dt.getLugarSalida());
			salidaInfo.put("cantMaxTuristas", dt.getCantMaxTur());
			salidaInfo.put("fechaAlta", dt.getFechaAlta().toString());
			salidaInfo.put("fechaSalida", dt.getFechaSalida().toString());
			salidaInfo.put("horaSalida", dt.getHoraSalida().toString());
			sa.add(salidaInfo);
		});
		jsonRet.put("salidas", sa);
			
		
		
		
				
		res.getWriter().write(jsonRet.toJSONString());
	}
}