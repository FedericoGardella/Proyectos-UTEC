
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>

using namespace std;

//********************ENUMERADOS*********************

enum calificador
{
    PRIMARY_KEY,
    NOT_EMPTY,
    ANY
};
typedef calificador TipoCalificador;

enum tipo_de_variable
{
    INTEGERS,
    STRINGS,
};
typedef tipo_de_variable TipoDeDato;

//********************ESTRUCTURAS********************

//DE LISTA DE INTEGERS
struct nodoListaInt{
    int * info;
    nodoListaInt * sig;
};

typedef nodoListaInt * ListaInt;

//DE LISTA DE STRINGS
struct nodoListaStr{
    string * info;
    nodoListaStr * sig;
};

typedef nodoListaStr * ListaStr;

//DE LISTA DE COLUMNAS
struct nodoColumna{
    string nomc;
    TipoCalificador calif;
    TipoDeDato tipo;
    ListaInt li;
    ListaStr ls;
    nodoColumna * sig;
};

typedef nodoColumna * ColumnaP;

//DE ARBOL DE TABLAS
struct nodoTablaABB{
    string nomt;
    ColumnaP col;
    nodoTablaABB * izq;
    nodoTablaABB * der;  
};

typedef nodoTablaABB *TablaABB;

//********************DECLARACIONES DE FUNCIONES*********************

//SOLICITADAS 

void createTable(string nom);
void dropCol(string nomt,string nomc);
void insertInto (string nombreTabla, string columnasTupla, string valoresTupla);
void printTables();
void addCol(string nomTab, string nomCol, TipoDeDato tip, TipoCalificador cal);
void alterCol(string nomtab, string nomcol, TipoDeDato nuevotipo, TipoCalificador nuevocal, string nuevcol);
void delet(string nomtab, string condicion);
void printMetadata(string nom);

//AUXILIARES

TablaABB crearTabla(TablaABB &T, string nom);
TablaABB buscarTabla(TablaABB T, string nom);
void eliminarTupla(TablaABB T, int altura);
ColumnaP buscarColumna(ColumnaP C, string nom);
void impNomTabla(TablaABB a);
bool esInt(string& str);
int contarCol (ColumnaP col);

//**********************VARIABLES GLOBALES*************************

TablaABB tablas = NULL;

//*******************IMPLEMENTACION DE FUNCIONES********************


TablaABB crearTabla(TablaABB &T, string nom){
    if (T == NULL){
        T = new nodoTablaABB;
        T->nomt = nom;
        T->izq = T->der = NULL;
        T->col = NULL;
        return T;
    }
    if (T->nomt == nom){
        return NULL;
    }
    if (T->nomt > nom)
        return crearTabla(T->izq, nom);
    else
        return crearTabla(T->der, nom);
}

void createTable(string nom){
    if (tablas == NULL){
        tablas = new nodoTablaABB;
        tablas->nomt = nom;
        tablas->izq = tablas->der = NULL;
        tablas->col = NULL;
    }
    else{
        TablaABB nueva = NULL;
        if (nom > tablas->nomt)
            nueva = crearTabla(tablas->der,nom);

        else{
            if (nom < tablas->nomt)
                nueva = crearTabla(tablas->izq,nom);
        }
        if (nueva == NULL)
            cout << "Ya existe una tabla con ese nombre" << endl;
    } 
}

TablaABB buscarTabla(TablaABB T, string nom){
    if (T == NULL)
        return NULL;
    if (T->nomt == nom)
        return T;
    if (T->nomt > nom)
        return buscarTabla(T->izq, nom);
    return buscarTabla(T->der, nom);
}

ColumnaP buscarColumna(ColumnaP C, string nom){
    if (C == NULL)
        return NULL;
    if (C->nomc == nom)
        return C;
    return buscarColumna(C->sig, nom);
}

