#include "../.h/Empresa.h"


Empresa::Empresa(string rut,string nombre, IDictionary* Sucursales){
    this->rut = rut;
    this->nombre = nombre;
    if (Sucursales != NULL)
        this->sucursales = Sucursales;
    else 
        this->sucursales = new OrderedDictionary;
}


string Empresa::getRut(){
    return this->rut;
}

string Empresa::getNombre(){
    return this->nombre;
}

IDictionary* Empresa::getSucursales(){
    return this->sucursales;
}

void Empresa::setRut(string rut){
    this->rut = rut;
}

void Empresa::setNombre(string nombre){
    this->nombre = nombre;
}

void Empresa::setSucursales(IDictionary* Sucursales){
    this->sucursales = Sucursales;
}

Empresa::~Empresa(){
    delete sucursales;
}

//Implementaciones

DtEmpresa Empresa::getDtEmpresa(){
    DtEmpresa datos(this->rut, this->nombre);
    return datos;
}

void Empresa::getNombreSucursales(){
    IIterator * it;
    Sucursal* suc;
    for(it = sucursales->getIterator(); it->hasCurrent(); it->next()){
        suc = (Sucursal *)it->getCurrent();
        cout << "Nombre:" << suc->getNombre() << endl;
    }
    delete it;
}

void Empresa::seleccionarSucursal(string sucursal){
    IKey* key = new String(sucursal.c_str()) ;
    Sucursal * suc = (Sucursal*)sucursales->find(key);
    suc->getNombreSecciones();
    delete key;
}
void Empresa::linkearSeccionOferta(OfertaLaboral* of, string sucursal, string seccion){
    IKey* k = new String(sucursal.c_str());
    Sucursal* suc = (Sucursal*)sucursales->find(k);
    delete k;
    suc->linkearSeccionOferta(of,seccion);
}

void Empresa::getDTEmpresa() {

    cout << "RUT empresa: " << this->getRut() << endl;
    cout << "Nombre empresa: " << this->getNombre() << endl;
}

IDictionary* Empresa::cargarPrecargaEmpresa(){
    IKey* key;
    string codigo;
    
    IDictionary* empresas = new OrderedDictionary;
    Empresa* empresa1 = new Empresa("1112335684", "Segurol S.A.", Sucursal::cargarPrecargaSucursal1());
    codigo = "1112335684";
    key = new String(codigo.c_str());
    empresas->add(key,empresa1);

    Empresa* empresa2 = new Empresa("5464897986", "Ingenieros Electricos Unidos", Sucursal::cargarPrecargaSucursal2());
    codigo = "5464897986";
    key = new String(codigo.c_str());
    empresas->add(key,empresa2);

    Empresa* empresa3 = new Empresa("1265498765", "MiniSoft Uy", Sucursal::cargarPrecargaSucursal3());
    codigo = "1265498765";
    key = new String(codigo.c_str());
    empresas->add(key,empresa3);

    Empresa* empresa4 = new Empresa("1298865497", "RoboTI", Sucursal::cargarPrecargaSucursal4());
    codigo = "1298865497";
    key = new String(codigo.c_str());
    empresas->add(key,empresa4);

    Empresa* empresa5 = new Empresa("1326548654", "Academia Yotexplico", Sucursal::cargarPrecargaSucursal5());
    codigo = "1326548654";
    key = new String(codigo.c_str());
    empresas->add(key,empresa5);

    return empresas;
}

void Empresa::linkearPrecarga(){
    IIterator* it;
    for (it = this->sucursales->getIterator();it->hasCurrent();it->next()){
        Sucursal * suc = (Sucursal*)it->getCurrent();
        suc->linkearPrecarga(this);
    }
    delete it;
}

void Empresa::linkearPrecargaOfertas1(OfertaLaboral* of){
    IKey* key;
    string codigo = "Casa central";
    key = new String(codigo.c_str());
    Sucursal* suc = (Sucursal*)this->sucursales->find(key);
    suc->linkearPrecargaOfertas1(of);
    delete key;
}

void Empresa::linkearPrecargaOfertas2(OfertaLaboral* of){
    IKey* key;
    string codigo = "Sucursal comercial";
    key = new String(codigo.c_str());
    Sucursal* suc = (Sucursal*)this->sucursales->find(key);
    suc->linkearPrecargaOfertas2(of);
    delete key;
}

void Empresa::linkearPrecargaOfertas3(OfertaLaboral* of){
    IKey* key;
    string codigo = "Sede Montevideo";
    key = new String(codigo.c_str());
    Sucursal* suc = (Sucursal*)this->sucursales->find(key);
    suc->linkearPrecargaOfertas3(of);
    delete key;
}

void Empresa::linkearPrecargaOfertas4(OfertaLaboral* of){
    IKey* key;
    string codigo = "Academia";
    key = new String(codigo.c_str());
    Sucursal* suc = (Sucursal*)this->sucursales->find(key);
    suc->linkearPrecargaOfertas4(of);
    delete key;
}