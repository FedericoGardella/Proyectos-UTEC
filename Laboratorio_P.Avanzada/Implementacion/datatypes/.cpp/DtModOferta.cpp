#include "../../datatypes/.h/DtModOferta.h"

//constructores
DtModOferta::DtModOferta(){}
DtModOferta::DtModOferta(DtOfertaLite* ofl,string* agregarAsig,string* eliminarAsig) {
    this->ofl = ofl;
    this->agregarAsig = agregarAsig;
    this->eliminarAsig = eliminarAsig;
}
DtModOferta::~DtModOferta(){}


DtOfertaLite* DtModOferta::getOfertaLite() {
    return this->ofl;
}
string* DtModOferta::getAgAsign() {
    return this->agregarAsig;
}
string* DtModOferta::getElimAsign() {
    return this->eliminarAsig;
}
