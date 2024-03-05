#ifndef dtseccion_
#define dtseccion_

#include <iostream>
#include <string>
#include "DtSucursal.h"

using namespace std;

class DtSeccion {
    private:
        string telefono;
        string nombre;
        DtSucursal miSuc;
    public:
        DtSeccion();
        DtSeccion(string,string,DtSucursal);
        string getTel();
        string getNombre();
        DtSucursal getSuc();
        void setTel(string);
        void setNombre(string);
        void setSuc(DtSucursal);
        ~DtSeccion();
};

#endif