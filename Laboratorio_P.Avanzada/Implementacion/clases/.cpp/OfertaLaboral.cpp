#include "../.h/OfertaLaboral.h"


using namespace std;

//constructores
OfertaLaboral::OfertaLaboral(){}

OfertaLaboral::OfertaLaboral(string numero,string titulo,string descripcion,int horas_sem,float salario_min,float salario_max,Date fecha_ini,Date fecha_fin,int puestos,Seccion* miSeccion,IDictionary* postulaciones,IDictionary* asignaturas){
    this->numero = numero;
    this->titulo = titulo;
    this->descripcion = descripcion;
    this->horas_sem = horas_sem;
    this->salario_min = salario_min;
    this->salario_max = salario_max;
    this->fecha_ini = fecha_ini;
    this->fecha_fin = fecha_fin;
    this->puestos = puestos;
    this->miSeccion = miSeccion;
    if (postulaciones != NULL)
        this->postulaciones = postulaciones;
    else
        this->postulaciones = new OrderedDictionary;
    if (asignaturas != NULL)
        this->asignaturas = asignaturas;
    else 
        this->asignaturas = new OrderedDictionary;
}
//Destructor
OfertaLaboral::~OfertaLaboral(){}
//gets
string OfertaLaboral::getNumero(){
    return this->numero;
}
string OfertaLaboral::getTitulo(){
    return this->titulo;
}
string OfertaLaboral::getDescripcion(){
    return this->descripcion;
}
int OfertaLaboral::getHoras_sem(){
    return this->horas_sem;
}   
float OfertaLaboral::getSalario_min(){
    return this->salario_min;
}
float OfertaLaboral::getSalario_max(){
    return this->salario_max;
}
Date OfertaLaboral::getFecha_ini(){
    return this->fecha_ini;
}       
Date OfertaLaboral::getFecha_fin(){
    return this->fecha_fin;
}   
int OfertaLaboral::getPuestos(){
    return this->puestos;
}
Seccion* OfertaLaboral::getSeccion(){
    return this->miSeccion;
}   
IDictionary* OfertaLaboral::getPostulaciones(){
    return this->postulaciones;
}
IDictionary* OfertaLaboral::getAsignaturas(){
    return this->asignaturas;
}
//sets
void OfertaLaboral::setTitulo(string titulo){
    this->titulo = titulo;
}
void OfertaLaboral::setDescripcion(string descripcion){
    this->descripcion = descripcion;
}
void OfertaLaboral::setHoras_sem(int horas_sem){
    this->horas_sem = horas_sem;
}
void OfertaLaboral::setSalario_min(float salario_min){
    this->salario_min = salario_min;
}
void OfertaLaboral::setSalario_max(float salario_max){
    this->salario_max = salario_max;
}
void OfertaLaboral::setFecha_ini(Date fecha_ini){
    this->fecha_ini = fecha_ini;
}
void OfertaLaboral::setFecha_fin(Date fecha_fin){
    this->fecha_fin = fecha_fin;
}
void OfertaLaboral::setPuestos(int puestos){
    this->puestos = puestos;
}
void OfertaLaboral::setPostulaciones(IDictionary* postulaciones){
    this->postulaciones = postulaciones;
}
void OfertaLaboral::setAsignaturas(IDictionary* asignaturas){
    this->asignaturas = asignaturas;
}

void OfertaLaboral::linkearAsignatura(Asignatura* asi){
    string codigo = asi->getCodigo();
    IKey* key = new String(codigo.c_str());
    asignaturas->add(key,asi);
}
void OfertaLaboral::linkearOfertaSeccion(Seccion* sec){
    this->miSeccion = sec;
}
void OfertaLaboral::getDtOfertaActiva(){
    cout << "NroExpediente: " << this->getNumero() << endl;
    cout << "Nombre: " << this->getTitulo() << endl;
    this->miSeccion->getNombreEmpresa();
    this->miSeccion->getDireccionSucursal();
    IIterator* it;
    int cant = 0;
    for (it= this->postulaciones->getIterator();it->hasCurrent();it->next()){
        cant++;
    }
    delete it;
    cout << "Canntidad de Inscriptos: " << cant << endl;
    cout << "Salario Minimo: " << this->getSalario_min() << endl;
    cout << "Salario Maximo: " << this->getSalario_max() << endl;
    cout << "Cantidad de Plazas: " << this->puestos << endl << endl << endl;

}

