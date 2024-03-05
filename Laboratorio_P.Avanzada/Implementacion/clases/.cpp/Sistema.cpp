#include "../.h/Sistema.h"

#include <iostream>

Sistema * Sistema::instancia = NULL;

Sistema::Sistema(){
    this->memoriaOf = NULL;
    this->memoriaEmp = NULL;
    this->memoriaEst = NULL;
    this->estudiantes = new OrderedDictionary();
    this->empresas = new OrderedDictionary();
    this->asignaturas = new OrderedDictionary();
    this->ofertasLaborales = new OrderedDictionary();
    this->carreras = new OrderedDictionary();
}
Sistema::~Sistema(){ }

Sistema * Sistema::getInstancia() {
    if (instancia == NULL) {
        instancia = new Sistema();
    }
    return instancia;
}

  
void Sistema::listarEmpresas(){
    IIterator * it;
    Empresa* emp;
    for(it = empresas->getIterator(); it->hasCurrent(); it->next()){
        emp = (Empresa *)it->getCurrent();
        cout << "Nombre:" << emp->getNombre() << endl;
        cout << "Rut:" << emp->getRut() << endl << endl;
    }
    delete it;
}

void Sistema::listarAsignaturas(){
    IIterator * it;
    Asignatura* asig;
    for(it = this->asignaturas->getIterator(); it->hasCurrent(); it->next()){
        asig = (Asignatura *)it->getCurrent();
        cout << "Nombre:" << asig->getNombre() << endl;
        cout << "Codigo:" << asig->getCodigo() << endl << endl;
    }
}

void Sistema::seleccionarEmpresa(string rutEmpresa){
    IKey* k = new String(rutEmpresa.c_str());
    Empresa* empre = (Empresa*)empresas->find(k);
    this->memoriaEmp = empre;
    delete k;
    empre->getNombreSucursales();
}
// Caso de uso Asignar Oferta Laboral a Estudiante -- SC -- Operacion 2
void Sistema::seleccionaEstudiante(string ciEst, float salario){
    IKey* k = new String(ciEst.c_str());
    Estudiante* est = (Estudiante*)estudiantes->find(k);
    delete k;
    if (est == NULL){
        throw invalid_argument("No existe el estudiante solicitado");
    }
    est->crearCargo(salario,this->pedirfecha()); //aca hay que pedir la fecha de hoy
}

// imprime datos estudaintes
void Sistema::elegirOferta(string numeroOferta){

    IIterator * it;

    IKey * k = new String(numeroOferta.c_str());
    OfertaLaboral * ofer = (OfertaLaboral *)ofertasLaborales->find(k);
    delete(k);
    this->memoriaOf = ofer;

    bool ok = false;
    for (it= this->estudiantes->getIterator();it->hasCurrent();it -> next()){
        Estudiante * est = (Estudiante*)it->getCurrent();

        ok = est->estaVinculado(numeroOferta);

        if (!ok) {
            cout << "Cedula: " << est->getCi() << endl;
            cout << "Nombre: " << est->getNombre() << endl << endl;
        }
	}
    delete it;
}

void Sistema::elegirOfertaL(string numeroOferta){

    IIterator * it;

    IKey * k = new String(numeroOferta.c_str());
    OfertaLaboral * ofer = (OfertaLaboral *)ofertasLaborales->find(k);
    delete(k);
    this->memoriaOf = ofer;

    bool ok = false;
    for (it= this->estudiantes->getIterator();it->hasCurrent();it -> next()){
        Estudiante * est = (Estudiante*)it->getCurrent();

        ok = est->estaVinculado(numeroOferta);

        if (ok) {
            cout << "Cedula: " << est->getCi() << endl;
            cout << "Nombre: " << est->getNombre() << endl << endl;
        }
	}
    delete it;
}

