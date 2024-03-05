#include "../.h/DtOferta.h"


//constructores
DtOferta::DtOferta(){}
DtOferta::DtOferta(DtOfertaLite* ofl, string* conjAsig, int cantAsig){
    this->ofl = ofl;
    this->cantAsig = cantAsig;
    for(int i = 0; i < cantAsig; i++){
        this->conjAsig[i] = conjAsig[i];
    }
}

//gets
DtOfertaLite* DtOferta::getOfertaLite(){
    return this->ofl;
}

string* DtOferta::getConjAsign(){
    return this->conjAsig;
}

DtOferta::~DtOferta(){}

int DtOferta::getCantAsig(){
    return this->cantAsig;
}




