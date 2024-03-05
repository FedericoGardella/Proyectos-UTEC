#ifndef Asignatura_
#define Asignatura_
#include <iostream>
#include "../../ICollection/interfaces/ICollectible.h"


using namespace std;

class Asignatura: public ICollectible {
    private:
        string nombre;
        string codigo;
        int creditos;
    public:
        Asignatura(string,string,int);
        string getNombre();
        string getCodigo();
        int getCreditos();
        void setNombre(string);
        void setCodigo(string);
        void setCreditos(int);
        virtual ~Asignatura();
};



#endif
