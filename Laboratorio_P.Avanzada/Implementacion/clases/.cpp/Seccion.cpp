#include "../.h/Seccion.h"


Seccion::Seccion(string nombre,string telefono, Sucursal* miSucursal, IDictionary* ofertas){
    this->nombre = nombre;
    this->telefono = telefono;
    this->miSucursal = miSucursal;
    if (ofertas != NULL)
        this->ofertas = ofertas;
    else 
        this->ofertas = new OrderedDictionary;
}

string Seccion::getNombre(){
    return this->nombre;
}

string Seccion::getTelefono(){
    return this->telefono;
}

Sucursal* Seccion::getSucursal(){
    return this->miSucursal;
}

IDictionary* Seccion::getOfertas(){
    return this->ofertas;
}

void Seccion::setNombre(string nombre){
    this->nombre = nombre;
}

void Seccion::setTelefono(string telefono){
    this->telefono = telefono;
}

void Seccion::setSucursal(Sucursal* miSucursal){
    this->miSucursal = miSucursal;
}

void Seccion::setOfertas(IDictionary* ofertas){
    this->ofertas = ofertas;
}

Seccion::~Seccion(){}

void Seccion::linkSeccionOferta(OfertaLaboral* of){
    string codigo = of->getNumero();
    IKey* key = new String(codigo.c_str());
    ofertas->add(key,of);
    of->linkearOfertaSeccion(this);
}
void Seccion::getNombreEmpresa(){
    this->miSucursal->getNombreEmpresa();
}
void Seccion::getDireccionSucursal(){
    cout << "Direccion: " << this->miSucursal->getDireccion() << endl;
}

void Seccion::getDTSeccion() {

    cout << "Nombre seccion: " << this->getNombre() << endl;
    cout << "Telefono seccion: " << this->getTelefono() << endl;

    this->miSucursal->getDTSucursal();
}

void Seccion::desvincularSeccionOferta(string oferta){
    IKey* key = new String(oferta.c_str()) ;
    ofertas->remove(key);
    delete key;
}

IDictionary* Seccion::cargarPrecargaSeccion1(){
    IKey* key;
    string codigo;

    IDictionary* secciones1 = new OrderedDictionary;
    Seccion* seccion1 = new Seccion("Contaduria", "101", NULL, nullptr);
    codigo = "Contaduria";
    key = new String(codigo.c_str());
    secciones1->add(key,seccion1);

    Seccion* seccion2 = new Seccion("Recursos humanos", "102", NULL, nullptr);
    codigo = "Recursos humanos";
    key = new String(codigo.c_str());
    secciones1->add(key,seccion2);

    Seccion* seccion3 = new Seccion("Recepcion", "103", NULL, nullptr);
    codigo = "Recepcion";
    key = new String(codigo.c_str());
    secciones1->add(key,seccion3);

    Seccion* seccion4 = new Seccion("Desarrollo", "104", NULL, nullptr);
    codigo = "Desarrollo";
    key = new String(codigo.c_str());
    secciones1->add(key,seccion4);

    Seccion* seccion5 = new Seccion("Pentesting", "105", NULL, nullptr);
    codigo = "Pentesting";
    key = new String(codigo.c_str());
    secciones1->add(key,seccion5);

    return secciones1;
}

IDictionary* Seccion::cargarPrecargaSeccion2(){
    IKey* key;
    string codigo;

    IDictionary* secciones2 = new OrderedDictionary;
    Seccion* seccion6 = new Seccion("Marketing", "1201", NULL, nullptr);
    codigo = "Marketing";
    key = new String(codigo.c_str());
    secciones2->add(key,seccion6);

    Seccion* seccion7 = new Seccion("Atencion al cliente", "1202", NULL, nullptr);
    codigo = "Atencion al cliente";
    key = new String(codigo.c_str());
    secciones2->add(key,seccion7);

    Seccion* seccion8 = new Seccion("Tesoreria", "1203", NULL, nullptr);
    codigo = "Tesoreria";
    key = new String(codigo.c_str());
    secciones2->add(key,seccion8);

    Seccion* seccion9 = new Seccion("Ventas", "1204", NULL, nullptr);
    codigo = "Ventas";
    key = new String(codigo.c_str());
    secciones2->add(key,seccion9);

    Seccion* seccion10 = new Seccion("Seccion tecnica", "1205", NULL, nullptr);
    codigo = "Seccion tecnica";
    key = new String(codigo.c_str());
    secciones2->add(key,seccion10);

    return secciones2;
}

