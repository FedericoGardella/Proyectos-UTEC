#ifndef dtestudianteplus_
#define dtestudianteplus_

#include <iostream>
#include <string>
#include "DtEstudiante.h"
#include "Date.h"

using namespace std;

class DtEstudiantePlus {
    private:
        Date* fecha_nac;
        string* telefono;
        int* creditos_obt;
        DtEstudiante* data;
    public:
        DtEstudiantePlus();
        DtEstudiantePlus(Date*,string*,int*,DtEstudiante*);
        Date* getFechaNac();
        string* getTel();
        int* getCred();
        DtEstudiante* getData();
        ~DtEstudiantePlus();
};

#endif