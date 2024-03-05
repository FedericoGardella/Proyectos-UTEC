#ifndef Carrera_
#define Carrera_
#include <iostream>
#include "../../ICollection/interfaces/ICollectible.h"


using namespace std;

class Carrera: public ICollectible {
    private:
        string codigo;
        string nombre;
    public:
        Carrera(string,string);
        string getCodigo();
        string getNombre();
        void setCodigo(string);
        void setNombre(string);
        virtual ~Carrera();
};


#endif