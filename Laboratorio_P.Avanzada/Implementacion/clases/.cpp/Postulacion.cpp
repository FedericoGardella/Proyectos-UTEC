#include "../.h/Postulacion.h"


using namespace std;

//constructores
Postulacion::Postulacion(Date fecha, ICollection* entrevistas, OfertaLaboral* oferta, Estudiante* estudiante){
    this->fecha = fecha;
    this->oferta = oferta;
    this->estudiante = estudiante;
    if (entrevistas != NULL)
        this->entrevistas = entrevistas;
    else
        this->entrevistas = new List;
}

//gets
Date Postulacion::getFecha(){
    return this->fecha;
}
ICollection* Postulacion::getEntrevistas(){
    return this->entrevistas;
}
OfertaLaboral* Postulacion::getOferta(){
    return this->oferta;
}
Estudiante* Postulacion::getEstudiante(){
    return this->estudiante;
}

//sets
void Postulacion::setEntrevistas(ICollection* entrevistas) {
    this->entrevistas = entrevistas;
}

bool Postulacion::esOferta(string numeroOferta) {
    string cod;
    cod = this->oferta->getNumero();
    return (cod == numeroOferta);
}

void Postulacion::nuevaEntrevista(Date* fech){
    this->entrevistas->add(fech);
}

void Postulacion::desvincularEstudiantePostulacion(string codigo){
	this->estudiante->desvincularEstudiantePostulacion(codigo);
}

Postulacion::~Postulacion(){}



void Postulacion::vincularEstudiantePost(string codigo) {

    this->estudiante->vincularEstudiantePost(this, codigo);
}


void Postulacion::getDTLlamados() {

    this->oferta->getDTLlamados();
}