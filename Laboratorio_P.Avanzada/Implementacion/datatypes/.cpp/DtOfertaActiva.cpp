#include "../.h/DtOfertaActiva.h"

//constructores
DtOfertaActiva::DtOfertaActiva(){}
DtOfertaActiva::DtOfertaActiva(string nombre, string empresa, string ubicacion, int cantInscriptos, float salarioMin, float salarioMax, int cantPlazas) {
    this->nombre = nombre;
    this->empresa = empresa;
    this->ubicacion = ubicacion;
    this->cantInscriptos = cantInscriptos;
    this->salarioMin = salarioMin;
    this->salarioMax = salarioMax;
    this->cantPlazas = cantPlazas;
}
DtOfertaActiva::~DtOfertaActiva(){};

//gets
string DtOfertaActiva::getNombre() {
    return this->nombre;
}
string DtOfertaActiva::getEmpresa() {
    return this->empresa;
}
string DtOfertaActiva::getUbicacion() {
    return this->ubicacion;
}
int DtOfertaActiva::getCantInscriptos() {
    return this->cantInscriptos;
}
float DtOfertaActiva::getSalarioMin() {
    return this->salarioMin;
}
float DtOfertaActiva::getSalarioMax() {
    return this->salarioMax;
}
int DtOfertaActiva::getCantPlazas() {
    return this->cantPlazas;
}


