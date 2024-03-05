#ifndef dtllamados_
#define dtllamados_

#include <iostream>
#include <string>
#include "../../ICollection/interfaces/ICollectible.h"
#include "DtSeccion.h"

using namespace std;

class DtLlamados : public ICollectible {
    private:
        string numOferta;
        string tituloOferta;
        DtSeccion miSec;
    public:
        DtLlamados();
        DtLlamados(string,string,DtSeccion);
        string getNum();
        string getTitulo();
        DtSeccion getSec();
        void setNum(string);
        void setTitulo(string);
        void setSec(DtSeccion);
        ~DtLlamados();
};

#endif