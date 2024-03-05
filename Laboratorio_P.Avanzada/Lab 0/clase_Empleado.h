#ifndef EMPLEADO_H
#define EMPLEADO_H

#include <iostream>
#include <string>
#include <cmath>
#include "clase_Paga.h"
#include "clase_Cambio.h"

using namespace std;

class Empresa;
class Empleado;

class Empleado {
    private:
        Empresa* empresa;
        string nombre;
        string ci;
        int edad;
        Paga valor_hora;
    public:
        Empleado(string nom, string cii, int edd, Paga vh){
            nombre = nom;
            ci = cii;
            edad = edd;
            valor_hora = vh;
            empresa = nullptr;
        }
        Moneda getMoneda(){
            return valor_hora.get_moneda();
        }
        float getMonto(){
            return valor_hora.get_monto();
        } 
        void setEmpresa(Empresa* empr){
            empresa = empr;
        }
        string getnombreEmpleado(){
            return this->nombre;
        }
        string getCi(){
            return this->ci;
        }
        void mostrarDatosEmpleados(){
            cout<< "- Nombre:       "<<this->nombre<<endl;
            cout<< "- Cedula:       "<<this->ci<<endl;
            cout<< "- Edad:         "<<this->edad<<endl;
            cout<< "- Valor Hora:   "<<this->valor_hora<<endl;            
        }
        virtual Paga get_sueldo_peso() = 0;
        virtual Paga get_sueldo_dolar() = 0;
};
#endif