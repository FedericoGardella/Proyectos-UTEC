#ifndef date_
#define date_

#include <iostream>
#include "../../ICollection/interfaces/ICollectible.h"

using namespace std;

class Date : public ICollectible{
    private:
        int dia;
        int mes;
        int anio;
    public:
        Date();
        Date(int dd, int mm, int aa);
        int getDia();
        int getMes();
        int getAnio();
        void setDia(int);
        void setMes(int);
        void setAnio(int);
        virtual~Date();    

        friend std::ostream& operator<<(std::ostream& os, const Date& date) {
        os << "Fecha: " << date.dia << "/" << date.mes << "/" << date.anio;
        return os;
    }
};

#endif