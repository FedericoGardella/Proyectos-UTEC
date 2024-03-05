#include "../.h/CargoEfectivo.h"

//constructores
CargoEfectivo::CargoEfectivo(){}

CargoEfectivo::CargoEfectivo(Date fecha,float salario){
    this->fecha = fecha;
    this->salario= salario;   
}

//Destructor
CargoEfectivo::~CargoEfectivo(){}

//gets
Date CargoEfectivo::getfecha()
{
	return this->fecha;
}

float CargoEfectivo::getSalario(){
    return this->salario;
}