void addCol(string nomTab, string nomCol, TipoDeDato tip, TipoCalificador cal){
    TablaABB T = buscarTabla(tablas,nomTab);
    if (T == NULL){
        cout << "No existe una tabla con ese nombre" << endl;
        return;
    }
    bool pk = false;
    if (T->col != NULL && (T->col->li != NULL || T->col->ls != NULL) && (cal == 1 || cal == 0)){
        cout << "No se permite calificador distinto de ANY ya que la tabla contiene tuplas" << endl;
        return;
    }
    ColumnaP aux = T->col;
    while (aux != NULL && aux->sig != NULL && aux->nomc != nomCol){
        if (aux->calif == 0)
            pk = true;
        aux = aux->sig;
    }
    if (pk && cal == 0){
        cout << "Ya existe una columna con el calificador PRIMARY KEY" << endl;
        return;
    }
    if (aux->nomc == nomCol){
        cout << "Ya existe una columna con ese nombre" << endl;
        return;
    }
    ColumnaP nueva = new nodoColumna;
    nueva->nomc = nomCol;
    nueva->calif = cal;
    nueva->sig = NULL;
    nueva->tipo = tip;
    if (aux == NULL){
        T->col = nueva;
        nueva->ls = NULL;
        nueva->li = NULL;
        return;
    }
    int cont = 0;
    if (aux->tipo == 0){
        ListaInt a = aux->li;
        while (a != NULL){
            cont++;
            a = a->sig;
        }
    }
    else{
        ListaStr a = aux->ls;
        while (a != NULL){
            cont++;
            a = a->sig; 
        }
    }
    aux->sig = nueva;
    aux = nueva;
    if (tip == 0 && cont != 0){
        aux->li = new nodoListaInt;
        ListaInt celda = aux->li;
        celda->info = NULL;
        cont--;
        while (cont > 0){
            celda->sig = new nodoListaInt;
            celda = celda->sig;
            celda->info = NULL;
        }
        celda->sig = NULL;
        return;
    }
    if (tip == 1 && cont != 0){
        aux->ls = new nodoListaStr;
        ListaStr celda = aux->ls;
        celda->info = NULL;
        cont--;
        while (cont > 0){
            celda->sig = new nodoListaStr;
            celda = celda->sig;
            celda->info = NULL;
        }
        celda->sig = NULL;
        return;
    }   
}

void dropCol (string nombreTabla, string nombreCol) {
    TablaABB tablaP = buscarTabla(tablas,nombreTabla); //verificar que la tabla exista
    if (tablaP == NULL) {
        cout<<"La tabla no existe"<<endl;
        return;
    }
    ColumnaP elim = buscarColumna(tablaP->col,nombreCol); //verificar que la columna exista
    if (elim == NULL) {
        cout<<"La columna no existe"<<endl;
        return;
    }
    if (tablaP->col == elim)       //enganchar todo correctamente
        tablaP->col = elim->sig;
    else {
        ColumnaP aux = tablaP->col;
        while (aux->sig != elim)
            aux = aux->sig;
        aux->sig = elim->sig;
    }

    if (elim->tipo == 0) {      //comenzar a eliminar
            ListaInt borrar;
            while (elim->li != NULL) {
                borrar = elim->li;
                elim->li = elim->li->sig;
                if (borrar->info != NULL)
                    delete borrar->info;
                delete borrar;                
            }
        } else {
            ListaStr borrar;
            while (elim->ls != NULL) {
                borrar = elim->ls;
                elim->ls = elim->ls->sig;
                if (borrar->info != NULL)
                    delete borrar->info;
                delete borrar;                
            }
        }
        delete elim;       
}