void Sistema::seleccionarSucursal(string sucursal){
    this->memoriaSuc = sucursal;
    this->memoriaEmp->seleccionarSucursal(sucursal);
}
void Sistema::seleccionarSeccion(string seccion){
    this->memoriaSec = seccion;
}
void Sistema::mostrarOfertasLaborales(){
    IIterator* it;
    for (it = this->ofertasLaborales->getIterator();it->hasCurrent();it->next()){
        OfertaLaboral * of = (OfertaLaboral*)it->getCurrent();
        cout << "Numero:" << of->getNumero() << endl;
        cout << "Titulo:" << of->getTitulo() << endl;
        cout << "Descripcion:" << of->getDescripcion() << endl << endl;
    }
    delete it;
}

void Sistema::altaOferta(DtOferta oferta){
    DtOfertaLite* ofl = oferta.getOfertaLite();
    OfertaLaboral* of = new OfertaLaboral(*ofl->getNroExpediente(),*ofl->getTitulo(),
    *ofl->getDescripcion(), *ofl->getCantHorasSem(), *ofl->getSalarioMin(), *ofl->getSalarioMax(),
    *ofl->getFechaCom(), *ofl->getFechaFin(), *ofl->getCantPuest(), NULL, NULL, NULL);
    string codigo = *ofl->getNroExpediente();
    IKey* key = new String(codigo.c_str());
    ofertasLaborales->add(key,of);
    string* asigns = oferta.getConjAsign();
    string asig;
    Asignatura* asi;
    for(int i=0; i<oferta.getCantAsig(); i++){
        asig = asigns[i];
        key = new String(asig.c_str());
        asi = (Asignatura*)asignaturas->find(key);
        of->linkearAsignatura(asi);
    }
    this->memoriaEmp->linkearSeccionOferta(of, this->memoriaSuc, this->memoriaSec);
}

void Sistema::seleccionarEstudianteyFecha(string ci, Date fecha){
    string codigo = this->memoriaOf->getNumero();
    IKey* key = new String(ci.c_str()) ;
    Estudiante * est = (Estudiante*)estudiantes->find(key);
    delete key;
    est->crearEntrevista(codigo,fecha);
}
void Sistema::listarOfertasActivas(){
    Date hoy = this->pedirfecha();
    IIterator* it;
    bool ok = false;
    for (it= this->ofertasLaborales->getIterator();it->hasCurrent();it->next()){
        OfertaLaboral * of = (OfertaLaboral*)it->getCurrent();
        ok = of->estaVigente(hoy);
        if(ok){
            of->getDtOfertaActiva();
            ok = false;
        }
    }
    delete it;
}
Date Sistema::pedirfecha(){
    time_t t = time(nullptr);
    tm* now = localtime(&t);
    int Dia, Mes, Anio;
    Dia = now->tm_mday;
    Mes = (now->tm_mon + 1);
    Anio = (now->tm_year + 1900);
    Date dia(Dia,Mes,Anio);
    return dia;
}
//Caso de uso Modificar Llamado -- SC -- Operacion ModificarOfertaLaboral
void Sistema::modificarOfertaLaboral( DtModOferta DTModOferta){ //controlar q DTOFERTALITE no sea null
    if(DTModOferta.getAgAsign() != NULL){
        IKey* k = new String((*DTModOferta.getAgAsign()).c_str());
        Asignatura* asig = (Asignatura*)asignaturas->find(k);         
        if(asig == NULL){
            throw invalid_argument("No existe la asignatura que desea agregar");
        }  
        this->memoriaOf->linkearAsignatura(asig);
        delete k; 
    }
    this->memoriaOf->cambiarDatos(DTModOferta);
     
}
//Caso de uso Modificar Lamado -- SC -- Operacion elegir oferta laboral
void Sistema::elegirOfertaLaboral(string numero){
    IKey* k = new String(numero.c_str());
    OfertaLaboral* ofer = (OfertaLaboral*)ofertasLaborales->find(k);
    delete k;
    if (ofer == NULL){
        throw invalid_argument("No existe la oferta laboral solicitada");        
    }
    this->memoriaOf = ofer;
}


void Sistema::seleccionarEstudiante(string ciEst) {
    Date fechaActual = this->pedirfecha(); //fecha actual

    IKey * k = new String(ciEst.c_str());
    Estudiante * est = (Estudiante *)estudiantes->find(k);
    delete(k);

    this->memoriaOf->addPostulacion(est,fechaActual);
}


