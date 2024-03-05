#ifndef Estudiante_
#define Estudiante_
#include <iostream>
#include <string>
#include "../../ICollection/interfaces/IDictionary.h"
#include "../../ICollection/collections/OrderedDictionary.h"
#include "../../ICollection/interfaces/ICollection.h"
#include "../../datatypes/.h/Date.h"
#include "../../datatypes/.h/DtEstudiante.h"
#include "../../ICollection/collections/List.h"
#include "../../datatypes/.h/DtNuevoEstudiante.h"
#include "Postulacion.h"
#include "CargoEfectivo.h"
#include "Asignatura.h"
#include "Carrera.h"



using namespace std;

class Postulacion;

class Estudiante: public ICollectible {
    private:
        string ci;
        string nombre;
        Date fecha_nac;
        string telefono;
        int creditos_obtenidos;
        IDictionary* postulaciones;
        ICollection* cargos;
        IDictionary* asignaturas; 
        IDictionary* carreras;
    public:
        Estudiante();
        Estudiante(string ci,string nombre,Date fecha_nac,string telefono,int creditos_obtenidos,IDictionary* postulaciones,ICollection* cargos,IDictionary* asignaturas, IDictionary* carreras);
        string getCi();
        string getNombre();
        Date getFecha_nac();
        string getTelefono();
        int getCreditos_obtenidos();
        IDictionary* getPostulaciones();
        ICollection* getCargos();
        IDictionary* getAsignaturas();
        void setNombre(string nombre);
        void setFechaNac(Date fecha_nac);
        void setTelefono(string telefono);
        void setCreditosObtenidos(int creditos_obtenidos);
        void setPostulaciones(IDictionary* postulaciones);
        void setCargos(ICollection* cargos);
        void setAsignaturas(IDictionary* asignaturas);
        bool estaVinculado(string);
        DtEstudiante listarEstudiante();
        void crearEntrevista(string num,Date fecha);
        void crearCargo(float salario, Date fechaInicio);
        void desvincularEstudiantePostulacion(string codigo);
        virtual ~Estudiante();
        void vincularEstudiantePost(Postulacion*, string);
        void getDTDatosEstudiante();
        void getNombresAsignaturas();
        void getDTLlamados();
        void linkearAsignatura(Asignatura* asig);
        void linkearCarrera(Carrera* carr);
        void modificarEstudiante(DtNuevoEstudiante DtNuevoEstudiante);
};



#endif
