#ifndef FIJO_JORNALERO_H
#define FIJO_JORNALERO_H

#include <iostream>
#include <string>
#include <cmath>
#include "clase_Paga.h"
#include "clase_Empleado.h"
#include "clase_Cambio.h"

using namespace std;

class Empresa;
class Empleado;


class Fijo : public Empleado {
    public:
        Fijo(string nom, string cii, int edd, Paga vh) : Empleado(nom,cii,edd,vh){};
        Paga get_sueldo_peso(){
            Paga sueldo;
            if (this->getMoneda() == 0)
                sueldo = Paga(this->getMonto(), Moneda::us) * 40;
            else {
                sueldo = Paga(Cambio::a_pesos(this->getMonto()), Moneda::us) * 40;
            }
            return sueldo;
        }
        Paga get_sueldo_dolar(){
            Paga sueldo;
            if (this->getMoneda() == 1)
                sueldo = Paga(this->getMonto(),Moneda::usd) * 40;
            else {
                sueldo = Paga(Cambio::a_dolar(this->getMonto()), Moneda::usd) * 40;
            }
            return sueldo;
        }
};


class Jornalero : public Empleado {
    private:
        int horas;
    public:
        Jornalero(string nom, string cii, int edd, Paga vh, int hor) : Empleado(nom,cii,edd,vh){
            this->horas = hor;
        }
        Paga get_sueldo_peso(){
            Paga sueldo;
            if (this->getMoneda() == 0)
                sueldo = Paga(this->getMonto(), Moneda::us) * horas;
            else {
                sueldo = Paga(Cambio::a_pesos(this->getMonto()), Moneda::us) * horas;
            }
            return sueldo;
        }
        Paga get_sueldo_dolar(){
            Paga sueldo;
            if (this->getMoneda() == 1)
                sueldo = Paga(this->getMonto(),Moneda::usd) * horas;
            else {
                sueldo = Paga(Cambio::a_dolar(this->getMonto()), Moneda::usd) * horas;
            }
            return sueldo;
        }
};
#endif