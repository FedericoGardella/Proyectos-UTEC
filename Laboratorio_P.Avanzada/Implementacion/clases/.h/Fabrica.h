#ifndef Fabrica_
#define Fabrica_

#include "ISistema.h"
#include "Sistema.h"

class Fabrica {
    public:
        static ISistema * getSistema();
};

#endif

