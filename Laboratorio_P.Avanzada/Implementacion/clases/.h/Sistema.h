#ifndef sistema_
#define sistema_

#include <iostream>
#include <string>
#include "ISistema.h"
#include <ctime>
#include "../../ICollection/interfaces/ICollection.h"
#include "../../ICollection/interfaces/IDictionary.h"
#include "OfertaLaboral.h"
#include "Estudiante.h"
#include "Empresa.h"
#include "Carrera.h"
#include "Asignatura.h"



using namespace std;

class Sistema : public ISistema{
    private:
        static Sistema* instancia;
        Sistema();
        string memoriaSuc;
        string memoriaSec;
        Empresa* memoriaEmp;
        Estudiante* memoriaEst;
        OfertaLaboral* memoriaOf;
        IDictionary* estudiantes;
        IDictionary* empresas;
        IDictionary* asignaturas;
        IDictionary* ofertasLaborales;
        IDictionary* carreras;
    public:
        static Sistema* getInstancia(); //devuelve el controlador Sistema (Singleton)
        virtual ~Sistema(); //Destructor
        void listarEmpresas(); // imprime todos los DtEmpresa
        void seleccionarEmpresa(string rutEmpresa);
        void seleccionarSucursal(string sucursal);// devuelve un arreglo de string de secciones
        void seleccionarSeccion(string seccion);
        void altaOferta(DtOferta oferta);
        void seleccionarEstudianteyFecha(string ci, Date fecha);
        void listarOfertasActivas();// devuelve collection de DtOfertaActiva
        void elegirOferta(string numeroOferta);// devuelve collection de DtEstudiante
        void mostrarOfertasLaborales(); //devuelve una colecction de DtOferta
        void seleccionaEstudiante(string ciEst, float salario); //
        void eliminarOfertaLaboral(string numeroOferta);
        void modificarOfertaLaboral(DtModOferta DTModOferta);
        void elegirOfertaLaboral(string numero);
        Date pedirfecha(); // el sistema devuelve la fecha de hoy
        void seleccionarEstudiante(string ciEst);
        void verEstudiante();
        void seleccEstudiante(string codigoEstudiante);
        void modificarEstudiante(DtNuevoEstudiante DtNuevoEstudiante);
        void selecEstudiante(string ci);
        void elegirOfertaL(string numeroOferta);
        void listarAsignaturas();
        void linkearPrecarga();
        IDictionary* cargarPrecargaEstudiantes();
        IDictionary* cargarCarreras();
        IDictionary* cargarOfertas();
        IDictionary* cargarAsignaturas();
        void linkearPrecargaOfertas1();
        void linkearPrecargaOfertas2();
        void linkearPrecargaOfertas3();
        void linkearPrecargaOfertas4();
        void cargarPrecarga();
        bool mismasAsignaturas(string estudiante);

};

#endif