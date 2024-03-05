#include "../.h/DtDatosEstudiante.h"


DtDatosEstudiante::DtDatosEstudiante(){}

DtDatosEstudiante::DtDatosEstudiante(DtEstudiantePlus datos,ICollection* llamados,ICollection* asigns){
    this->data = datos;
    this->llamados = llamados;
    this->asigns = asigns;
}

DtEstudiantePlus DtDatosEstudiante::getData(){
    return this->data;
}

ICollection* DtDatosEstudiante::getLlamados(){
    return this->llamados;
}

ICollection* DtDatosEstudiante::getAsigns(){
    return this->asigns;
}

DtDatosEstudiante::~DtDatosEstudiante(){}

