
#include "../.h/DtEmpresa.h"

//constructores
DtEmpresa::DtEmpresa(){}

DtEmpresa::DtEmpresa(string rut, string nom){
    this->rut = rut;
    this->nombre = nom;
}

//Destructor
DtEmpresa::~DtEmpresa(){}

//gets
string DtEmpresa::getRut()
{
	return this-> rut;
}

string DtEmpresa::getNombre() {
	return this-> nombre;
}

//sets
void DtEmpresa::setRut(string rut) {
	this->rut = rut;
}

void DtEmpresa::setNombre(string nom) {
	this->nombre = nom;
}