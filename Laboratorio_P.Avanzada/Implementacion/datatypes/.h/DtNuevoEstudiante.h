#ifndef dtnuevoestudiante_
#define dtnuevoestudiante_

#include <iostream>
#include <string>
#include "DtEstudiantePlus.h"

using namespace std;


class DtNuevoEstudiante {
    private:
        DtEstudiantePlus* data;
        string* agregarAsig;
        string* eliminarAsig;
        string* agregarCarr;
        string* eliminarCarr;
    public:
        DtNuevoEstudiante();
        DtNuevoEstudiante(DtEstudiantePlus*,string*,string*,string*,string*);
        DtEstudiantePlus* getData();
        string* getAgAsign();
        string* getElimAsign();
        string* getAgCarr();
        string* getElimCarr();
        ~DtNuevoEstudiante();
};

#endif