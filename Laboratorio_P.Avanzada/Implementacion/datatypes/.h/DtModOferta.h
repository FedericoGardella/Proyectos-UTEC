#ifndef dtmodoferta_
#define dtmodoferta_

#include <iostream>
#include <string>
#include "DtOfertaLite.h"

using namespace std;


class DtModOferta {
    private:
        DtOfertaLite* ofl;
        string* agregarAsig;
        string* eliminarAsig;
    public:
        DtModOferta();
        DtModOferta(DtOfertaLite*,string*,string*);
        DtOfertaLite* getOfertaLite();
        string* getAgAsign();
        string* getElimAsign();
        void setOfertaLite(DtOfertaLite*);
        void setAgAsign(string*);
        void setElimAsign(string*);
        ~DtModOferta();
};

#endif