void Sistema::verEstudiante() {
    IIterator* it;
    for (it = this->estudiantes->getIterator();it->hasCurrent();it->next()){
        Estudiante * est = (Estudiante*)it->getCurrent();
        cout << "Cedula: " << est->getCi() << endl;
        cout << "Nombre: " << est->getNombre() << endl << endl;
    }
    delete it;
}

void Sistema::seleccEstudiante(string ci) {

    IKey* k = new String(ci.c_str());
    Estudiante* est = (Estudiante*)estudiantes->find(k);
    delete k;

    est->getDTDatosEstudiante();

    est->getNombresAsignaturas();
    
    est->getDTLlamados();
}

//Caso Modificar estudiante
void Sistema::modificarEstudiante(DtNuevoEstudiante DtNuevoEstudiante){

    if(DtNuevoEstudiante.getAgAsign() != NULL){
    	string asignatura= *DtNuevoEstudiante.getAgAsign();
    	IKey* key = new String(asignatura.c_str()) ;
	    Asignatura * asig = (Asignatura*)this->asignaturas->find(key);//ACA PROBLEM
	    this->memoriaEst->linkearAsignatura(asig);
	    delete key;       
    } 
          

    if(DtNuevoEstudiante.getAgCarr() != NULL){
        string carrera= *DtNuevoEstudiante.getAgCarr();
    
	    IKey* key = new String(carrera.c_str()) ;
	    Carrera * carr = (Carrera*)this->carreras->find(key);
	    this->memoriaEst->linkearCarrera(carr);
	    delete key;     
    }
    
    this->memoriaEst->modificarEstudiante(DtNuevoEstudiante);
}

//Modificar estudiante
void Sistema::selecEstudiante(string ci){   
	IKey* key = new String(ci.c_str()) ;
	Estudiante * est = (Estudiante*) estudiantes->find(key);
    cout << "LO ENCUENTRA" << endl;
	delete key; 
	this->memoriaEst = est;
}

void Sistema::eliminarOfertaLaboral(string numeroOferta) {
    IKey* key = new String(numeroOferta.c_str()) ;
    OfertaLaboral * ofertaLaboral = (OfertaLaboral*)ofertasLaborales-> find(key);

    //Me fijo si la cuenta existe y si existe la remuevo de la colección

        if(ofertaLaboral == NULL){
            delete key;
            throw invalid_argument("No existe la oferta laboral solicitada");
        }
    //Luego elimino oferta laboral de la coleccion oferta laboral
        this->ofertasLaborales->remove(key);
        ofertaLaboral->destruirOferta();
        delete ofertaLaboral;
        delete key;

}

void Sistema::cargarPrecarga(){
    this->asignaturas = this->cargarAsignaturas();
    this->carreras = this->cargarCarreras();
    this->ofertasLaborales = this->cargarOfertas();
    this->estudiantes = this->cargarPrecargaEstudiantes();
    this->empresas = Empresa::cargarPrecargaEmpresa();
    this->linkearPrecarga();
    this->linkearPrecargaOfertas1();
    this->linkearPrecargaOfertas2();
    this->linkearPrecargaOfertas3();
    this->linkearPrecargaOfertas4();
}

