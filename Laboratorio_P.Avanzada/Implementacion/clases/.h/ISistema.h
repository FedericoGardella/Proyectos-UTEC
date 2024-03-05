#ifndef ISISTEMA
#define ISISTEMA
#include <iostream>
#include <string>
#include "../../datatypes/.h/DtNuevoEstudiante.h"
#include "../../datatypes/.h/DtSucursal.h"
#include "../../datatypes/.h/DtDatosEstudiante.h"
#include "../../datatypes/.h/DtOferta.h"
#include "../../datatypes/.h/Date.h"
#include "../../datatypes/.h/DtOfertaActiva.h"
#include "../../datatypes/.h/DtEstudiante.h"
#include "../../datatypes/.h/DtModOferta.h"
#include "../../ICollection/interfaces/IDictionary.h"


using namespace std;

class ISistema{
    public:
        virtual ~ISistema();
        virtual void listarEmpresas() = 0; // imprime todos los DtEmpresa
        virtual void seleccionarEmpresa(string rutEmpresa) = 0;
        virtual void seleccionarSucursal(string sucursal) = 0;// devuelve un arreglo de string de secciones
        virtual void seleccionarSeccion(string seccion) = 0;
        virtual void altaOferta(DtOferta oferta) = 0;
        virtual void seleccionarEstudianteyFecha(string ci, Date fecha) = 0;
        virtual void listarOfertasActivas() = 0;// devuelve collection de DtOfertaActiva
        virtual void elegirOferta(string numeroOferta) = 0;// devuelve collection de DtEstudiante
        virtual void mostrarOfertasLaborales() = 0; //devuelve una colecction de DtOferta
        virtual void seleccionaEstudiante(string ciEst, float salario) = 0; //
        virtual void eliminarOfertaLaboral(string numeroOferta) = 0;
        virtual void modificarOfertaLaboral(DtModOferta DTModOferta) = 0;
        virtual void elegirOfertaLaboral(string numero) = 0;
        virtual Date pedirfecha() = 0; // el sistema devuelve la fecha de hoy
        virtual void seleccionarEstudiante(string ciEst) = 0;
        virtual void verEstudiante() = 0;
        virtual void seleccEstudiante(string codigoEstudiante) = 0;
        virtual void modificarEstudiante(DtNuevoEstudiante DtNuevoEstudiante) = 0;
        virtual void selecEstudiante(string ci) = 0;
        virtual void listarAsignaturas() = 0;
        virtual void elegirOfertaL(string numeroOferta) = 0;
        virtual void cargarPrecarga() = 0;
        virtual void linkearPrecarga() = 0;
        virtual IDictionary* cargarPrecargaEstudiantes() = 0;
        virtual IDictionary* cargarCarreras() = 0;
        virtual IDictionary* cargarOfertas() = 0;
        virtual IDictionary* cargarAsignaturas() = 0;
        virtual void linkearPrecargaOfertas1() = 0;
        virtual void linkearPrecargaOfertas2() = 0;
        virtual void linkearPrecargaOfertas3() = 0;
        virtual void linkearPrecargaOfertas4() = 0;
        virtual bool mismasAsignaturas(string estudiante) = 0;
};

#endif

    



