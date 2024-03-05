package com.pruebaPA.models;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import logica.Fabrica;

@WebListener
public class singletonContextListener implements ServletContextListener {
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        // Inicializa tu "singleton" o realiza tareas de configuración aquí
		
		// al ejecutar esta linea al iniciar el servidor, la primer persona en requerir de un metodo de sistema, no debera esperar que cargue
		Fabrica.getSistema();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Realiza cualquier limpieza necesaria cuando el servidor se reinicie
    	Fabrica.getSistema().cerrarConexionBD();
    }
}
