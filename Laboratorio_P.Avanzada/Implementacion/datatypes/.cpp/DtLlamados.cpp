#include "../.h/DtLlamados.h"


//constructores
DtLlamados::DtLlamados() {} 
DtLlamados::DtLlamados(string numOferta,string tituloOferta, DtSeccion miSec) {
    this->numOferta = numOferta;
    this->tituloOferta = tituloOferta;
    this->miSec = miSec;
}
DtLlamados::~DtLlamados(){}
//getters
string DtLlamados::getNum() {
    return this->numOferta;
}
string DtLlamados::getTitulo() {
    return this->tituloOferta;
}
DtSeccion DtLlamados::getSec() {
    return this->miSec;
}
//setters
void DtLlamados::setNum(string numOferta) {
    this->numOferta = numOferta;
}
void DtLlamados::setTitulo(string tituloOferta) {
    this->tituloOferta = tituloOferta;
}
void DtLlamados::setSec(DtSeccion miSec) {
    this->miSec = miSec;
}
