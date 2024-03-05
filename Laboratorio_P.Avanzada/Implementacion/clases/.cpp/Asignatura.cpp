#include "../.h/Asignatura.h"


using namespace std;

//constructores
Asignatura::Asignatura(string nombre, string codigo, int creditos){
    this->nombre = nombre;
    this->codigo = codigo;
    this->creditos = creditos;
}
Asignatura::~Asignatura(){}

//gets
string Asignatura::getNombre(){
    return this->nombre;
}
string Asignatura::getCodigo(){
    return this->codigo;
}
int Asignatura::getCreditos(){
    return this->creditos;
}

//sets
void Asignatura::setNombre(string nombre){
    this->nombre = nombre;
}
void Asignatura::setCodigo(string codigo){
    this->codigo = codigo;
}
void Asignatura::setCreditos(int creditos){
    this->creditos = creditos;
}