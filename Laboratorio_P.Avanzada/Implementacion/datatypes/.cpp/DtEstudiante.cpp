#include "../.h/DtEstudiante.h"

DtEstudiante::DtEstudiante(){}

DtEstudiante::DtEstudiante(string* ci, string* nombre){
    this->ci = ci;
    this->nombre = nombre;
}

string* DtEstudiante::getCi(){
    return this->ci;
}

string* DtEstudiante::getNombre(){
    return this->nombre;
}

DtEstudiante::~DtEstudiante(){}