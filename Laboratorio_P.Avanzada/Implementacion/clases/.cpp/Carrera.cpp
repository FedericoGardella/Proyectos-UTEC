#include "../.h/Carrera.h"

using namespace std;

//constructores
Carrera::Carrera(string codigo, string nombre){
    this->codigo = codigo;
    this->nombre = nombre;
}
Carrera::~Carrera(){}

//gets
string Carrera::getCodigo(){
    return this->codigo;
}
string Carrera::getNombre(){
    return this->nombre;
}

//sets
void Carrera::setCodigo(string codigo){
    this->codigo = codigo;
}
void Carrera::setNombre(string nombre){
    this->nombre = nombre;
}
