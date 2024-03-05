#ifndef Seccion_
#define Seccion_
#include <iostream>
#include <string>

#include "../../ICollection/interfaces/ICollectible.h"
#include "../../ICollection/interfaces/IDictionary.h"
#include "../../ICollection/String.h"
#include "Sucursal.h"
#include "OfertaLaboral.h"


using namespace std;

class OfertaLaboral;

class Sucursal;

class Seccion: public ICollectible {
    private:
        string nombre;
        string telefono;
        Sucursal* miSucursal;
        IDictionary* ofertas;
    public:
        Seccion(string nombre,string telefono, Sucursal* miSucursal, IDictionary* ofertas);
        string getNombre();
        string getTelefono();
        Sucursal* getSucursal();
        IDictionary* getOfertas();
        void setNombre(string nombre);
        void setTelefono(string telefono);
        void setSucursal(Sucursal* miSucursal);
        void setOfertas(IDictionary* ofertas);
        void linkSeccionOferta(OfertaLaboral* of);
        void getNombreEmpresa();
        void getDireccionSucursal();
        void desvincularSeccionOferta(string oferta);
        static IDictionary* cargarPrecargaSeccion1();
        static IDictionary* cargarPrecargaSeccion2();
        static IDictionary* cargarPrecargaSeccion3();
        static IDictionary* cargarPrecargaSeccion4();
        static IDictionary* cargarPrecargaSeccion5();
        static IDictionary* cargarPrecargaSeccion6();
        static IDictionary* cargarPrecargaSeccion7();
        void linkearPrecarga(Sucursal* suc);
        void linkearPrecargaOfertas(OfertaLaboral* of);

        virtual ~Seccion();
        void getDTSeccion();
};



#endif
