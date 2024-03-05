#ifndef dtasignatura_
#define dtasignatura_

#include <iostream>
#include <string>
#include "../../ICollection/interfaces/ICollectible.h"

using namespace std;

class DtAsignatura : public ICollectible{
    private:
        string codigo;
        string nombre;
        int creditos;
    public:
        DtAsignatura();
        DtAsignatura(string,string,int);
        string getCodigo();
        string getNombre();
        int getCreditos();
        void setCodigo(string);
        void setNombre(string);
        void setCreditos(int);
        ~DtAsignatura();
};

#endif