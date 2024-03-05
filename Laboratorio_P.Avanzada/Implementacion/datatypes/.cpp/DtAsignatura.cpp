#include "../.h/DtAsignatura.h"


//constructores
DtAsignatura::DtAsignatura(){}
DtAsignatura::DtAsignatura(string codigo, string nombre, int creditos) {
    this->codigo = codigo;
    this->nombre = nombre;
    this->creditos = creditos;
}
DtAsignatura::~DtAsignatura(){};

//gets
string DtAsignatura::getCodigo() {
    return this->codigo;
}
string DtAsignatura::getNombre() {
    return this->nombre;
}
int DtAsignatura::getCreditos() {
    return this->creditos;
}