IDictionary* Sistema::cargarAsignaturas(){
    IKey* key;
    string codigo;

    IDictionary* asignaturas = new OrderedDictionary;
    Asignatura* A1 = new Asignatura( "Calculo I", "1686", 16 );
    codigo = "1686";
    key = new String(codigo.c_str());
    asignaturas->add(key,A1);

    Asignatura* A2 = new Asignatura("Programacion 1", "6598", 10);
    codigo = "6598";
    key = new String(codigo.c_str());
    asignaturas->add(key,A2);

    Asignatura* A3 = new Asignatura("Programacion 2", "5698", 12);
    codigo = "5698";
    key = new String(codigo.c_str());
    asignaturas->add(key,A3);

    Asignatura* A4 = new Asignatura("Programacion 3", "4875", 15);
    codigo = "4875";
    key = new String(codigo.c_str());
    asignaturas->add(key,A4);

    Asignatura* A5 = new Asignatura("Calculo II", "1689", 16);
    codigo = "1689";
    key = new String(codigo.c_str());
    asignaturas->add(key,A5);

    Asignatura* A6 = new Asignatura("Electromagnetismo", "8683", 12);
    codigo = "8683";
    key = new String(codigo.c_str());
    asignaturas->add(key,A6);

    Asignatura* A7 = new Asignatura("Bases de datos", "6943", 15);
    codigo = "6943";
    key = new String(codigo.c_str());
    asignaturas->add(key,A7);

    Asignatura* A8 = new Asignatura("Redes de computadoras", "6879", 12);
    codigo = "6879";
    key = new String(codigo.c_str());
    asignaturas->add(key,A8);

    Asignatura* A9 = new Asignatura("Ecuaciones diferenciales", "3216", 12);
    codigo = "3216";
    key = new String(codigo.c_str());
    asignaturas->add(key,A9);

    Asignatura* A10 = new Asignatura("Fundamentos de seguridad informatica", "9171", 12);
    codigo = "9171";
    key = new String(codigo.c_str());
    asignaturas->add(key,A10);

    Asignatura* A11 = new Asignatura("Robotica basada en comportamientos", "1698", 15);
    codigo = "1698";
    key = new String(codigo.c_str());
    asignaturas->add(key,A11);

    Asignatura* A12 = new Asignatura("Ciencia tecnologia y sociedad", "6416", 8);
    codigo = "6416";
    key = new String(codigo.c_str());
    asignaturas->add(key,A12);

    Asignatura* A13 = new Asignatura("Proyecto de Ingenieria de software", "6587", 15);
    codigo = "6587";
    key = new String(codigo.c_str());
    asignaturas->add(key,A13);

    Asignatura* A14 = new Asignatura("Arquitectura de computadoras", "5498", 12);
    codigo = "5498";
    key = new String(codigo.c_str());
    asignaturas->add(key,A14);

    Asignatura* A15 = new Asignatura("Taller de programacion", "1889", 15);
    codigo = "1889";
    key = new String(codigo.c_str());
    asignaturas->add(key,A15);

    return asignaturas;

}

