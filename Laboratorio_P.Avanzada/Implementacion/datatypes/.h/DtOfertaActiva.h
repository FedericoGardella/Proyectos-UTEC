#ifndef dtofertaactiva_
#define dtofertaactiva_

#include <iostream>
#include <string>

using namespace std;

class DtOfertaActiva {
    private:
        string nombre;
        string empresa;
        string ubicacion;
        int cantInscriptos;
        float salarioMin;
        float salarioMax;
        int cantPlazas;
    public:
        DtOfertaActiva();
        DtOfertaActiva(string,string,string,int,float,float,int);
        string getNombre();
        string getEmpresa();
        string getUbicacion();
        int getCantInscriptos();
        float getSalarioMin();
        float getSalarioMax();
        int getCantPlazas();
        ~DtOfertaActiva();
};

#endif