void alterCol(string nomtab, string nomcol, TipoDeDato nuevotipo, TipoCalificador nuevocal, string nuevcol){
    TablaABB auxtab = buscarTabla(tablas, nomtab);// Nos fijamos si existe esa tabla
    if (auxtab == NULL){
        cout << "No existe esa tabla" << "\n";
        return;
    }
    ColumnaP auxcol = buscarColumna(auxtab->col, nuevcol);// Nos fijamos si ya existe una columna con el nuevo nombre
    if (auxcol != NULL){
        cout << "Ya existe una columna con ese nombre" << "\n";
        return;
    }
    auxcol = buscarColumna(auxtab->col, nomcol);// Nos fijamos si existe una columna con el nombre que buscamos
    if (auxcol == NULL){
        cout << "No existe esa columna" << "\n";
        return;
    }
    if (auxcol->tipo == 1 && nuevotipo == 0){// Nos fijamos si quieren pasar strings a integers
        cout << "No se puede cambiar strings a integers" << "\n";
        return;
    }
    if (nuevocal == 0 && auxcol->calif != 0){//Nos fijamos si ya existe Primary_key en caso de querer poner una columna de ese tipo
        bool pk = false;
        ColumnaP pksearch = auxtab->col;
        while (pksearch != NULL){
            if (pksearch->calif == 0)
                pk = true;
            pksearch = pksearch->sig;
        }
        if (pk){
            cout << "Ya hay una columna PRIMARY_KEY" << "\n";
            return; 
        }
    }
    auxcol->nomc = nuevcol;
    auxcol->calif = nuevocal;
    if (auxcol->tipo == 0 && nuevotipo == 1){
        if (auxcol->li != NULL){
            auxcol->ls = new nodoListaStr;
            ListaStr str = auxcol->ls;
            ListaInt integ = auxcol->li;
            str->info = new string;
            *str->info = to_string(*integ->info);
            integ = integ->sig;
            while (integ != NULL){// Cambiamos todos los datos a strings
                str->sig = new nodoListaStr;
                str = str->sig;
                str->info = new string;
                *str->info = to_string(*integ->info);
                integ = integ->sig;
            }
            str->sig = NULL;
            ListaInt borrador = auxcol->li;
            while (auxcol->li != NULL){// Borramos la lista de integers
                auxcol->li = borrador->sig;
                delete borrador->info;
                delete borrador;
                borrador = auxcol->li;
            }
        }
    }

}

bool esInt(string& str)
{
    for (char const &c : str) {
        if (isdigit(c) == 0)
        return false;
    }
    return true;
}

int contarCol (ColumnaP col) {
    int res = 0;
    while (col != NULL) {
        col = col->sig;
        res++;
    }
    return res;
}

