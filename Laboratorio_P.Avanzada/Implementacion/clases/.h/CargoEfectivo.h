#ifndef CargoEfectivo_
#define CargoEfectivo_
#include <iostream>
#include "../../datatypes/.h/Date.h"

using namespace std;

class CargoEfectivo: public ICollectible {
    private:
        Date fecha;
        float salario;
    public:
        CargoEfectivo();
        CargoEfectivo(Date fecha,float salario);
        Date getfecha();
        float getSalario();
        virtual ~CargoEfectivo();
};



#endif
