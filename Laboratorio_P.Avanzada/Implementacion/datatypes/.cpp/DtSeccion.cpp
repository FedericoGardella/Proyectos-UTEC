#include "../.h/DtSeccion.h"


//constructores
DtSeccion::DtSeccion(){}
DtSeccion::DtSeccion(string nombre,string telefono, DtSucursal miSuc) {
    this->nombre = nombre;
    this->telefono = telefono;
    this->miSuc = miSuc;
}
DtSeccion::~DtSeccion(){}
//getters
string DtSeccion::getNombre() {
    return this->nombre;
}
string DtSeccion::getTel() {
    return this->telefono;
}
DtSucursal DtSeccion::getSuc() {
    return this->miSuc;
}
