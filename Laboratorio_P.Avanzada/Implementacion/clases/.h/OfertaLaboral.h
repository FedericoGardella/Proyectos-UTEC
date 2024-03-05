#ifndef OfertaLaboral_
#define OfertaLaboral_
#include <iostream>
#include <string>
#include "../../datatypes/.h/Date.h"
#include "../../datatypes/.h/DtModOferta.h"
#include "../../ICollection/interfaces/IDictionary.h"
#include "Seccion.h"
#include "Estudiante.h"
#include "Postulacion.h"
#include "Asignatura.h"

using namespace std;

class Seccion;

class Postulacion;

class Estudiante;

class OfertaLaboral: public ICollectible {
    private:
        string numero;
        string titulo;
        string descripcion;
        int horas_sem;
        float salario_min;
        float salario_max;
        Date fecha_ini;
        Date fecha_fin;
        int puestos;
        Seccion* miSeccion;
        IDictionary* postulaciones;
        IDictionary* asignaturas;
    public:
        OfertaLaboral();
        OfertaLaboral(string numero,string titulo,string descripcion,int horas_sem,float salario_min,float salario_max,Date fecha_ini,Date fecha_fin,int puestos,Seccion* miSeccion,IDictionary* postulaciones,IDictionary* asignaturas);
        string getNumero();
        string getTitulo();
        string getDescripcion();
        int getHoras_sem();
        float getSalario_min();
        float getSalario_max();
        Date getFecha_ini();
        Date getFecha_fin();
        int getPuestos();
        Seccion* getSeccion();
        IDictionary* getPostulaciones();
        IDictionary* getAsignaturas();        
        void setTitulo(string titulo);
        void setDescripcion(string descripcion);
        void setHoras_sem(int horas_sem);
        void setSalario_min(float salario_min);
        void setSalario_max(float salario_max);
        void setFecha_ini(Date fecha_ini);
        void setFecha_fin(Date fecha_fin);
        void setPuestos(int puestos);
        void setPostulaciones(IDictionary* postulaciones);
        void setAsignaturas(IDictionary* asignaturas);
        void linkearAsignatura(Asignatura* asi);
        void linkearOfertaSeccion(Seccion* sec);
        void getDtOfertaActiva();
        bool estaVigente(Date hoy);
        void cambiarDatos(DtModOferta DTModOferta);
        void destruirOferta();
        virtual ~OfertaLaboral();

        void addPostulacion(Estudiante*,Date);
        void getDTLlamados();
};


#endif


