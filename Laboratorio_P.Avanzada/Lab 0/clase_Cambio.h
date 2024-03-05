#ifndef CAMBIO_H
#define CAMBIO_H

#include <iostream>
#include <string>
#include <cmath>

using namespace std;

class Cambio {
    private:
        static const float cotizacion;
    public: 
        static float a_pesos(float importe) {
            return importe * cotizacion;
        }
        static float a_dolar(float importe) {
            return importe / cotizacion;
        }
};

const float Cambio::cotizacion = 40.15;

#endif