void insertInto (string nombreTabla, string columnasTupla, string valoresTupla) {
    TablaABB tablaP = buscarTabla(tablas,nombreTabla);
    if (tablaP == NULL) {
        cout<<"La tabla no existe"<<endl;
        return;
    }

    int cantColumnas = 0, cantValores = 0;
    for (int i = 0; i < columnasTupla.size(); i++)
        if (columnasTupla[i] == ':')
            cantColumnas++;
    for (int i = 0; i < valoresTupla.size(); i++)
        if (valoresTupla[i] == ':')
            cantValores++;
    if (cantColumnas != cantValores) {            //ver que la cantidad de datos ingresados coincida
        cout<<"La cantidad de columnas y valores no coinciden"<<endl;
        return;
    }

    string arrCol[cantColumnas+1];        //separar datos y guardarlos en array
    int cont = 0;
    string lectura;                                             
    
    stringstream input_stringstream(columnasTupla);                    
    while (getline(input_stringstream, lectura, ':'))
    {
        arrCol[cont] = lectura;
        cont++;
    }

    string arrVal[cantValores];
    cont = 0;
    
    stringstream input_stringstream2(valoresTupla);                    
    while (getline(input_stringstream2, lectura, ':'))
    {
        arrVal[cont] = lectura;
        cont++;
    }

    ColumnaP aux = tablaP->col;   //verificar que las columnas a agregar existan
    bool esta = true;
    bool mismoTipo = false;
    int i = 0;
    while (esta != false && i <= cantColumnas) {
        aux = tablaP->col;
        esta = false;
        while (aux != NULL && esta != true) {
            esta = (aux->nomc == arrCol[i]);
            if (esta) {                                //verificar que el tipo de la columna y el valor sea el mismo
                if (aux->tipo == 0)
                    mismoTipo = esInt(arrVal[i]);
                else
                    mismoTipo = !esInt(arrVal[i]);
                if (mismoTipo == false) {
                    cout<<"El tipo de una columna no coincide con el de su valor";
                    return;
                }
            }
            aux = aux->sig;
        }
        i++;
    }
    if (esta == false) {
        cout<<"Se nombraron columnas que no existen";
        return;
    }

    if (cantColumnas+1 < contarCol(tablaP->col)) { //verificar que si habra valores NULL, estos esten permitidos
        i = 0;
        esta = false;
        aux = tablaP->col;
        while (aux != NULL) { 
            while (esta == false && i <= cantColumnas) {
                esta = (aux->nomc == arrCol[i]);
                i++;            
            }
            if (!esta)
                if (aux->calif != 2) {
                    cout<<"Se pide agregar un valor nulo en una columna que no lo permite";
                    return;
                }
            aux = aux->sig;
        }
    }
   
    aux = tablaP->col;
    esta = false;
    i = 0;
    int enteros[cantValores+1];
    ListaInt agregarInt;
    ListaStr agregarStr;
    while (aux != NULL) {        //agregar tupla
        while (i <= cantColumnas && aux->nomc != arrCol[i])
            i++;
        if (aux->nomc != arrCol[i]) {
            if (aux->tipo == 0) {
                agregarInt = new nodoListaInt;
                agregarInt->info = NULL;
                agregarInt->sig = aux->li;
                aux->li = agregarInt;
            }
            else {
                agregarStr = new nodoListaStr;
                agregarStr->info = NULL;
                agregarStr->sig = aux->ls;
                aux->ls = agregarStr;
            }
        }
        else {
            if (aux->tipo == 0) {
                enteros[i] = stoi(arrVal[i]);
                agregarInt = new nodoListaInt;
                agregarInt->info = &enteros[i];
                agregarInt->sig = aux->li;
                aux->li = agregarInt;
            }
            else {                
                agregarStr = new nodoListaStr;
                agregarStr->info = &arrVal[i];
                agregarStr->sig = aux->ls;
                aux->ls = agregarStr;
            }
        }
        aux = aux->sig;       
    }
}

void eliminarTupla(TablaABB T, int altura){//Precondicion: T != NULL && altura > 0
    ListaInt auxi, borrari;
    ListaStr auxs, borrars;
    ColumnaP auxcol = T->col;
    int cont;
    while (auxcol != NULL){
        if (auxcol->tipo == 0){
            if(altura == 1){
                borrari = auxcol->li;
                auxcol->li = borrari->sig;
                if (borrari->info != NULL)
                    delete borrari->info;
                delete borrari;
            }
            else{
                auxi = auxcol->li;
                cont = 2;
                while (cont < altura){
                    auxi = auxi->sig;
                    cont++;
                }
                borrari = auxi->sig;
                auxi->sig = borrari->sig;
                if(borrari->info != NULL)
                    delete borrari->info;
                    delete borrari;
            }
        }
        if (auxcol->tipo == 1){
            if(altura == 1){
                borrars = auxcol->ls;
                auxcol->ls = borrars->sig;
                if (borrars->info != NULL)
                    delete borrars->info;
                delete borrars;
            }
            else{
                auxs = auxcol->ls;
                cont = 2;
                while (cont < altura){
                    auxs = auxs->sig;
                    cont++;
                }
                borrars = auxs->sig;
                auxs->sig = borrars->sig;
                if(borrars->info != NULL)
                    delete borrars->info;
                    delete borrars;
            }
        }
        auxcol = auxcol->sig;
    }
    
}

