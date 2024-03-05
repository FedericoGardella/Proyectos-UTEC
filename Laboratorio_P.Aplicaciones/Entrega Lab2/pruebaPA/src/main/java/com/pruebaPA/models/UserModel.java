package com.pruebaPA.models;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.pruebaPA.exceptions.UserExceptions.*;

import datatype.DtModUser;
import datatype.DtUsuario;
import logica.Fabrica;
import logica.ISistema;

public class UserModel {
	public static DtUsuario canLogin(String nick, String password) throws UserNotFound{
		ISistema sis = Fabrica.getSistema();
		boolean nickExist = sis.checkUserNickExist(nick);
		boolean emailExist = sis.checkUserEmailExist(nick);
		if (!nickExist && !emailExist) {
			if (!nickExist) 
				throw new UserNotFound("El nick no existe");
			if (!emailExist) 
				throw new UserNotFound("El email no existe");
		}
		if (nickExist && sis.checkValidUser(nick, password)) {
			return sis.obtenerDataUsuario(nick);
		}
		if (emailExist && sis.checkValidUser(nick, password)) {	
			return sis.obtenerDataUsuarioConEmail(nick);
		}
		return null;
	}
	
	public static void registerUser(DtUsuario data, boolean esProveedor, String password) throws UserExist, java.lang.Exception {
		ISistema sis = Fabrica.getSistema();
		if (sis.checkUserNickExist(data.getNick()))
			throw new UserExist("El nick ya esta en uso");
		
		if (sis.checkUserEmailExist(data.getEmail()))
			throw new UserExist("El email ya esta en uso");
		sis.altaUsuario(esProveedor, data, password);
	}
	
	public static DtUsuario editUser(String nick, DtModUser newData) throws UserNotFound, Exception{
		ISistema sis = Fabrica.getSistema();
		if (!sis.checkUserNickExist(nick))
			throw new UserNotFound("El nick no existe");
		
		sis.modificarDatosWeb(nick, newData);
		return sis.obtenerDataUsuario(nick);
	}
	public static String editUserImage(String nick, String ruta, String extension, InputStream contentFile) throws UserNotFound, Exception{
		ISistema sis = Fabrica.getSistema();
		if (!sis.checkUserNickExist(nick))
			throw new UserNotFound("El nick no existe");
		
		
		Path destinoAbsoluto = Paths.get(ruta);
		
		if (!Files.exists(destinoAbsoluto)) {
		    Files.createDirectories(destinoAbsoluto); // Crea el directorio y cualquier directorio intermedio si no existen
		}
		
		
		Files.copy(contentFile, destinoAbsoluto.resolve(nick + "." + extension), StandardCopyOption.REPLACE_EXISTING);
		sis.modificarImagenUser(nick, nick + "." + extension);
		return nick + "." + extension;
		
	}
	public static String getURLImageUser(String nick) throws UserNotFound{
		ISistema sis = Fabrica.getSistema();
		if (!sis.checkUserNickExist(nick))
			throw new UserNotFound("El nick no existe");
		return sis.getURLImageUser(nick);
	}
}