IDictionary* Sistema::cargarOfertas(){

    Date fecha1(1, 6, 2015);
    Date fecha2(20, 7, 2024);
    Date fecha3(20, 5, 2015);
    Date fecha4(30, 6, 2024);
    Date fecha5(5, 6, 2015);
    Date fecha6(6, 8, 2024);
    Date fecha7(10, 9, 2015);
    Date fecha8(20, 12, 2024);

    IKey* key;
    string codigo;
    
    IDictionary* ofertas = new OrderedDictionary;

    OfertaLaboral* oferta1 = new OfertaLaboral("45896", "Auditor de seguridad part-time junior",
                          "Segurol S.A. busca estudiantes de Ingenieria en Computacion para unirse a su equipo. Se requiere un nivel minimo de conocimiento en seguridad informatica y programacion. Interesados enviar cv a oportunidades@segurol.com.uy",
                          20, 2000, 3000, fecha1,fecha2, 5, nullptr, nullptr, nullptr);
    codigo = "45896";
    key = new String(codigo.c_str());
    ofertas->add(key,oferta1);

    OfertaLaboral* oferta2 = new OfertaLaboral("12356", "Ayudante de Ingeniero",
                          "Estamos buscando estudiantes avanzados de Ingenieria Electrica con perfil potencia. Es imprescindible tener disponibilidad para viajar al interior del pais una vez por mes. Se pagan viaticos ademas de sueldo. Llamar al 25225323 int 1205 para mas detalles.",
                          30, 4000, 5000, fecha3, fecha4, 2, nullptr, nullptr, nullptr);
    codigo = "12356";
    key = new String(codigo.c_str());
    ofertas->add(key,oferta2);

    OfertaLaboral* oferta3 = new OfertaLaboral("88890", "Desarrollador C++",
                          "Buscamos desarrollador C++ para importante proyecto internacional. Llenar formulario con datos personales y cv en minisoft.uy/careers.",
                          40, 5000, 6000, fecha5, fecha6, 4, nullptr, nullptr, nullptr);
    codigo = "88890";
    key = new String(codigo.c_str());
    ofertas->add(key,oferta3);

    OfertaLaboral* oferta4 = new OfertaLaboral("49563", "Estudiantes para dictar clases de Calculo I y II",
                          "Buscamos estudiantes de Ingenieria con Calculo 1 y 2 aprobadas. Deben tener disponibilidad horaria y gusto por enseñar. Enviar mail a academiayotexplico@gmail.com.",
                          5, 500, 2000, fecha7, fecha8, 10, nullptr, nullptr, nullptr);
    codigo = "49563";
    key = new String(codigo.c_str());
    ofertas->add(key,oferta4);

    Asignatura* asig;
    //A2
    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta1->linkearAsignatura(asig);
    delete key;
    //A3
    codigo = "5698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta1->linkearAsignatura(asig);
    delete key;
    //A4
    codigo = "4875";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta1->linkearAsignatura(asig);
    delete key;
    //A10
    codigo = "9171";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta1->linkearAsignatura(asig);
    delete key;
    //A6
    codigo = "8683";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta2->linkearAsignatura(asig);
    delete key;
    //A9
    codigo = "3216";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta2->linkearAsignatura(asig);
    delete key;
    //A2
    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta3->linkearAsignatura(asig);
    delete key;
    //A3
    codigo = "5698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta3->linkearAsignatura(asig);
    delete key;
    //A4
    codigo = "4875";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta3->linkearAsignatura(asig);
    delete key;
    //A7
    codigo = "6943";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta3->linkearAsignatura(asig);
    delete key;
    //A13
    codigo = "6587";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta3->linkearAsignatura(asig);
    delete key;
    //A15
    codigo = "1889";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta3->linkearAsignatura(asig);
    delete key;
    //A1
    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta4->linkearAsignatura(asig);
    delete key;
    //A5
    codigo = "1689";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    oferta4->linkearAsignatura(asig);
    delete key;
   

    return ofertas;
 }

IDictionary* Sistema::cargarCarreras(){
    IKey* key;
    string codigo;

    IDictionary* carreras = new OrderedDictionary;
    Carrera* C1 = new Carrera("1010", "Ingenieria Electrica");
    codigo = "1010";
    key = new String(codigo.c_str());
    carreras->add(key,C1);

    Carrera* C2 = new Carrera("1011", "Ingenieria en Computacion" );
    codigo = "1011";
    key = new String(codigo.c_str());
    carreras->add(key,C2);

    return carreras;
}

void Sistema::linkearPrecarga(){
    IIterator* it;
    for (it = this->empresas->getIterator();it->hasCurrent();it->next()){
        Empresa * emp = (Empresa*)it->getCurrent();
        emp->linkearPrecarga();
    }
    delete it;
}