bool OfertaLaboral::estaVigente(Date hoy){
    int dia = hoy.getDia();
    int mes = hoy.getMes();
    int anio = hoy.getAnio();
    int Odia = this->getFecha_fin().getDia();
    int Omes = this->getFecha_fin().getMes();
    int Oanio = this->getFecha_fin().getAnio();
    if (Oanio > anio)
        return true;
    if (Oanio == anio && Omes > mes)
        return true;
    if (Oanio == anio && Omes == mes && Odia >= dia)
        return true;
    return false;
};

//Caso de uso Modificar Llamado -- SC -- Operacion cambiar datos
void OfertaLaboral::cambiarDatos(DtModOferta DTModOferta){
    if(DTModOferta.getOfertaLite() != NULL){
        if(DTModOferta.getOfertaLite()->getTitulo() != NULL){
            this->setTitulo(*(DTModOferta.getOfertaLite()->getTitulo()));
        }
        if(DTModOferta.getOfertaLite()->getDescripcion() != NULL){
            this->setDescripcion(*DTModOferta.getOfertaLite()->getDescripcion());
        }
        if(DTModOferta.getOfertaLite()->getCantHorasSem() != NULL){
            this->setHoras_sem(*DTModOferta.getOfertaLite()->getCantHorasSem());
        }
        if(DTModOferta.getOfertaLite()->getSalarioMin() != NULL){
            this->setSalario_min(*DTModOferta.getOfertaLite()->getSalarioMin());
        }
        if(DTModOferta.getOfertaLite()->getSalarioMax() != NULL){
            this->setSalario_max(*(DTModOferta.getOfertaLite()->getSalarioMax()));
        }
        if(DTModOferta.getOfertaLite()->getFechaCom() != NULL){
            this->setFecha_ini(*DTModOferta.getOfertaLite()->getFechaCom());
        }
        if(DTModOferta.getOfertaLite()->getFechaFin() != NULL){
            this->setFecha_fin(*DTModOferta.getOfertaLite()->getFechaFin());
        }
        if(DTModOferta.getOfertaLite()->getCantPuest() != NULL){
            this->setPuestos(*DTModOferta.getOfertaLite()->getCantPuest());
        }
    }
    if(DTModOferta.getElimAsign() != NULL){
        IKey* k = new String((*DTModOferta.getElimAsign()).c_str());
        Asignatura* asig = (Asignatura*)asignaturas->find(k);
        if (asig == NULL){
            throw invalid_argument("No existe la asignatura solicitada");
        }
        this->asignaturas->remove(k);
        delete k;
    }
}

void OfertaLaboral::destruirOferta(){
    IIterator * it;
    Postulacion* post;
    if(this->postulaciones != NULL){
        for(it = this->postulaciones->getIterator(); it->hasCurrent(); it->next()){
            post = (Postulacion *)it->getCurrent();  
            post->desvincularEstudiantePostulacion(this->getNumero());
            delete post;
        }
    delete it;
         
    }     
    this->miSeccion->desvincularSeccionOferta(this->getNumero());
}

void OfertaLaboral::addPostulacion(Estudiante* est, Date fechaActual) {

    Postulacion* pos = new Postulacion(fechaActual,NULL,this,est);

    string codigo = est->getCi();
    IKey* key = new String(codigo.c_str());
    postulaciones->add(key,pos);

    pos->vincularEstudiantePost(this->getNumero()); //se hace en postulacion
}


void OfertaLaboral::getDTLlamados() {

    cout << "Numero oferta: " << this->getNumero() << endl;
    cout << "Titulo oferta: " << this->getTitulo() << endl;

    this->miSeccion->getDTSeccion();
}