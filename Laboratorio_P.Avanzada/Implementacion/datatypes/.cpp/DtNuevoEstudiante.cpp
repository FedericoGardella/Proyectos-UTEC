#include "../.h/DtNuevoEstudiante.h"

DtNuevoEstudiante::DtNuevoEstudiante(){}

DtNuevoEstudiante::DtNuevoEstudiante(DtEstudiantePlus* datos,string* agrA,string* elimA,string* agrC,string* elimC){
    this->data = datos;
    this->agregarAsig = agrA;
    this->eliminarAsig = elimA;
    this->agregarCarr = agrC;
    this->eliminarCarr = elimC;
}

DtEstudiantePlus* DtNuevoEstudiante::getData(){
    return this->data;
}

string* DtNuevoEstudiante::getAgAsign(){
    return this->agregarAsig;
}

string* DtNuevoEstudiante::getElimAsign(){
    return this->eliminarAsig;
}

string* DtNuevoEstudiante::getAgCarr(){
    return this->agregarCarr;
}

string* DtNuevoEstudiante::getElimCarr(){
    return this->eliminarCarr;
}

DtNuevoEstudiante::~DtNuevoEstudiante(){}