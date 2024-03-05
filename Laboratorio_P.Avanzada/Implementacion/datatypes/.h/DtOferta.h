#ifndef dtoferta_
#define dtoferta_

#include <iostream>
#include <string>
#include "DtOfertaLite.h"

using namespace std;

const int MAX_ASIGN = 15;

class DtOferta {
    private:
        DtOfertaLite* ofl;
        string conjAsig[MAX_ASIGN];
        int cantAsig;
    public:
        DtOferta();
        DtOferta(DtOfertaLite*,string*,int);
        DtOfertaLite* getOfertaLite();
        string* getConjAsign();
        int getCantAsig();
        ~DtOferta();
};

#endif