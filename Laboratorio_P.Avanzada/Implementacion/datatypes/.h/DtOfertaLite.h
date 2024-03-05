#ifndef dtofertalite_
#define dtofertalite_

#include <iostream>
#include <string>
#include "Date.h"

using namespace std;

class DtOfertaLite {
    private:
        string* nroExpediente;
        string* titulo;
        string* descripcion;
        int* cantHorasSem;
        float* salarioMin;
        float* salarioMax;
        Date* fechaCom;
        Date* fechaFin;
        int* cantPuest;
    public:
        DtOfertaLite();
        DtOfertaLite(string*,string*,string*,int*,float*,float*,Date*,Date*,int*);
        string* getNroExpediente();
        string* getTitulo();
        string* getDescripcion();
        int* getCantHorasSem();
        float* getSalarioMin();
        float* getSalarioMax();
        Date* getFechaCom();
        Date* getFechaFin();
        int* getCantPuest();

        //Destructor
        ~DtOfertaLite();
      
};

#endif