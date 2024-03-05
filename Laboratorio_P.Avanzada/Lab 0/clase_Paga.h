#ifndef PAGA_H
#define PAGA_H

#include <iostream>
#include <string>
#include <cmath>
#include "clase_Cambio.h"
using namespace std;

enum Moneda{
    us,
    usd
};

class Paga {
    private:
        float monto;
        Moneda moneda;
    public:
        Paga();
        Paga(float mont, Moneda mone);
        void setMonto(float);
        void setMoneda(Moneda);
        Paga a_dolar(){
            if (moneda == 0) {
                cout << "- Cotización a dolares realizada exitosamente -" << endl<< endl;
                return Paga(Cambio::a_dolar(monto), Moneda::usd);
            }
            else {
                cout << "- La paga seleccionada ya se encuentra en dolares - " << endl<<endl;
                return *this;
            }
        }
        Paga a_peso() {
            if (moneda == 1) {
                cout << "- Cotización a pesos realizada exitosamente -" << endl<< endl;
                return Paga(Cambio::a_pesos(monto), Moneda::us);
            }
            else {
                cout << "- La paga seleccionada ya se encuentra en pesos - " << endl<<endl;
                return *this;
            }
        }
        float get_monto() {
            return monto;
        }  
        Moneda get_moneda() {
            return moneda;
        }
        Paga& operator=(const Paga& otra) {
            monto = otra.monto;
            moneda = otra.moneda;
            return *this;
        }
        Paga operator*(float multip) {
            return Paga(monto * multip, moneda);
        }
        Paga operator+(const Paga& otra) {
            if (moneda == otra.moneda) {
                float nuevo_monto = monto + otra.monto;
                cout << "Paga sumada exitosamente" << endl;
                return Paga(nuevo_monto, moneda);
            }
            else {
                throw std::invalid_argument("No se pueden sumar pagas de diferentes monedas.");
                cout << "Debe realizar conversion primero" << endl;
                return  Paga(monto, moneda);
                
            }
        }
        friend ostream& operator<<(ostream& os, const Paga& p) {
            int mont = round(p.monto * 1.0f) / 1.0f;
            string monto_str = to_string(mont);
            int n = monto_str.length();
            for (int i = n-3; i > 0; i -= 3) {
                monto_str.insert(i, ".");
            }
            string mon;
            if (p.moneda == 0)
                mon = "us";
            else
                mon = "usd";
            os << monto_str << " " << mon << endl;
            return os;
        }
};

void Paga::setMonto(float _monto){
    this->monto = _monto;
}

void Paga::setMoneda(Moneda _moneda){
    this->moneda=_moneda;

}
Paga::Paga(){
    monto = 0;
    moneda = us;
}

Paga::Paga(float mont, Moneda mone) {
    this->monto = mont;
    this->moneda = mone;
}

#endif
