#ifndef dtestudiante_
#define dtestudiante_

#include <iostream>
#include <string>

using namespace std;

class DtEstudiante {
    private:
        string* ci;
        string* nombre;
    public:
        DtEstudiante();
        DtEstudiante(string*,string*);
        string* getCi();
        string* getNombre();
        ~DtEstudiante();
};

#endif