IDictionary* Sistema::cargarPrecargaEstudiantes(){

    Date fechaNac1(10, 2, 1990);
    Date fechaNac2(20, 8, 1992);
    Date fechaNac3(30, 1, 1980);
    Date fechaNac4(5, 6, 1975);
    Date fechaNac5(10, 10, 1969);
    Date fechaNac6(3, 1, 1994);
    Date fechaNac7(9, 3, 1993);
    Date fechaNac8(5, 8, 1992);
    Date fechaNac9(7, 9, 1990);
    Date fechaNac10(2, 9, 1990);
    Date fechaNac11(30, 3, 1984);
    Date fechaNac12(9, 12, 1983);

    IKey* key;
    string codigo;

    IDictionary* estudiantes = new OrderedDictionary;
    Carrera* carr;
    Asignatura* asig;


    Estudiante* ES1 = new Estudiante("4516231","Esteban_Perez", fechaNac1, "099111222", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "4516231";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES1);

    codigo = "1010";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES1->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES1->linkearAsignatura(asig);
    delete key;

    codigo = "1689";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES1->linkearAsignatura(asig);
    delete key;

    codigo = "8683";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES1->linkearAsignatura(asig);
    delete key;

    codigo = "3216";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES1->linkearAsignatura(asig);
    delete key;

    Estudiante* ES2 = new Estudiante("5111235","Felipe_Garcia", fechaNac2, "24035612", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "5111235";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES2);

    codigo = "1011";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES2->linkearCarrera(carr);
    delete key;

    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES2->linkearAsignatura(asig);
    delete key;

    codigo = "5698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES2->linkearAsignatura(asig);
    delete key;

    codigo = "4875";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES2->linkearAsignatura(asig);
    delete key;

    codigo = "6416";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES2->linkearAsignatura(asig);
    delete key;

    Estudiante* ES3 = new Estudiante("3594561", "Juan_Wolf", fechaNac3, "091222223", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "3594561";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES3);

    codigo = "1011";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES3->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "5698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "4875";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "1689";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "6943";
    key = new String(codigo.c_str());
    asig =(Asignatura*) this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "6879";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "6416";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "6587";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "5498";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    codigo = "1889";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES3->linkearAsignatura(asig);
    delete key;

    Estudiante* ES4 = new Estudiante("2784531", "Alfonsina_Ramirez", fechaNac4, "43712345", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "2784531";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES4);

    codigo = "1011";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES4->linkearCarrera(carr);
    delete key;

    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES4->linkearAsignatura(asig);
    delete key;

    codigo = "5698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES4->linkearAsignatura(asig);
    delete key;

    Estudiante* ES5 = new Estudiante("1956234", "Hector_Otonello", fechaNac5, "098334456", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "1956234";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES5);

    codigo = "1010";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES5->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES5->linkearAsignatura(asig);
    delete key;

    codigo = "1689";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES5->linkearAsignatura(asig);
    delete key;

    codigo = "3216";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES5->linkearAsignatura(asig);
    delete key;

    codigo = "6416";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES5->linkearAsignatura(asig);
    delete key;

    Estudiante* ES6 = new Estudiante("5005684", "Lorena_Nunez", fechaNac6, "092659878", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "5005684";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES6);

    codigo = "1010";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES6->linkearCarrera(carr);
    delete key;

    //no tiene asignaturas

    Estudiante* ES7 = new Estudiante("4686231", "Hector_Lorenzo", fechaNac7, "21656498", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "4686231";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES7);

    codigo = "1010";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES7->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES7->linkearAsignatura(asig);
    delete key;

    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES7->linkearAsignatura(asig);
    delete key;

    Estudiante* ES8 = new Estudiante("4987623", "Julio_Lee", fechaNac8, "26984899", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "4987623";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES8);

    codigo = "1011";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES8->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES8->linkearAsignatura(asig);
    delete key;

    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES8->linkearAsignatura(asig);
    delete key;

    codigo = "5698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES8->linkearAsignatura(asig);
    delete key;

    codigo = "4875";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES8->linkearAsignatura(asig);
    delete key;

    codigo = "6943";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES8->linkearAsignatura(asig);
    delete key;

    codigo = "6879";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES8->linkearAsignatura(asig);
    delete key;

    codigo = "1698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES8->linkearAsignatura(asig);
    delete key;

    codigo = "1889";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES8->linkearAsignatura(asig);
    delete key;

    Estudiante* ES9 = new Estudiante("4986313", "Rodrigo_Fernandez", fechaNac9, "22233346", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "4986313";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES9);

    codigo = "1011";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES9->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES9->linkearAsignatura(asig);
    delete key;

    Estudiante* ES10 = new Estudiante("3659532", "Noelia_Pereira", fechaNac10, "099112233", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "3659532";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES10);

    codigo = "1011";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES10->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES10->linkearAsignatura(asig);
    delete key;

    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES10->linkearAsignatura(asig);
    delete key;

    codigo = "5698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES10->linkearAsignatura(asig);
    delete key;

    Estudiante* ES11 = new Estudiante("3665492", "Cecilia_Garrido", fechaNac11, "094698568", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "3665492";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES11);

    codigo = "1011";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES11->linkearCarrera(carr);
    delete key;

    codigo = "1010";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES11->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES11->linkearAsignatura(asig);
    delete key;

    codigo = "6598";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES11->linkearAsignatura(asig);
    delete key;

    codigo = "5698";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES11->linkearAsignatura(asig);
    delete key;

    codigo = "4875";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES11->linkearAsignatura(asig);
    delete key;

    codigo = "1689";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES11->linkearAsignatura(asig);
    delete key;

    codigo = "8683";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES11->linkearAsignatura(asig);
    delete key;

    codigo = "6943";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES11->linkearAsignatura(asig);
    delete key;

    Estudiante* ES12 = new Estudiante("3335689", "Roman_Gul", fechaNac12, "096677889", 0, nullptr, nullptr, nullptr, NULL);
    codigo = "3335689";
    key = new String(codigo.c_str());
    estudiantes->add(key,ES12);

    codigo = "1010";
    key = new String(codigo.c_str());
    carr = (Carrera*)this->carreras->find(key);
    ES12->linkearCarrera(carr);
    delete key;

    codigo = "1686";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES12->linkearAsignatura(asig);
    delete key;

    codigo = "1689";
    key = new String(codigo.c_str());
    asig = (Asignatura*)this->asignaturas->find(key);
    ES12->linkearAsignatura(asig);
    delete key;

    return estudiantes;
}

