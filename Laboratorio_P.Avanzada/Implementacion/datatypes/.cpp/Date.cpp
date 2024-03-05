#include "../.h/Date.h"


//Constructores
Date::Date(){}

Date::Date(int  dd, int  mm, int aa){
    this->dia = dd;
    this->mes = mm;
    this->anio = aa;
}
//Destructor

Date::~Date(){}

//gets

int Date::getDia()
{
    return this->dia;
}

int Date::getMes()
{
    return this->mes;
}

int Date::getAnio()
{
    return this->anio;
}

//sets

void Date::setDia(int dia)
{
    this->dia = dia;
}

void Date::setMes(int mes)
{
    this->mes = mes;
}

void Date::setAnio(int anio)
{
    this->anio = anio;
}
