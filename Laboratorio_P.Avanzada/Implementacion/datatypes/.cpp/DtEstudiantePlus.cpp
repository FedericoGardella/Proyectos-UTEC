#include "../.h/DtEstudiantePlus.h"

DtEstudiantePlus::DtEstudiantePlus(){}

DtEstudiantePlus::DtEstudiantePlus(Date* dia, string* tel, int* cred, DtEstudiante* datos){
    this->fecha_nac = dia;
    this->telefono = tel;
    this->creditos_obt = cred;
    this->data = datos;
}

Date* DtEstudiantePlus::getFechaNac(){
    return this->fecha_nac;
}

string* DtEstudiantePlus::getTel(){
    return this->telefono;
}

int* DtEstudiantePlus::getCred(){
    return this->creditos_obt;
}

DtEstudiante* DtEstudiantePlus::getData(){
    return this->data;
}

DtEstudiantePlus::~DtEstudiantePlus(){}

