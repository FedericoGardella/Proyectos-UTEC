#include "../.h/Estudiante.h"


Estudiante::Estudiante(){}

Estudiante::Estudiante(string ci,string nombre,Date fecha_nac,string telefono,int creditos_obtenidos,IDictionary* postulaciones,ICollection* cargos,IDictionary* asignaturas, IDictionary* carreras){
    this->ci = ci;
    this->nombre = nombre;
    this->fecha_nac = fecha_nac;
    this->telefono = telefono;
    this->creditos_obtenidos = creditos_obtenidos;
    if (postulaciones != NULL)
        this->postulaciones = postulaciones;
    else
        this->postulaciones = new OrderedDictionary;
    if (cargos != NULL)
        this->cargos = cargos;
    else
        this->cargos = new List;
    if (asignaturas != NULL)
        this->asignaturas = asignaturas;
    else
        this->asignaturas = new OrderedDictionary;
    if (carreras != NULL)
        this->carreras = carreras;
    else
        this->carreras = new OrderedDictionary;
}

string Estudiante::getCi(){
    return this->ci;
}

string Estudiante::getNombre(){
    return this->nombre;
}

Date Estudiante::getFecha_nac(){
    return this->fecha_nac;
}

string Estudiante::getTelefono(){
    return this->telefono;
}

int Estudiante::getCreditos_obtenidos(){
    return this->creditos_obtenidos;
}

IDictionary* Estudiante::getPostulaciones(){
    return this->postulaciones;
}

ICollection* Estudiante::getCargos(){
    return this->cargos;
}

IDictionary* Estudiante::getAsignaturas(){
    return this->asignaturas;
}

void Estudiante::setNombre(string nombre){
    this->nombre = nombre;
}

void Estudiante::setFechaNac(Date fecha_nac){
    this->fecha_nac = fecha_nac;
}

void Estudiante::setTelefono(string telefono){
    this->telefono = telefono;
}

void Estudiante::setCreditosObtenidos(int creditos_obtenidos){
    this->creditos_obtenidos = creditos_obtenidos;
}

void Estudiante::setPostulaciones(IDictionary* postulaciones){
    this->postulaciones = postulaciones;
}

void Estudiante::setCargos(ICollection* cargos){
    this->cargos = cargos;
}

void Estudiante::setAsignaturas(IDictionary* asignaturas){
    this->asignaturas = asignaturas;
}

bool Estudiante::estaVinculado(string numeroOferta) {
    IKey* key = new String(numeroOferta.c_str());
    Postulacion* post = (Postulacion*)postulaciones->find(key);
    delete key;
    return (post != NULL);
}

DtEstudiante Estudiante::listarEstudiante() {
    string * cedula = new string;
    *cedula = this->ci;
    string * nom = new string;
    *nom = this->nombre;
    DtEstudiante est(cedula,nom);
    return est;
}
// Caso de uso Asignar Oferta Laboral a Estudiante -- SC -- Operacion 2.2
void Estudiante::crearCargo(float salario, Date fechaInicio){
    CargoEfectivo * cargo = new CargoEfectivo(fechaInicio, salario); // pendiente de recibir el puntero a oferta laboral
    this->cargos->add(cargo);
}

void Estudiante::crearEntrevista(string num, Date fecha){
    IKey* key = new String(num.c_str()) ;
    Postulacion * post = (Postulacion*)postulaciones->find(key);
    delete key;
    Date* fech = &fecha;
    post->nuevaEntrevista(fech);
}

void Estudiante::desvincularEstudiantePostulacion(string codigo){
    IKey* key = new String(codigo.c_str()) ;
    postulaciones->remove(key);
    delete key;
}

Estudiante::~Estudiante(){}


void Estudiante::vincularEstudiantePost(Postulacion* pos, string codigo) {

    IKey* key = new String(codigo.c_str());
    postulaciones->add(key,pos);
}


void Estudiante::getDTDatosEstudiante() {

    cout << "Cedula: " << this->getCi() << endl;
    cout << "Nombre: " << this->getNombre() << endl;
    cout << "Fecha nacimiento: " << this->getFecha_nac() << endl;
    cout << "Telefono: " << this->getTelefono() << endl;
    cout << "Creditos obtenidos: " << this->getCreditos_obtenidos() << endl << endl;
}


void Estudiante::getNombresAsignaturas() {

    cout << "Asignaturas aprobadas: " << endl;
    IIterator* it;
    for (it = this->asignaturas->getIterator();it->hasCurrent();it->next()){
        Asignatura * as = (Asignatura*)it->getCurrent();
        cout << "Codigo: " << as->getCodigo() << endl;
        cout << "Nombre: " << as->getNombre() << endl;
        cout << "Creditos: " << as->getCreditos() << endl << endl;
    }
    delete it;
}


void Estudiante::getDTLlamados() {

    IIterator* it;
    for (it = this->postulaciones->getIterator();it->hasCurrent();it->next()){
        Postulacion * pos = (Postulacion*)it->getCurrent();
        pos->getDTLlamados();
        }
    delete it;
}

void Estudiante::linkearAsignatura(Asignatura* asig){
    string codigo = asig->getCodigo();
    IKey* key = new String(codigo.c_str());
    asignaturas->add(key,asig);
    int cred = asig->getCreditos();
    this->setCreditosObtenidos(this->getCreditos_obtenidos() + cred);
}

void Estudiante::linkearCarrera(Carrera* carr){
    string codigo = carr->getCodigo();
    IKey* key = new String(codigo.c_str());
    carreras->add(key,carr);
}

void Estudiante::modificarEstudiante(DtNuevoEstudiante DtNuevoEstudiante){

    if(DtNuevoEstudiante.getData() != NULL){
        if(DtNuevoEstudiante.getData()->getTel() != NULL){
            this->setTelefono(*DtNuevoEstudiante.getData()->getTel());
        }
    } 
    if(DtNuevoEstudiante.getData()->getFechaNac() != NULL){
        if(DtNuevoEstudiante.getData()->getFechaNac() != NULL){
           this-> setFechaNac(*DtNuevoEstudiante.getData()->getFechaNac());
        }
    }
    if(DtNuevoEstudiante.getData()->getData() != NULL){
        if(DtNuevoEstudiante.getData()->getData()->getNombre() != NULL){
            this->setNombre(*DtNuevoEstudiante.getData()->getData()->getNombre());
        }
    }       
      
    if(DtNuevoEstudiante.getElimAsign() != NULL){
        IKey* k = new String((*DtNuevoEstudiante.getElimAsign()).c_str());
        Asignatura* asig = (Asignatura*)asignaturas->find(k);
        if (asig == NULL){
            throw invalid_argument("No existe la asignatura solicitada");
        }
        int cred = asig->getCreditos();
        this->setCreditosObtenidos(this->getCreditos_obtenidos() - cred);
        this->asignaturas->remove(k);
        delete k;
    }


    if(DtNuevoEstudiante.getElimCarr() != NULL){
        IKey* k = new String((*DtNuevoEstudiante.getElimCarr()).c_str());
        Carrera* carr = (Carrera*)carreras->find(k);
       
        if (carr == NULL){
            throw invalid_argument("No existe la carrerasolicitada");
        }
        this->carreras->remove(k);
         delete k;
    }
}