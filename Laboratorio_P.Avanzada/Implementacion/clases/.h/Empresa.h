#ifndef empresa_
#define empresa_
#include <iostream>
#include <string>
#include "../../datatypes/.h/DtEmpresa.h"
#include "../../ICollection/interfaces/IDictionary.h"
#include "../../ICollection/collections/OrderedDictionary.h"
#include "OfertaLaboral.h"
#include "Sucursal.h"

using namespace std;

class Sucursal;

class OfertaLaboral;

class Empresa: public ICollectible {
    private:
        string rut;
        string nombre;
        IDictionary* sucursales;
    public:
        Empresa(string rut,string nombre, IDictionary* Sucursales);
        string getRut();
        string getNombre();
        IDictionary* getSucursales();
        void setRut(string rut);
        void setNombre(string nombre);
        void setSucursales(IDictionary* Sucursales);
        DtEmpresa getDtEmpresa();
        void getNombreSucursales();
        void seleccionarSucursal(string sucursal);
        void linkearSeccionOferta(OfertaLaboral* of, string sucursal, string seccion);
        static IDictionary* cargarPrecargaEmpresa();
        void linkearPrecarga();
        void linkearPrecargaOfertas1(OfertaLaboral* of);
        void linkearPrecargaOfertas2(OfertaLaboral* of);
        void linkearPrecargaOfertas3(OfertaLaboral* of);
        void linkearPrecargaOfertas4(OfertaLaboral* of);
        virtual ~Empresa();

        void getDTEmpresa();
};

#endif