IDictionary* Seccion::cargarPrecargaSeccion3(){
    IKey* key;
    string codigo;

    IDictionary* secciones3 = new OrderedDictionary;
    Seccion* seccion11 = new Seccion("Seccion tecnica", "1009", NULL, nullptr);
    codigo = "Seccion tecnica";
    key = new String(codigo.c_str());
    secciones3->add(key,seccion11);

    Seccion* seccion12 = new Seccion("I+D", "1008", NULL, nullptr);
    codigo = "I+D";
    key = new String(codigo.c_str());
    secciones3->add(key,seccion12);

    Seccion* seccion13 = new Seccion("Recepcion", "1000", NULL, nullptr);
    codigo = "Recepcion";
    key = new String(codigo.c_str());
    secciones3->add(key,seccion13);

    return secciones3;
}

IDictionary* Seccion::cargarPrecargaSeccion4(){
    IKey* key;
    string codigo;

    IDictionary* secciones4 = new OrderedDictionary;
    Seccion* seccion14 = new Seccion("Secretaria", "100", NULL, nullptr);
    codigo = "Secretaria";   
    key = new String(codigo.c_str());
    secciones4->add(key,seccion14); 

    Seccion* seccion15 = new Seccion("Desarrollo", "1001", NULL, nullptr);
    codigo = "Desarrollo";
    key = new String(codigo.c_str());
    secciones4->add(key,seccion15);

    Seccion* seccion16 = new Seccion("Testing", "1002", NULL, nullptr);
    codigo = "Testing";
    key = new String(codigo.c_str());
    secciones4->add(key,seccion16);

    return secciones4;
}

IDictionary* Seccion::cargarPrecargaSeccion5(){
    IKey* key;
    string codigo;

    IDictionary* secciones5 = new OrderedDictionary;
    Seccion* seccion17 = new Seccion("Secretaria", "100", NULL, nullptr);
    codigo = "Secretaria";
    key = new String(codigo.c_str());
    secciones5->add(key,seccion17);

    Seccion* seccion18 = new Seccion("Desarrollo", "1001", NULL, nullptr);
    codigo = "Desarrollo";
    key = new String(codigo.c_str());
    secciones5->add(key,seccion18);

    Seccion* seccion19 = new Seccion("Testing", "1002", NULL, nullptr);
    codigo = "Testing";
    key = new String(codigo.c_str());
    secciones5->add(key,seccion19);

    return secciones5;
}

IDictionary* Seccion::cargarPrecargaSeccion6(){
    IKey* key;
    string codigo;

    IDictionary* secciones6 = new OrderedDictionary;
    Seccion* seccion20 = new Seccion("Laboratorio", "10", NULL, nullptr);
    codigo = "Laboratorio";
    key = new String(codigo.c_str());
    secciones6->add(key,seccion20);

    Seccion* seccion21 = new Seccion("Atencion comercial", "11", NULL, nullptr);
    codigo = "Atencion comercial";
    key = new String(codigo.c_str());
    secciones6->add(key,seccion21);

    return secciones6;
}

IDictionary* Seccion::cargarPrecargaSeccion7(){
    IKey* key;
    string codigo;

    IDictionary* secciones7 = new OrderedDictionary;
    Seccion* seccion22 = new Seccion("Direccion", "101", NULL, nullptr);
    codigo = "Direccion";   
    key = new String(codigo.c_str());
    secciones7->add(key,seccion22);

    Seccion* seccion23 = new Seccion("Inscripciones", "102", NULL, nullptr);
    codigo = "Inscripciones";
    key = new String(codigo.c_str());
    secciones7->add(key,seccion23);

    return secciones7;
}

void Seccion::linkearPrecarga(Sucursal* suc){
    this->setSucursal(suc);
}

void Seccion::linkearPrecargaOfertas(OfertaLaboral* of){
    string codigo = of->getNumero();
    IKey* key = new String(codigo.c_str());
    this->ofertas->add(key,of);
    of->linkearOfertaSeccion(this);
}