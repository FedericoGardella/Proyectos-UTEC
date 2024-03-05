#ifndef dtdatosestudiante_
#define dtdatosestudiante_

#include <iostream>
#include <string>
#include "DtEstudiantePlus.h"
#include "DtLlamados.h"
#include "DtAsignatura.h"
#include "../../ICollection/interfaces/ICollection.h"


using namespace std;

class DtDatosEstudiante {
    private:
        DtEstudiantePlus data;
        ICollection* llamados;
        ICollection* asigns;
    public:
        DtDatosEstudiante();
        DtDatosEstudiante(DtEstudiantePlus,ICollection*,ICollection*);
        DtEstudiantePlus getData();
        ICollection* getLlamados();
        ICollection* getAsigns();
        ~DtDatosEstudiante();
};

#endif