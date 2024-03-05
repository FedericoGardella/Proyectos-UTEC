#ifndef Sucursal_
#define Sucursal_
#include <iostream>
#include <string>
#include "../../ICollection/interfaces/IDictionary.h"
#include "Empresa.h"
#include "OfertaLaboral.h"
#include "Seccion.h"


using namespace std;

class Empresa;

class Seccion;

class OfertaLaboral;

class Sucursal: public ICollectible {
    private:
        string nombre;
        string telefono;
        string direccion;
        IDictionary* secciones;
        Empresa* miEmpresa;
    public:
        Sucursal(string nombre,string telefono, string direccion, IDictionary* Secciones, Empresa* miEmpresa);
        string getNombre();
        string getTelefono();
        string getDireccion();
        IDictionary* getSecciones();
        Empresa* getEmpresa();
        void setNombre(string nombre);
        void setTelefono(string telefono);
        void setDireccion(string direccion);
        void setSeccion(IDictionary* Secciones);
        void setEmpresa(Empresa* empresa);
        void getNombreSecciones();
        void linkearSeccionOferta(OfertaLaboral* of, string seccion);
        void getNombreEmpresa();
        static IDictionary* cargarPrecargaSucursal1();
        static IDictionary* cargarPrecargaSucursal2();
        static IDictionary* cargarPrecargaSucursal3();
        static IDictionary* cargarPrecargaSucursal4();
        static IDictionary* cargarPrecargaSucursal5();
        void linkearPrecarga(Empresa* emp);
        void linkearPrecargaOfertas1(OfertaLaboral* of);
        void linkearPrecargaOfertas2(OfertaLaboral* of);
        void linkearPrecargaOfertas3(OfertaLaboral* of);
        void linkearPrecargaOfertas4(OfertaLaboral* of);
        virtual ~Sucursal();

        void getDTSucursal();
};



#endif
