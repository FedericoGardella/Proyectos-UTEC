#include "../.h/DtSucursal.h"


//constructores
DtSucursal::DtSucursal(){}
DtSucursal::DtSucursal(string tel, string nom, string dir, DtEmpresa emp){
    this->telefono = tel;
    this->nombre = nom;
    this->direccion = dir;
    this->miEmp = emp;
}
DtSucursal::~DtSucursal(){}

//gets
string DtSucursal::getTel(){
    return this->telefono;
}
string DtSucursal::getNombre(){
    return this->nombre;
}   
string DtSucursal::getDireccion(){
    return this->direccion;
}
DtEmpresa DtSucursal::getEmp(){
    return this->miEmp;
}
//sets
void DtSucursal::setTel(string tel){
    this->telefono = tel;
}
void DtSucursal::setNombre(string nom){
    this->nombre = nom;
}
void DtSucursal::setDireccion(string dir){
    this->direccion = dir;
}
void DtSucursal::setEmp(DtEmpresa emp){
    this->miEmp = emp;
}





