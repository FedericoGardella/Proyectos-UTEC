#ifndef dtempresa_
#define dtempresa_

#include <iostream>
#include <string>

using namespace std;

class DtEmpresa {
    private:
        string rut;
        string nombre;
    public:
        DtEmpresa();
        DtEmpresa(string,string);
        string getRut();
        string getNombre();
        void setRut(string);
        void setNombre(string);
        ~DtEmpresa();
};

#endif