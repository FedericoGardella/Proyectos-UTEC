#ifndef Postulacion_
#define Postulacion_
#include <iostream>
#include "OfertaLaboral.h"
#include "Estudiante.h"

using namespace std;

class OfertaLaboral;

class Estudiante;

class Postulacion: public ICollectible {
    private:
        Date fecha;
        ICollection* entrevistas;
        OfertaLaboral* oferta;
        Estudiante* estudiante;
    public:
        Postulacion(Date fecha,ICollection* entrevistas,OfertaLaboral* oferta,Estudiante* estudiante);
        Date getFecha();
        ICollection* getEntrevistas();
        OfertaLaboral* getOferta();
        Estudiante* getEstudiante();
        void setEntrevistas(ICollection* entrevistas);
        bool esOferta(string);
        void nuevaEntrevista(Date* fecha);
        void desvincularEstudiantePostulacion(string codigo);
        virtual ~Postulacion();        


        void vincularEstudiantePost(string);
        void getDTLlamados();
};



#endif
