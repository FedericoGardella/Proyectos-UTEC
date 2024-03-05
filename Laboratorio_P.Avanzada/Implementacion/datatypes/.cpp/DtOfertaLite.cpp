#include "../.h/DtOfertaLite.h"
  

//constructores
DtOfertaLite::DtOfertaLite(){}

DtOfertaLite::DtOfertaLite(string* nroExp,string* tit, string* desc ,int* cantHs,float* salMin, float* salMax, Date* feCom ,Date* feFin ,int* cantPu){
    this->nroExpediente = nroExp;
    this->titulo = tit;
    this->descripcion = desc;
    this->cantHorasSem = cantHs;
    this->salarioMin = salMin;
    this->salarioMax= salMax;
    this->fechaCom = feCom;
    this->fechaCom = feFin;
    this->cantHorasSem = cantPu;
}

//Destructor
DtOfertaLite::~DtOfertaLite(){}

//gets
string* DtOfertaLite::getNroExpediente()
{
	return this->nroExpediente;
}

string* DtOfertaLite::getTitulo() 
{
	return this->titulo;
}
string* DtOfertaLite::getDescripcion()
{
	return this->descripcion;
}

int* DtOfertaLite::getCantHorasSem() 
{
	return this->cantHorasSem;
}
float* DtOfertaLite::getSalarioMin()
{
	return this->salarioMin;
}

float* DtOfertaLite::getSalarioMax() 
{
	return this->salarioMax;
}
Date* DtOfertaLite::getFechaCom()
{
	return this->fechaCom;
}

Date* DtOfertaLite::getFechaFin() 
{
	return this->fechaCom;
}

int* DtOfertaLite::getCantPuest() 
{
	return this->cantHorasSem;
}

