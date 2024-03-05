#ifndef EMPRESA_H
#define EMPRESA_H

#include <iostream>
#include <string>
#include <cmath>
#include "clase_Paga.h"
#include "clase_Fijo_Jornalero.h"
#include "clase_Empresa.h"
#include "clase_Cambio.h"

using namespace std;

#define MAX_EMPRESAS 100
#define MAX_EMPLEADO 50


class Empresa;
class Empleado;


class Empresa {
    private:
        Empleado* empleados[MAX_EMPLEADO];
        string nombre;
        string nombre_legal;
        long long rut;
        int num_Emp;
    public:
        Empresa(string nom, string nom_l, long long rutt);
        void contratarEmpleado(Empleado* emp) {
            if (num_Emp < MAX_EMPLEADO) {
                empleados[num_Emp++] = emp;
                emp->setEmpresa(this);
            } else {
                cout << "No se pueden contratar más empleados." << endl;
            }
        }
        Paga total_paga_peso(){
            Paga totalp = Paga();
            for (int i = 0; i <= num_Emp-1; i++){
                totalp = totalp + empleados[i]->get_sueldo_peso();
            }
            return totalp;
        }
        Paga total_paga_dolar(){
            Paga totalp(0,Moneda::usd);
            for (int i = 0; i <= num_Emp-1; i++){
                totalp = totalp + empleados[i]->get_sueldo_dolar();
            }
            return totalp;
        }
        void mostrarDatosEmpresa(){
            cout<< "- Nombre:       "<<this->nombre<<endl;
            cout<< "- Nombre Legal: "<<this->nombre_legal<<endl;
            cout<< "- Rut:          "<<this->rut<<endl<<endl;
        }
        void mostrarDatosEmpleados(){
            for (int i = 0; i < num_Emp; i++){
                cout << "Empleado " << i+1 << ":" << endl;
                empleados[i]->mostrarDatosEmpleados();
                cout << endl;
            }
        }
        
        ~Empresa(){
            for (int i = 0; i < num_Emp; i++){
                delete empleados[i];
            }
        }
        void Eliminar() {
            delete this;
    }
};


Empresa::Empresa(string nom, string nom_l, long long rutt){
    nombre = nom;
    nombre_legal = nom_l;
    rut = rutt;
    num_Emp = 0;
}
#endif