#ifndef dtsucursal_
#define dtsucursal_

#include <iostream>
#include <string>
#include "DtEmpresa.h"

using namespace std;

class DtSucursal {
    private:
        string telefono;
        string nombre;
        string direccion;
        DtEmpresa miEmp;
    public:
        DtSucursal();
        DtSucursal(string,string,string,DtEmpresa);
        string getTel();
        string getNombre();
        string getDireccion();
        DtEmpresa getEmp();
        void setTel(string);
        void setNombre(string);
        void setDireccion(string);
        void setEmp(DtEmpresa);
        virtual ~DtSucursal();
};

#endif