void Sistema::linkearPrecargaOfertas1(){
    IKey* key;
    string codigo = "45896";
    key = new String(codigo.c_str());
    OfertaLaboral* of = (OfertaLaboral*)this->ofertasLaborales->find(key);
    delete key;

    codigo = "1112335684";
    key = new String(codigo.c_str());
    Empresa* emp = (Empresa*)this->empresas->find(key);
    emp->linkearPrecargaOfertas1(of);
    delete key;
}

void Sistema::linkearPrecargaOfertas2(){
    IKey* key;
    string codigo = "12356";
    key = new String(codigo.c_str());
    OfertaLaboral* of = (OfertaLaboral*)this->ofertasLaborales->find(key);
    delete key;

    codigo = "5464897986";
    key = new String(codigo.c_str());
    Empresa* emp = (Empresa*)this->empresas->find(key);
    emp->linkearPrecargaOfertas2(of);
    delete key;
}

void Sistema::linkearPrecargaOfertas3(){
    IKey* key;
    string codigo = "88890";
    key = new String(codigo.c_str());
    OfertaLaboral* of = (OfertaLaboral*)this->ofertasLaborales->find(key);
    delete key;

    codigo = "1265498765";
    key = new String(codigo.c_str());
    Empresa* emp = (Empresa*)this->empresas->find(key);
    emp->linkearPrecargaOfertas3(of);
    delete key;
}

void Sistema::linkearPrecargaOfertas4(){
    IKey* key;
    string codigo = "49563";
    key = new String(codigo.c_str());
    OfertaLaboral* of = (OfertaLaboral*)this->ofertasLaborales->find(key);
    delete key;

    codigo = "1326548654";
    key = new String(codigo.c_str());
    Empresa* emp = (Empresa*)this->empresas->find(key);
    emp->linkearPrecargaOfertas4(of);
    delete key;
}

bool Sistema::mismasAsignaturas(string estudiante){
    IKey* key = new String(estudiante.c_str());
    Estudiante* est = (Estudiante*)this->estudiantes->find(key);
    delete key;
    IDictionary* asE = est->getAsignaturas();
    IDictionary* asO = memoriaOf->getAsignaturas();
    IIterator* it;
    for (it = asO->getIterator();it->hasCurrent();it->next()){
        Asignatura * as = (Asignatura*)it->getCurrent();
        key = new String((as->getCodigo()).c_str());
        as = (Asignatura*)asE->find(key);
        if (as == NULL){
            delete it;
            return false;
        }
    }   
    delete it;
    return true;
}