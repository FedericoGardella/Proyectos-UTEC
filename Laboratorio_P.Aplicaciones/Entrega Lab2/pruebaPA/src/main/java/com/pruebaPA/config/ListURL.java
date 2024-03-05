package com.pruebaPA.config;

public enum ListURL {
	HomeURL("/pruebaPA/home"),
	LoginURL("/pruebaPA/user"),
	RegisterURL("/pruebaPA/user?r=true"),
	SalidaURL("/pruebaPA/salida"),
	PaqueteURL("/pruebaPA/paquete"),
	ActURL("/pruebaPA/act"),
	ListUsers("/pruebaPA/listarUsuarios"),
	ComprapaqURL("/pruebaPA/comprapaq"),
	ConsActURL("/pruebaPA/consact"),
	AltaSalidaURL("/pruebaPA/altasalida"),
	BootstrapURL("/pruebaPA/media/styles/bootstrap.min.css"),
	JSFilesURL("/pruebaPA/media/js/"),
	LogoutURL("/pruebaPA/logout"),
	ProfileURL("/pruebaPA/profile"),
	headerStylesURL("/pruebaPA/media/styles/header.css"),
	headerJSURL("/pruebaPA/media/js/header.js"),
	ImagesURL("/pruebaPA/media/img/");
	
	
	private final String URL;
	private ListURL(String URL) {
        this.URL = URL;
    }
    public String getURL() {
        return URL;
    }
}