void delet(string nomtab, string condicion){
    TablaABB auxtab = buscarTabla(tablas, nomtab);
    int cont = 0;
    if (auxtab == NULL){
        cout << "No hay una tabla con ese nombre" << "\n";
        return; 
    }
    if (condicion.length() == 0){//Si la condicion es "" borro todas las tuplas
        if (auxtab->col != NULL){
            if (auxtab->col->tipo == 0){
                if (auxtab->col->li != NULL){
                    ListaInt auxi = auxtab->col->li;
                    cont = 1;
                    auxi = auxi->sig;
                    while (auxi != NULL){
                        cont++;
                        auxi = auxi->sig;
                    }
                    while (auxtab->col->li != NULL){
                        eliminarTupla(auxtab,1);
                    }
                }
            }
            else{
                if (auxtab->col->ls != NULL){
                    ListaStr auxs = auxtab->col->ls;
                    cont = 1;
                    auxs = auxs->sig;
                    while (auxs != NULL){
                        cont++;
                        auxs = auxs->sig;
                    }
                    while (auxtab->col->ls != NULL){
                        eliminarTupla(auxtab,1);
                    }
                }
            }
        }
        return;
    }
    int ubicmenor = condicion.find_first_of("<");
    int ubicmayor = condicion.find_first_of(">");
    int ubicigual = condicion.find_first_of("=");
    if ((ubicmenor < 1 && ubicmayor < 1 && ubicigual < 1) || (ubicmenor + 1 == condicion.length() ||
            ubicmayor + 1 == condicion.length() || ubicigual + 1 == condicion.length() 
            || (ubicmenor > 0 && ubicigual > 0) || (ubicmayor > 0 && ubicigual > 0))){
        cout << "Formato de condicion incorrecto" << "\n";
        return; 
    }
    string nomcol;
    string dato;
    if (ubicmenor > 0 && ubicmayor > 0){
        nomcol = condicion.substr(0,ubicmenor);
        dato = condicion.substr(ubicmayor + 1);
        ColumnaP auxcol = buscarColumna(auxtab->col,nomcol);
        if (auxcol == NULL){
            cout << "No se encontro dicha columna" << "\n";
            return; 
            }
        if (auxcol->tipo == 1){
            ListaStr auxs = auxcol->ls;
            cont = 1;
            if (dato == "EMPTY"){
                while (auxs != NULL){
                    if (auxs->info != NULL){
                        eliminarTupla(auxtab,cont);
                    }
                    cont++;
                    auxs = auxs->sig;
                }
            }
            else{
                while (auxs != NULL){
                    if (auxs->info == NULL || *auxs->info != dato){
                        eliminarTupla(auxtab,cont);
                    }
                    cont++;
                    auxs = auxs->sig;
                }
            }
        }
    }
}//SIN TERMINAR

//condicion.length()
//condicion.find_first_of("<")
//condicion.substr(0,4)
//condicion.erase(0,4)


void impNomTabla(TablaABB a) {
    if (a == NULL)
        return;
    impNomTabla(a->izq);
    cout<<"-"<<a->nomt<<"-";
    impNomTabla(a->der);
}

void printTables() {
    if (tablas == NULL)
        cout << "La tabla es vacia" << endl;
    else impNomTabla(tablas);
}


void printMetadata(string nom){
    TablaABB auxtab = buscarTabla(tablas,nom);
    if (auxtab == NULL){
        cout << "No hay una tabla con ese nombre" << "\n";
        return; 
    }
    cout << "El nombre de la tabla es:" << "\n";
    cout << auxtab->nomt << "\n";
    if (auxtab->col != NULL){
        ColumnaP auxcol = auxtab->col;
        cout << "Y sus columnas son:" << "\n";
        while (auxcol != NULL){
            cout << auxcol->nomc << " / de tipo: " << auxcol->tipo << " / de calificador: " << auxcol->calif << "\n";
            auxcol = auxcol->sig;
        }
    }
}
