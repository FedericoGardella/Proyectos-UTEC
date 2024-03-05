#include "../.h/Sucursal.h"


Sucursal::Sucursal(string nombre,string telefono, string direccion, IDictionary* Secciones, Empresa* miEmpresa){
    this->nombre = nombre;
    this->telefono = telefono;
    this->direccion = direccion;
    if (Secciones != NULL)
        this->secciones = Secciones;
    else
        this->secciones = new OrderedDictionary;
    this->miEmpresa = miEmpresa;
}

string Sucursal::getNombre(){
    return this->nombre;
}

string Sucursal::getTelefono(){
    return this->telefono;
}

string Sucursal::getDireccion(){
    return this->direccion;
}

IDictionary* Sucursal::getSecciones(){
    return this->secciones;
}

Empresa* Sucursal::getEmpresa(){
    return this->miEmpresa;
}

void Sucursal::setNombre(string nombre){
    this->nombre = nombre;
}

void Sucursal::setTelefono(string telefono){
    this->telefono = telefono;
}

void Sucursal::setDireccion(string direccion){
    this->direccion = direccion;
}

void Sucursal::setSeccion(IDictionary* Secciones){
    this->secciones = Secciones;
}

void Sucursal::setEmpresa(Empresa* empresa){
    this->miEmpresa = empresa;
}

Sucursal::~Sucursal(){}

void Sucursal::getNombreSecciones(){
    IIterator* it;
    for (it= this->secciones->getIterator();it->hasCurrent();it -> next()){
        Seccion * sec = (Seccion*)it->getCurrent();
        cout << "Nombre:" << sec->getNombre() << endl << endl;
    }
    delete it;
}

void Sucursal::linkearSeccionOferta(OfertaLaboral* of, string seccion){
    IKey* k = new String(seccion.c_str());
    Seccion* sec = (Seccion*)secciones->find(k);
    delete k;
    sec->linkSeccionOferta(of);
}

void Sucursal::getNombreEmpresa(){
    cout << "Empresa: " << this->miEmpresa->getNombre() << endl;
}


void Sucursal::getDTSucursal() {

    cout << "Nombre sucursal: " << this->getNombre() << endl;
    cout << "Telefono sucursal: " << this->getTelefono() << endl;
    cout << "Direccion sucursal: " << this->getDireccion() << endl;

    this->miEmpresa->getDTEmpresa();
}

IDictionary* Sucursal::cargarPrecargaSucursal1(){
    IKey* key;
    string codigo;

    IDictionary* sucursales1 = new OrderedDictionary;
    Sucursal* sucursal1 = new Sucursal("Casa central", "24598765", "Ciudad Vieja", Seccion::cargarPrecargaSeccion1(), NULL);
    codigo = "Casa central";
    key = new String(codigo.c_str());
    sucursales1->add(key,sucursal1);

    return sucursales1;
}

IDictionary* Sucursal::cargarPrecargaSucursal2(){
    IKey* key;
    string codigo;

    IDictionary* sucursales2 = new OrderedDictionary;
    Sucursal* sucursal2 = new Sucursal("Sucursal comercial", "25225323", "Malvin", Seccion::cargarPrecargaSeccion2(), NULL);
    codigo = "Sucursal comercial";
    key = new String(codigo.c_str());
    sucursales2->add(key,sucursal2);

    Sucursal* sucursal3 = new Sucursal("Sucursal San Carlos", "42668350", "San Carlos", Seccion::cargarPrecargaSeccion3(), NULL);
    codigo = "Sucursal San Carlos";
    key = new String(codigo.c_str());
    sucursales2->add(key,sucursal3);

    return sucursales2;
}

IDictionary* Sucursal::cargarPrecargaSucursal3(){
    IKey* key;
    string codigo;

    IDictionary* sucursales3 = new OrderedDictionary;
    Sucursal* sucursal4 = new Sucursal("Sede Montevideo", "25468932", "Buceo", Seccion::cargarPrecargaSeccion4(), NULL);
    codigo = "Sede Montevideo";
    key = new String(codigo.c_str());
    sucursales3->add(key,sucursal4);

    Sucursal* sucursal5 = new Sucursal("Sede Ciudad de la Costa", "43764232", "Solymar", Seccion::cargarPrecargaSeccion5(), NULL);
    codigo = "Sede Ciudad de la Costa";
    key = new String(codigo.c_str());
    sucursales3->add(key,sucursal5);

    return sucursales3;
}

IDictionary* Sucursal::cargarPrecargaSucursal4(){
    IKey* key;
    string codigo;

    IDictionary* sucursales4 = new OrderedDictionary;
    Sucursal* sucursal6 = new Sucursal("Oficina central", "25495878", "Centro", Seccion::cargarPrecargaSeccion6(), NULL);
    codigo = "Oficina central";
    key = new String(codigo.c_str());
    sucursales4->add(key,sucursal6);

    return sucursales4;
}

IDictionary* Sucursal::cargarPrecargaSucursal5(){
    IKey* key;
    string codigo;

    IDictionary* sucursales5 = new OrderedDictionary;
    Sucursal* sucursal7 = new Sucursal("Academia", "24594565", "Parque Rodo", Seccion::cargarPrecargaSeccion7(), NULL);
    codigo = "Academia";
    key = new String(codigo.c_str());
    sucursales5->add(key,sucursal7);

    return sucursales5;
}

void Sucursal::linkearPrecarga(Empresa* emp){
    this->setEmpresa(emp);
    IIterator* it;
    for (it = this->secciones->getIterator();it->hasCurrent();it->next()){
        Seccion * sec = (Seccion*)it->getCurrent();
        sec->linkearPrecarga(this);
    }
    delete it;
}

void Sucursal::linkearPrecargaOfertas1(OfertaLaboral* of){
    IKey* key;
    string codigo = "Recursos humanos";
    key = new String(codigo.c_str());
    Seccion* sec = (Seccion*)this->secciones->find(key);
    sec->linkearPrecargaOfertas(of);
    delete key;
}

void Sucursal::linkearPrecargaOfertas2(OfertaLaboral* of){
    IKey* key;
    string codigo = "Seccion tecnica";
    key = new String(codigo.c_str());
    Seccion* sec = (Seccion*)this->secciones->find(key);
    sec->linkearPrecargaOfertas(of);
    delete key;
}

void Sucursal::linkearPrecargaOfertas3(OfertaLaboral* of){
    IKey* key;
    string codigo = "Desarrollo";
    key = new String(codigo.c_str());
    Seccion* sec = (Seccion*)this->secciones->find(key);
    sec->linkearPrecargaOfertas(of);
    delete key;
}

void Sucursal::linkearPrecargaOfertas4(OfertaLaboral* of){
    IKey* key;
    string codigo = "Direccion";
    key = new String(codigo.c_str());
    Seccion* sec = (Seccion*)this->secciones->find(key);
    sec->linkearPrecargaOfertas(of);
    delete key;
}