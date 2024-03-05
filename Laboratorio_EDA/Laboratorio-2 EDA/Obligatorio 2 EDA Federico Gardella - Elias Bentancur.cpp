
/* ACLARACIONES: Cambiamos el nombre de la funcion "minus" por "menos" ya que la primera
es una palabra reservada del lenguaje, al igual que cambiamos "integer" y "string"
por "integers" y "strings" en tipo de variable de las columnas ya que "string" tambien 
es palabra reservada */

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
#include <cstdlib>

using namespace std;


//********************ENUMERADOS*********************
enum _retorno {
    OK, 
    ERROR, 
    NO_IMPLEMENTADA
};
typedef enum _retorno TipoRet;

enum calificador
{
    PRIMARY_KEY,
    NOT_EMPTY,
    ANY
};
typedef calificador TipoCalificador; 

enum tipo_de_variable
{
    integers,
    strings,
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

//DE LISTA DE ARREGLOS DE STRINGS
struct nodoListaArreglos{
    string *A;
    nodoListaArreglos * sig;
};

typedef nodoListaArreglos *ListaArreglos;

//********************DECLARACIONES DE FUNCIONES*********************

//SOLICITADAS 

TipoRet createTable (string nombreTabla);
TipoRet dropTable (string nombreTabla);
TipoRet addCol (string nombreTabla, string NombreCol, string tipoCol, string calificadorCol);
TipoRet dropCol (string nombreTabla, string nombreCol);
TipoRet alterCol (string nombreTabla, string nombreCol, string tipoColNuevo, string calificadorColNuevo, string nombreColNuevo);
TipoRet insertInto (string nombreTabla, string columnasTupla, string valoresTupla);
TipoRet deleteTupla (string nombreTabla, string condicionEliminar); 
TipoRet update (string nombreTabla, string condicionModificar, string columnaModificar, string valorModificar);
TipoRet selectWhere (string nombreTabla1, string condicion, string nombreTabla2);
TipoRet printTables(); 
TipoRet printMetadata(string nombreTabla);
TipoRet printdatatable (string NombreTabla, string ordenadaPor); 
TipoRet select(string nombreTabla1, string columnas, string nombreTabla2);
TipoRet join(string nombreTabla1, string nombreTabla2, string  nombreTabla3);
TipoRet unionTable(string nombreTabla1, string nombreTabla2, string nombreTabla3);
TipoRet intersec(string nombreTabla1, string nombreTabla2, string nombreTabla3);
TipoRet menos(string nombreTabla1, string nombreTabla2, string nombreTabla3);

//AUXILIARES

TablaABB crearTabla(TablaABB &T, string nom);
TablaABB buscarTabla(TablaABB T, string nom);
void eliminarTodo (ColumnaP punt);
void eliminarTupla(TablaABB T, int altura);
void eliminarTuplasTodas(TablaABB auxtab);
TipoRet copiarOEliminarTuplasIgualODistinto(TablaABB auxtab1, ColumnaP auxcol, string dato, bool esigual, bool copiar, TablaABB auxtab2);
TipoRet copiarOEliminarTuplasMenorOMayor(TablaABB auxtab1, ColumnaP auxcol, string dato, bool esmenor,bool copiar, TablaABB auxtab2);
ColumnaP buscarColumna(ColumnaP C, string nom);
void impNomTabla(TablaABB a);
bool esInt(string& str);
int contarCol (ColumnaP col);
int contarDatos (ColumnaP col);
void copiarTupla(TablaABB t1, int altura, TablaABB t2);
void cambiarDatoAltura(int altura, ColumnaP col, string dato);
void cambiarDatoANY(ColumnaP colcond, ColumnaP col, string datocond, string dato, bool igual, bool menor);
TipoRet cambiarDatoPK(ColumnaP colcond, ColumnaP col, string datocond, string dato, bool igual, bool menor);
TipoRet cambiarDatoNE(ColumnaP colcond, ColumnaP col, string datocond, string dato, bool igual, bool menor);
void copiarColumna(ColumnaP col1, ColumnaP col2);
bool mismoEsquema(TablaABB tab1, TablaABB tab2);
bool mismaTupla(TablaABB tab1, int altura, TablaABB tab2);
ListaArreglos tuplasAArreglos(TablaABB tab);
void imprimirTabla(ListaInt cols, ListaArreglos tuplas);
ListaArreglos ordenarTuplas(ListaInt cols, ListaArreglos tuplas);
int verNivelInt (ListaInt inicio, ListaInt encontrar);
int verNivelStr (ListaStr inicio, ListaStr encontrar);
//**********************VARIABLES GLOBALES*************************

TablaABB tablas = NULL;
const int NOT_FIND = 4294967295;

//*******************IMPLEMENTACION DE FUNCIONES********************

TipoRet createTable (string nombreTabla){
    if (nombreTabla == "")
        return ERROR;
    if (tablas == NULL){
        tablas = new nodoTablaABB;
        tablas->nomt = nombreTabla;
        tablas->izq = tablas->der = NULL;
        tablas->col = NULL;
    }
    else{
        TablaABB nueva = NULL;
        if (nombreTabla > tablas->nomt)
            nueva = crearTabla(tablas->der,nombreTabla);

        else{
            if (nombreTabla < tablas->nomt)
                nueva = crearTabla(tablas->izq,nombreTabla);
        }
        if (nueva == NULL)
            return ERROR;
    }
    return OK; 
}

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

void eliminarTodo (ColumnaP punt){
    ColumnaP elim;
    while (punt != NULL) {
        elim = punt;
        punt = punt->sig;
        if (elim->tipo == 0) {
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
}

TipoRet dropTable (string nombreTabla){
    TablaABB t = buscarTabla(tablas, nombreTabla);
    if (nombreTabla == "" || t == NULL)
        return ERROR;
    if (tablas == t) {
        if (t->izq == NULL && t->der == NULL) {
        eliminarTodo(t->col);
        delete t;
        tablas = NULL;
        return OK;
        }

    if (t->izq == NULL) {
        tablas = tablas->der;
        eliminarTodo(t->col);
        delete t;
        return OK;
    }

    if (t->der == NULL) {
        tablas = tablas->izq;
        eliminarTodo(t->col);
        delete t;
        return OK;
    }

    if (t->der->izq == NULL) {
        tablas = tablas->der;
        tablas->izq = t->izq;
        eliminarTodo(t->col);
        delete t;
        return OK;
    }

    TablaABB aux = t->der;
    TablaABB aux2 = aux;

    while (aux->izq != NULL)
        aux = aux->izq;
    while (aux2->izq != aux)
        aux2 = aux2->izq;

    aux2->izq = aux->der;
    aux->der = t->der;
    aux->izq = t->izq;
    tablas = aux;
    eliminarTodo(t->col);
    delete t;
    return OK;
    }

    TablaABB ant = tablas;
    while (ant->izq != t && ant->der != t) {
        if (ant->nomt > nombreTabla)
            ant = ant->izq;
        else if (ant->nomt < nombreTabla)
            ant = ant->der;
    }

    if (t->izq == NULL && t->der == NULL) {
        if (ant->izq == t)
            ant->izq = NULL;
        else
            ant->der = NULL;
        eliminarTodo(t->col);
        delete t;
        return OK;
    }

    if (t->izq == NULL) {
        if (ant->izq == t)
            ant->izq = t->der;
        else
            ant->der = t->der;
        eliminarTodo(t->col);
        delete t;
        return OK;
    }

    if (t->der == NULL) {
        if (ant->izq == t)
            ant->izq = t->izq;
        else
            ant->der = t->izq;
        eliminarTodo(t->col);
        delete t;
        return OK;
    }

    if (t->der->izq == NULL) {
        if (ant->izq == t) {
            ant->izq = t->der;
            ant->izq->izq = t->izq;
        }
        else {
            ant->der = t->der;
            ant->der->izq = t->izq;
        }
        eliminarTodo(t->col);
        delete t;
        return OK;
    }

    TablaABB aux = t->der;
    TablaABB aux2 = aux;

    while (aux->izq != NULL)
        aux = aux->izq;
    while (aux2->izq != aux)
        aux2 = aux2->izq;

    aux2->izq = aux->der;

    if (ant->izq == t)
        ant->izq = aux;
    else
        ant->der = aux;
    
    aux->der = t->der;
    aux->izq = t->izq;
    eliminarTodo(t->col);
    delete t;
    return OK;
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

TipoRet addCol (string nombreTabla, string NombreCol, string tipoCol, string calificadorCol){
    TablaABB T = buscarTabla(tablas,nombreTabla);
    if (T == NULL || NombreCol == "")
        return ERROR;
    if (tipoCol != "strings" && tipoCol != "integers")
        return ERROR;
    if (calificadorCol != "PRIMARY_KEY" && calificadorCol != "NOT_EMPTY" && calificadorCol != "ANY")
        return ERROR;
    bool pk = false;
    if (T->col != NULL && (T->col->li != NULL || T->col->ls != NULL) && (calificadorCol == "NOT_EMPTY" || calificadorCol == "PRIMARY_KEY"))
        return ERROR;
    ColumnaP aux = T->col;
    while (aux != NULL && aux->sig != NULL && aux->nomc != NombreCol){
        if (aux->calif == 0 || (aux->sig != NULL && aux->sig->calif == 0))
            pk = true;
        aux = aux->sig;
    }
    if (pk && calificadorCol == "PRIMARY_KEY"){
        return ERROR;
    }
    if (aux != NULL && aux->nomc == NombreCol){
        return ERROR;
    }
    ColumnaP nueva = new nodoColumna;
    nueva->nomc = NombreCol;
    if (calificadorCol == "PRIMARY_KEY")
        nueva->calif = PRIMARY_KEY;
    if (calificadorCol == "NOT_EMPTY")
        nueva->calif = NOT_EMPTY;
    if (calificadorCol == "ANY")
        nueva->calif = ANY;
    nueva->sig = NULL;
    if(tipoCol == "integers")
        nueva->tipo = integers;
    if(tipoCol == "strings")
        nueva->tipo = strings;
    nueva->ls = NULL;
    nueva->li = NULL;
    if (aux == NULL){
        T->col = nueva;
        return OK;
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
    while (aux->sig != NULL)
        aux = aux->sig;
    aux->sig = nueva;
    aux = nueva;
    if (tipoCol == "integers" && cont != 0){
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
        return OK;
    }
    if (tipoCol == "strings" && cont != 0){
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
        return OK;
    }
    return OK;   
}

TipoRet dropCol (string nombreTabla, string nombreCol){
    TablaABB tablaP = buscarTabla(tablas,nombreTabla); //verificar que la tabla exista
    if (tablaP == NULL)
        return ERROR;
    ColumnaP elim = buscarColumna(tablaP->col,nombreCol); //verificar que la columna exista
    if (elim == NULL) 
        return ERROR;
    if (elim->calif == 0 && tablaP->col->sig != NULL)
        return ERROR;
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
        return OK;    
}

TipoRet alterCol (string nombreTabla, string nombreCol, string tipoColNuevo, string calificadorColNuevo, string nombreColNuevo){
    TablaABB auxtab = buscarTabla(tablas, nombreTabla);// Nos fijamos si existe esa tabla
    if (auxtab == NULL)
        return ERROR;
    ColumnaP auxcol = buscarColumna(auxtab->col, nombreColNuevo);// Nos fijamos si ya existe una columna con el nuevo nombre
    if (auxcol != NULL)
        return ERROR;
    auxcol = buscarColumna(auxtab->col, nombreCol);// Nos fijamos si existe una columna con el nombre que buscamos
    if (auxcol == NULL)
        return ERROR;
    if (auxcol->calif == 0 && auxtab->col->sig != NULL)
        return ERROR;
    if (auxcol->tipo == 1 && tipoColNuevo == "integers")// Nos fijamos si quieren pasar strings a integers
        return ERROR;
    if (nombreColNuevo == "")
        return ERROR;
    if (calificadorColNuevo == "PRIMARY_KEY" && auxcol->calif != 0){//Nos fijamos si ya existe Primary_key en caso de querer poner una columna de ese tipo
        bool pk = false;
        ColumnaP pksearch = auxtab->col;
        while (pksearch != NULL){
            if (pksearch->calif == 0)
                pk = true;
            pksearch = pksearch->sig;
        }
        if (pk)
            return ERROR; 
    }
    auxcol->nomc = nombreColNuevo;
    if(calificadorColNuevo == "PRIMARY_KEY")
        auxcol->calif = PRIMARY_KEY;
    if(calificadorColNuevo == "NOT_EMPTY")
        auxcol->calif = NOT_EMPTY;
    if(calificadorColNuevo == "ANY")
        auxcol->calif = ANY;
    if (auxcol->tipo == 0 && tipoColNuevo == "strings"){
        auxcol->tipo = strings;
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
    return OK;
}

bool esInt(string& str){
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

int contarDatos (ColumnaP col) {
    int res = 0;
    if (col->tipo == 0) {
        ListaInt recInt = col->li;
        while (recInt != NULL) {
            res++;
            recInt = recInt->sig;
        }
    }
    else {
        ListaStr recStr = col->ls;
        while (recStr != NULL) {
            res++;
            recStr = recStr->sig;
        }
    }
    return res;
}

TipoRet insertInto (string nombreTabla, string columnasTupla, string valoresTupla) {
    TablaABB tablaP = buscarTabla(tablas,nombreTabla);
    
    if (tablaP == NULL) {
        return ERROR;
    }
    
    if (tablaP->col == NULL) {
        return ERROR;
    }
    

    int cantColumnas = 0, cantValores = 0, i;
    for (i = 0; i < columnasTupla.size(); i++)
        if (columnasTupla[i] == ':')
            cantColumnas++;
    for (i = 0; i < valoresTupla.size(); i++)
        if (valoresTupla[i] == ':')
            cantValores++;
    if (cantColumnas != cantValores) {            //ver que la cantidad de datos ingresados coincida
        return ERROR;
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
    
    string arrVal[cantValores+1];
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
    i = 0;
    while (esta && i <= cantColumnas) {
        aux = tablaP->col;
        esta = false;
        while (aux != NULL && esta != true) {
            esta = (aux->nomc == arrCol[i]);
            if (esta) {                                     //verificar que el tipo de la columna y el valor sea el mismo
                if (aux->tipo == integers) 
                    mismoTipo = esInt(arrVal[i]);
                else
                    mismoTipo = true;
                if (mismoTipo == false) {
                    return ERROR;
                }
            }
            aux = aux->sig;
        }
        i++;
    }
    if (esta == false) {
        return ERROR;
    }
    
    
    int contCol = contarCol(tablaP->col);
    if (cantColumnas+1 < contCol) { //verificar que si habra valores NULL, estos esten permitidos
        i = 0;
        aux = tablaP->col;
        while (aux != NULL) { 
            esta = false;
            while (esta == false && i <= cantColumnas) {
                esta = (aux->nomc == arrCol[i]);
                i++;            
            }
            if (!esta) {
                if (aux->calif != 2) {
                    return ERROR;
                }
            }
            aux = aux->sig;
            i = 0;
        }
    }
    
    
    string arrColOrd[contCol], arrValOrd[contCol];    //hacer un arreglo ordenado
    aux = tablaP->col;
    int pos = 0;
    i = 0;
    while (aux != NULL) {
        while (i <= cantColumnas && aux->nomc != arrCol[i])
            i++;
        
        if (i <= cantColumnas) {
            arrColOrd[pos] = arrCol[i];
            arrValOrd[pos] = arrVal[i];
        }
        aux = aux->sig;
        i = 0;
        pos++;
    }


    //si hay pk, verificar que no se repita
    aux = tablaP->col;
    pos = 0;
    while (aux != NULL && aux->calif != 0) {
        aux = aux->sig;
        pos++;
    }

    ListaInt auxInt;
    ListaStr auxStr;
    if (aux != NULL) {
       
        auxInt = aux->li;
        auxStr = aux->ls;
        if (arrValOrd[pos] == "") {   //ver que la clave a agregar no sea null
            return ERROR;
        }
        if (aux->tipo == 0) {
            while (auxInt != NULL) {
                if (*auxInt->info == stoi(arrValOrd[pos])) {
                    return ERROR;
                }
                auxInt = auxInt->sig;
            }

        } 
        else if (aux->tipo == 1) {
            while (auxStr != NULL) {
                if (*auxStr->info == (arrValOrd[pos])) {
                    return ERROR;
                }
                auxStr = auxStr->sig;
            }
        }
    }

    
    //ver que no se agreguen valores null en NO_EMPTY
    aux = tablaP->col;
    pos = 0;

    while (aux != NULL) {
        if (aux->calif == 1 && arrValOrd[pos] == "") {
                return ERROR;
            }
        aux = aux->sig;
        pos++;
    }
    
    
    //verificar si la tupla se repite
    aux = tablaP->col;
    i = 1;
    pos = 0;
    int nivel = 1;
    int cantDatos = contarDatos(aux);
    bool repetido = true;

    while (i <= cantDatos) {

        while (pos < contCol && repetido) {
            if (aux->tipo == 0) {
                auxInt = aux->li;
                while (nivel < i) {
                    auxInt = auxInt->sig;
                    nivel++;
                }    
                if (auxInt->info == NULL && arrValOrd[pos] != "" || auxInt->info != NULL && arrValOrd[pos] == "")
                    repetido = false;
                else {
                    if (auxInt->info != NULL && arrValOrd[pos] != "" && *auxInt->info != stoi(arrValOrd[pos]))
                        repetido = false;
                }
            }
            else {
                auxStr = aux->ls;
                while (nivel < i) {
                    auxStr = auxStr->sig;
                    nivel++;
                }
                if (auxStr->info == NULL && arrValOrd[pos] != "" || auxStr->info != NULL && arrValOrd[pos] == "")
                    repetido = false;
                else {
                    if (auxStr->info != NULL && arrValOrd[pos] != "" && *auxStr->info != arrValOrd[pos])
                        repetido = false;
                }
            }
            pos++;
            aux = aux->sig;
            nivel = 1;
        
        }
        if (repetido) {
            return ERROR;
        }
        aux = tablaP->col;
        i++;
        pos = 0;
        repetido = true;
    }

    //agregar tupla
   
    aux = tablaP->col;
    esta = false;
    i = 0;
    ListaInt agregarInt;
    ListaStr agregarStr;

    while (aux != NULL) {

        if (aux->tipo == 0) {
            if (arrValOrd[i] == "") {
                agregarInt = new nodoListaInt;
                agregarInt->info = NULL;
                agregarInt->sig = aux->li;
                aux->li = agregarInt;
            }
            else {
                agregarInt = new nodoListaInt;
                agregarInt->info = new int;
                *agregarInt->info = stoi(arrValOrd[i]);
                agregarInt->sig = aux->li;
                aux->li = agregarInt;
            }
        }
        else {
            if (arrValOrd[i] == "") {
                agregarStr = new nodoListaStr;
                agregarStr->info = NULL;
                agregarStr->sig = aux->ls;
                aux->ls = agregarStr;
            }
            else{
                agregarStr = new nodoListaStr;
                agregarStr->info = new string;
                *agregarStr->info = arrValOrd[i];
                agregarStr->sig = aux->ls;
                aux->ls = agregarStr;
            }   
        }
        aux = aux->sig;
        i++;
    }
    return OK;
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

TipoRet deleteTupla (string nombreTabla, string condicionEliminar){
    TablaABB auxtab = buscarTabla(tablas, nombreTabla);
    if (nombreTabla == "" || auxtab == NULL)
        return ERROR; 
    if (condicionEliminar.length() == 0){//Si la condicion es "" borro todas las tuplas
        eliminarTuplasTodas(auxtab);
        return OK;
    }
    int ubicmenor = condicionEliminar.find_first_of("<");
    int ubicmayor = condicionEliminar.find_first_of(">");
    int ubicigual = condicionEliminar.find_first_of("=");
    string nomcol;
    string dato;
    ColumnaP auxcol;
    if ((ubicmenor != NOT_FIND) && ubicmayor != NOT_FIND){
        nomcol = condicionEliminar.substr(0,ubicmenor);
        dato = condicionEliminar.substr(ubicmayor + 1);
        auxcol = buscarColumna(auxtab->col,nomcol);
        return copiarOEliminarTuplasIgualODistinto(auxtab, auxcol, dato, false, false, NULL);
    }
    if (ubicigual != NOT_FIND){
        nomcol = condicionEliminar.substr(0,ubicigual);
        dato = condicionEliminar.substr(ubicigual + 1);
        auxcol = buscarColumna(auxtab->col,nomcol);
        return copiarOEliminarTuplasIgualODistinto(auxtab, auxcol, dato, true, false, NULL);
    }
    if (ubicmenor != NOT_FIND){
        nomcol = condicionEliminar.substr(0,ubicmenor);
        dato = condicionEliminar.substr(ubicmenor + 1);
        auxcol = buscarColumna(auxtab->col,nomcol);
        return copiarOEliminarTuplasMenorOMayor(auxtab, auxcol, dato, true, false, NULL);
    }
    if (ubicmayor != NOT_FIND){
        nomcol = condicionEliminar.substr(0,ubicmayor);
        dato = condicionEliminar.substr(ubicmayor + 1);
        auxcol = buscarColumna(auxtab->col,nomcol);
        return copiarOEliminarTuplasMenorOMayor(auxtab, auxcol, dato, false, false, NULL);
    }
}

void eliminarTuplasTodas(TablaABB auxtab){
        if (auxtab->col != NULL){
            int cont;
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
}

TipoRet copiarOEliminarTuplasIgualODistinto(TablaABB auxtab1, ColumnaP auxcol, string dato, bool esigual, bool copiar, TablaABB auxtab2){
    if (auxcol == NULL)
        return ERROR; 
    int cont;
    if (auxcol->tipo == 1){
        ListaStr auxs = auxcol->ls;
        cont = 1;
        if (dato == "EMPTY"){
            while (auxs != NULL){
                if (esigual == true){
                    if (auxs->info == NULL){
                        auxs = auxs->sig;
                        if (copiar)
                            copiarTupla(auxtab1, cont, auxtab2);
                        else{
                            eliminarTupla(auxtab1,cont);
                            cont--;
                        }
                    }
                    else
                        auxs = auxs->sig;
                    cont++;
                }
                else{
                    if (auxs->info != NULL){
                        auxs = auxs->sig;
                        if (copiar)
                            copiarTupla(auxtab1, cont, auxtab2);
                        else{
                            eliminarTupla(auxtab1,cont);
                            cont--;
                        }
                    }
                    else
                        auxs = auxs->sig;
                    cont++;
                }
            }
        }
        else{
            while (auxs != NULL){
                if (auxs->info != NULL && *auxs->info == dato){
                    auxs = auxs->sig;
                    if (copiar)
                        copiarTupla(auxtab1, cont, auxtab2);
                    else{
                        eliminarTupla(auxtab1,cont);
                        cont--;
                    }
                }
                else
                    auxs = auxs->sig;
                cont++;
            }
        }
    }
    else{
        ListaInt auxi = auxcol->li;
        cont = 1;
        if (dato == "EMPTY"){
            while (auxi != NULL){
                if (esigual == true){
                    if (auxi->info == NULL){
                        auxi = auxi->sig;
                        if (copiar)
                            copiarTupla(auxtab1, cont, auxtab2);
                        else{
                            eliminarTupla(auxtab1,cont);
                            cont--;
                        }
                    }
                    else
                        auxi = auxi->sig;
                    cont++;
                }
                else{
                    if (auxi->info != NULL){
                        auxi = auxi->sig;
                        if (copiar)
                            copiarTupla(auxtab1, cont, auxtab2);
                        else{
                            eliminarTupla(auxtab1,cont);
                            cont--;
                        }
                    }
                    else
                        auxi = auxi->sig;
                    cont++;
                }
            }
        }
        else{
            if(!esInt(dato))
                return ERROR;
            int datoint = stoi(dato);
            while (auxi != NULL){
                if (esigual == true){
                    if (auxi->info != NULL && *auxi->info == datoint){
                        auxi = auxi->sig;
                        if (copiar)
                            copiarTupla(auxtab1, cont, auxtab2);
                        else{
                            eliminarTupla(auxtab1,cont);
                            cont--;
                        }
                    }
                    else
                        auxi = auxi->sig;
                    cont++;
                }
                else{
                    if (auxi->info != NULL && *auxi->info != datoint){
                        auxi = auxi->sig;
                        if (copiar)
                            copiarTupla(auxtab1, cont, auxtab2);
                        else{
                            eliminarTupla(auxtab1,cont);
                            cont--;
                        }
                    }
                    else
                        auxi = auxi->sig;
                    cont++;
                }
            }
        }
    }
    return OK; 
}

TipoRet copiarOEliminarTuplasMenorOMayor(TablaABB auxtab1, ColumnaP auxcol, string dato, bool esmenor,bool copiar, TablaABB auxtab2){
    if (auxcol == NULL)
        return ERROR; 
    int cont;
    if (auxcol->tipo == 1){
        ListaStr auxs = auxcol->ls;
        cont = 1;
        while (auxs != NULL){
            if (esmenor == true){
                if (auxs->info != NULL && *auxs->info < dato){
                    auxs = auxs->sig;
                    if (copiar)
                        copiarTupla(auxtab1, cont, auxtab2);
                    else{
                        eliminarTupla(auxtab1,cont);
                        cont--;
                    }
                }
                else
                    auxs = auxs->sig;
                cont++;
            }
            else{
                if (auxs->info != NULL && *auxs->info > dato){
                    auxs = auxs->sig;
                    if (copiar)
                        copiarTupla(auxtab1, cont, auxtab2);
                    else{
                        eliminarTupla(auxtab1,cont);
                        cont--;
                    }
                }
                else
                    auxs = auxs->sig;
                cont++;
            }
        }
    }
    else{
        ListaInt auxi = auxcol->li;
        cont = 1;
        if(!esInt(dato))
            return ERROR;
        int datoint = stoi(dato);
        while (auxi != NULL){
            if (esmenor == true){
                if (auxi->info != NULL && *auxi->info < datoint){
                    auxi = auxi->sig;
                    if (copiar)
                        copiarTupla(auxtab1, cont, auxtab2);
                    else{
                        eliminarTupla(auxtab1,cont);
                        cont--;
                    }
                }
                else
                    auxi = auxi->sig;
                cont++;
            }
            else{
                if (auxi->info != NULL && *auxi->info > datoint){
                    auxi = auxi->sig;
                    if (copiar)
                        copiarTupla(auxtab1, cont, auxtab2);
                    else{
                        eliminarTupla(auxtab1,cont);
                        cont--;
                    }
                }
                else
                    auxi = auxi->sig;
                cont++;
            }
        }
    } 
    return OK;
}

void impNomTabla(TablaABB a) {
    if (a == NULL)
        return;
    impNomTabla(a->izq);
    cout<<"-"<<a->nomt<<"-";
    impNomTabla(a->der);
}

TipoRet printTables(){
    if (tablas == NULL)
        return ERROR;
    impNomTabla(tablas);
    cout<<endl;
    return OK;
}

TipoRet printMetadata(string nombreTabla){
    TablaABB auxtab = buscarTabla(tablas,nombreTabla);
    if (nombreTabla == "" || auxtab == NULL)
        return ERROR; 
    cout << "El nombre de la tabla es:" << "\n";
    cout << auxtab->nomt << "\n";
    if (auxtab->col != NULL){
        ColumnaP auxcol = auxtab->col;
        cout << "Y sus columnas son:" << "\n";
        string tipo;
        string calificador;
        while (auxcol != NULL){
            if (auxcol->tipo == 0)
                tipo = "integers";
            else
                tipo = "strings";
            if (auxcol->calif == 0)
                calificador = "PRIMARY_KEY";
            else{
                if (auxcol->calif == 1)
                    calificador = "NOT_EMPTY";
                else 
                    calificador = "ANY";
            }
            cout << auxcol->nomc << " / de tipo: " << tipo << " / de calificador: " << calificador << "\n";
            auxcol = auxcol->sig;
        }
    }
    return OK;
}

void copiarTupla(TablaABB t1, int altura, TablaABB t2){// Precondicion: t1 y t2 != NULL y altura>0
    ColumnaP auxcol1, auxcol2;                         // Las tablas tienen las mismas columnas 
    auxcol1 = t1->col;                                 // en el mismo orden.
    auxcol2 = t2->col;
    int cont = 1;
    ListaInt auxi1, auxi2;
    ListaStr auxs1, auxs2;
    while (auxcol1 != NULL){
        if (auxcol1->tipo == 0){
            auxi1 = auxcol1->li;
            while (cont != altura){
                auxi1 = auxi1->sig;
                cont++;
            }
            if(auxcol2->li == NULL){
                auxcol2->li = new nodoListaInt;
                auxi2 = auxcol2->li;

            }
            else{
                auxi2 = auxcol2->li;
                while (auxi2->sig != NULL)
                    auxi2 = auxi2->sig;
                auxi2->sig = new nodoListaInt;
                auxi2 = auxi2->sig;
            }
            auxi2->sig = NULL;
            auxi2->info = new int;
            *auxi2->info = *auxi1->info;
        }
        else{
            auxs1 = auxcol1->ls;
            while (cont != altura){
                auxs1 = auxs1->sig;
                cont++;
            }
            if(auxcol2->ls == NULL){
                auxcol2->ls = new nodoListaStr;
                auxs2 = auxcol2->ls;

            }
            else{
                auxs2 = auxcol2->ls;
                while (auxs2->sig != NULL)
                    auxs2 = auxs2->sig;
                auxs2->sig = new nodoListaStr;
                auxs2 = auxs2->sig;
            }
            auxs2->sig = NULL;
            auxs2->info = new string;
            *auxs2->info = *auxs1->info;
        }
        auxcol1 = auxcol1->sig;
        auxcol2 = auxcol2->sig;
        cont = 1;
    }
}

TipoRet selectWhere (string nombreTabla1, string condicion, string nombreTabla2){
    TablaABB T1 = buscarTabla(tablas, nombreTabla1);
    if (nombreTabla1 == "" || T1 == NULL)
        return ERROR; 
    TablaABB T2 = buscarTabla(tablas, nombreTabla2);
    if (T2 != NULL)
        return ERROR;  
    TipoRet X = createTable(nombreTabla2);
    T2 = buscarTabla(tablas, nombreTabla2);
    if (T1->col == NULL)
        return ERROR;
    ColumnaP auxcol = T1->col;
    while (auxcol != NULL){
        if(auxcol->tipo == 0){
            if (auxcol->calif == 0)
                X = addCol(nombreTabla2, auxcol->nomc, "integers", "PRIMARY_KEY");
            if (auxcol->calif == 1)
                X = addCol(nombreTabla2, auxcol->nomc, "integers", "NOT_EMPTY");
            if (auxcol->calif == 2)
                X = addCol(nombreTabla2, auxcol->nomc, "integers", "ANY");
        }
        if(auxcol->tipo == 1){
            if (auxcol->calif == 0)
                X = addCol(nombreTabla2, auxcol->nomc, "strings", "PRIMARY_KEY");
            if (auxcol->calif == 1)
                X = addCol(nombreTabla2, auxcol->nomc, "strings", "NOT_EMPTY");
            if (auxcol->calif == 2)
                X = addCol(nombreTabla2, auxcol->nomc, "strings", "ANY");
        }
        auxcol = auxcol->sig;
    }
    if (condicion == ""){
        int altura = 1;
        if (T1->col->tipo == 0){
            ListaInt auxi = T1->col->li;
            while (auxi != NULL){
                copiarTupla(T1, altura, T2);
                auxi = auxi->sig;
                altura++;
            }
        }
        else{
            ListaStr auxs = T1->col->ls;
            while (auxs != NULL){
                copiarTupla(T1, altura, T2);
                auxs = auxs->sig;
                altura++;
            } 
        }
    }
    else{
        int ubicmenor = condicion.find_first_of("<");
        int ubicmayor = condicion.find_first_of(">");
        int ubicigual = condicion.find_first_of("=");
        string nomcol;
        string dato;
        ColumnaP auxcol;
        if (ubicmenor != NOT_FIND && ubicmayor != NOT_FIND){
            nomcol = condicion.substr(0,ubicmenor);
            dato = condicion.substr(ubicmayor + 1);
            auxcol = buscarColumna(T1->col,nomcol);
            return copiarOEliminarTuplasIgualODistinto(T1, auxcol, dato, false, true, T2);
        }
        if (ubicigual != NOT_FIND){
            nomcol = condicion.substr(0,ubicigual);
            dato = condicion.substr(ubicigual + 1);
            auxcol = buscarColumna(T1->col,nomcol);
            return copiarOEliminarTuplasIgualODistinto(T1, auxcol, dato, true, true, T2);
        }
        if (ubicmenor != NOT_FIND){
            nomcol = condicion.substr(0,ubicmenor);
            dato = condicion.substr(ubicmenor + 1);
            auxcol = buscarColumna(T1->col,nomcol);
            return copiarOEliminarTuplasMenorOMayor(T1, auxcol, dato, true, true, T2);
        }
        if (ubicmayor != NOT_FIND){
            nomcol = condicion.substr(0,ubicmayor);
            dato = condicion.substr(ubicmayor + 1);
            auxcol = buscarColumna(T1->col,nomcol);
            return copiarOEliminarTuplasMenorOMayor(T1, auxcol, dato, false, true, T2); 
        }
    }  
}

void impTablas (string nombreTabla){
    TablaABB t = buscarTabla(tablas,nombreTabla);
    if (t == NULL) {
        cout<<"La tabla no existe"<<endl;
        return;
    }
    ColumnaP c = t->col;
    ListaInt recInt;
    ListaStr recStr;
    while (c != NULL) {
        cout<<"Columna "<<c->nomc<<":"<<endl;
        if (c->tipo == 0) {
            recInt = c->li;
            while (recInt != NULL) {
                if(recInt->info == NULL)
                    cout<<"NULL"<<endl;
                else  
                    cout<<*recInt->info<<endl;
                recInt = recInt->sig;
            }
        }
        else {
            recStr = c->ls;
            while (recStr != NULL) {
                if(recStr->info == NULL)
                    cout<<"NULL"<<endl;
                else  
                    cout<<*recStr->info<<endl;
                recStr = recStr->sig;
            }
        }
        c = c->sig;
    }
}

void cambiarDatoAltura(int altura, ColumnaP col, string dato){
    if (altura < 1 || (col->li == NULL && col->ls == NULL))
        return;
    int cont = 1;
    ListaInt auxi;
    ListaStr auxs;
    if (dato == "EMPTY"){
        if (col->tipo == 0){
            auxi = col->li;
            while (cont != altura){
                auxi = auxi->sig;;
                cont++;
            }
            delete auxi->info;
            auxi->info = NULL;
        }
        else{
            auxs = col->ls;
            while(cont != altura){
                auxs = auxs->sig;
                cont++;
            }
            delete auxs->info;
            auxs->info = NULL;
        }
    }
    else{
        if(esInt(dato)){
            if(col->tipo == 0){
                int datoint = stoi(dato);
                auxi = col->li;
                while (cont != altura){
                    auxi = auxi->sig;;
                    cont++;
                }
                if (auxi->info == NULL)
                    auxi->info = new int;
                *auxi->info = datoint;
            }
            else{
                auxs = col->ls;
                while (cont != altura){
                    auxs = auxs->sig;;
                    cont++;
                }
                if (auxs->info == NULL)
                    auxs->info = new string;
                *auxs->info = dato;
            }
        }
        else{
            auxs = col->ls;
            while (cont != altura){
                auxs = auxs->sig;;
                cont++;
            }
            if (auxs->info == NULL)
                auxs->info = new string;
            *auxs->info = dato;
        }
    }
}

void cambiarDatoANY(ColumnaP colcond, ColumnaP col, string datocond, string dato, bool igual, bool menor){
    int cont = 1;
    ListaInt auxi;
    ListaStr auxs;
    if(datocond == "EMPTY"){
        if(igual && !menor){//significa que el signo es "="
            if (colcond->tipo == 0){
                auxi = colcond->li;
                while (auxi != NULL){
                    if (auxi->info == NULL)
                        cambiarDatoAltura(cont, col, dato);
                    auxi = auxi->sig;
                    cont++;
                }
            }
            else{
                auxs = colcond->ls;
                while (auxs != NULL){
                    if(auxs->info == NULL)
                        cambiarDatoAltura(cont, col, dato);
                    auxs = auxs->sig;
                    cont++;
                }
            }
        }
        if(!igual && menor){//significa que el signo es "<>"
            if (colcond->tipo == 0){
                auxi = colcond->li;
                while (auxi != NULL){
                    if (auxi->info != NULL)
                        cambiarDatoAltura(cont, col, dato);
                    auxi = auxi->sig;
                    cont++;
                }
            }
            else{
                auxs = colcond->ls;
                while (auxs != NULL){
                    if(auxs->info != NULL)
                        cambiarDatoAltura(cont, col, dato);
                    auxs = auxs->sig;
                    cont++;
                }
            }
        }
    }
    else{
        if(igual && !menor){//significa que el signo es "="
            if (esInt(datocond)){
                if (colcond->tipo == 0){
                    int datocondint = stoi(datocond);
                    auxi = colcond->li;
                    while (auxi != NULL){
                        if (*auxi->info == datocondint)
                            cambiarDatoAltura(cont, col, dato);
                        auxi = auxi->sig;
                        cont++;
                    }
                }
                else{
                    auxs = colcond->ls;
                    while (auxs != NULL){
                        if(*auxs->info == datocond)
                            cambiarDatoAltura(cont, col, dato);
                        auxs = auxs->sig;
                        cont++;
                    }
                }
            }
            else{
                auxs = colcond->ls;
                while (auxs != NULL){
                    if(*auxs->info == datocond)
                        cambiarDatoAltura(cont, col, dato);
                    auxs = auxs->sig;
                    cont++;
                }
            }
        }
        if(!igual && menor){//significa que el signo es "<>"
            if (esInt(datocond)){
                if (colcond->tipo == 0){
                    int datocondint = stoi(datocond);
                    auxi = colcond->li;
                    while (auxi != NULL){
                        if (*auxi->info != datocondint)
                            cambiarDatoAltura(cont, col, dato);
                        auxi = auxi->sig;
                        cont++;
                    }
                }
                else{
                    auxs = colcond->ls;
                    while (auxs != NULL){
                        if(*auxs->info != datocond)
                            cambiarDatoAltura(cont, col, dato);
                        auxs = auxs->sig;
                        cont++;
                    }
                }
            }
            else{
                auxs = colcond->ls;
                while (auxs != NULL){
                    if(*auxs->info != datocond)
                        cambiarDatoAltura(cont, col, dato);
                    auxs = auxs->sig;
                    cont++;
                }
            }
        }
        if(!igual && !menor){//significa que el signo es ">"
            if (esInt(datocond)){
                if (colcond->tipo == 0){
                    int datocondint = stoi(datocond);
                    auxi = colcond->li;
                    while (auxi != NULL){
                        if (*auxi->info > datocondint)
                            cambiarDatoAltura(cont, col, dato);
                        auxi = auxi->sig;
                        cont++;
                    }
                }
                else{
                    auxs = colcond->ls;
                    while (auxs != NULL){
                        if(*auxs->info > datocond)
                            cambiarDatoAltura(cont, col, dato);
                        auxs = auxs->sig;
                        cont++;
                    }
                }
            }
            else{
                auxs = colcond->ls;
                while (auxs != NULL){
                    if(*auxs->info > datocond)
                        cambiarDatoAltura(cont, col, dato);
                    auxs = auxs->sig;
                    cont++;      
                }
            }
        }
        if(igual && menor){//significa que el signo es "<"
            if (esInt(datocond)){
                if (colcond->tipo == 0){
                    int datocondint = stoi(datocond);
                    auxi = colcond->li;
                    while (auxi != NULL){
                        if (*auxi->info < datocondint)
                            cambiarDatoAltura(cont, col, dato);
                        auxi = auxi->sig;
                        cont++;
                    }
                }
                else{
                    auxs = colcond->ls;
                    while (auxs != NULL){
                        if(*auxs->info < datocond)
                            cambiarDatoAltura(cont, col, dato);
                        auxs = auxs->sig;
                        cont++;
                    }
                }
            }
            else{
                auxs = colcond->ls;
                while (auxs != NULL){
                    if(*auxs->info < datocond)
                        cambiarDatoAltura(cont, col, dato);
                    auxs = auxs->sig;
                    cont++;
                }
            }
        }
    }
}

TipoRet cambiarDatoPK(ColumnaP colcond, ColumnaP col, string datocond, string dato, bool igual, bool menor){
    if (dato == "EMPTY")
        return ERROR; 
    int cont = 0;
    bool esta = false;
    ListaInt auxi;
    ListaStr auxs;
    if (igual && !menor){// "="
        if (esInt(datocond)){
            int datocondint = stoi(datocond);
            if(colcond->tipo == 0){
                auxi = colcond->li;
                while (auxi != NULL){
                    if (*auxi->info == datocondint)
                        cont++;
                    if (cont == 2)
                        auxi = NULL;
                    else
                        auxi = auxi->sig;
                }
            }
        }
        if (colcond->tipo == 1){
            auxs = colcond->ls;
            while (auxs != NULL){
                if (*auxs->info == datocond)
                    cont++;
                if (cont == 2)
                    auxs = NULL;
                else
                    auxs = auxs->sig;
            }
        }
    }
    if (!igual && menor){// "<>"
        if (esInt(datocond)){
            int datocondint = stoi(datocond);
            if(colcond->tipo == 0){
                auxi = colcond->li;
                while (auxi != NULL){
                    if (*auxi->info != datocondint)
                        cont++;
                    if (cont == 2)
                        auxi = NULL;
                    else
                        auxi = auxi->sig;
                }
            }
        }
        if (colcond->tipo == 1){
            auxs = colcond->ls;
            while (auxs != NULL){
                if (*auxs->info != datocond)
                    cont++;
                if (cont == 2)
                    auxs = NULL;
                else
                    auxs = auxs->sig;
            }
        }
    }
    if (!igual && !menor){// ">"
        if (esInt(datocond)){
            int datocondint = stoi(datocond);
            if(colcond->tipo == 0){
                auxi = colcond->li;
                while (auxi != NULL){
                    if (*auxi->info > datocondint)
                        cont++;
                    if (cont == 2)
                        auxi = NULL;
                    else
                        auxi = auxi->sig;
                }
            }
        }
        if (colcond->tipo == 1){
            auxs = colcond->ls;
            while (auxs != NULL){
                if (*auxs->info > datocond)
                    cont++;
                if (cont == 2)
                    auxs = NULL;
                else
                    auxs = auxs->sig;
            }
        }
    }
    if (igual && menor){// "<"
        if (esInt(datocond)){
            int datocondint = stoi(datocond);
            if(colcond->tipo == 0){
                auxi = colcond->li;
                while (auxi != NULL){
                    if (*auxi->info < datocondint)
                        cont++;
                    if (cont == 2)
                        auxi = NULL;
                    else
                        auxi = auxi->sig;
                }
            }
        }
        if (colcond->tipo == 1){
            auxs = colcond->ls;
            while (auxs != NULL){
                if (*auxs->info < datocond)
                    cont++;
                if (cont == 2)
                    auxs = NULL;
                else
                    auxs = auxs->sig;
            }
        }
    }
    if(cont == 2)
        return ERROR; 
    if (esInt(dato)){
        int datoint = stoi(dato);
        if(col->tipo == 0){
            auxi = col->li;
            while (auxi != NULL){
                if (*auxi->info == datoint){
                    esta == true;
                    auxi = NULL;
                }
                else
                    auxi = auxi->sig;
            }
        }
        else{
            auxs = col->ls;
            while (auxs != NULL){
                if (*auxs->info == dato){
                    esta == true;
                    auxs = NULL;
                }
                else
                    auxs = auxs->sig;
            }
        }
    }
    else{
        if(col->tipo == 1){
            auxs = col->ls;
            while (auxs != NULL){
                if (*auxs->info == dato){
                    esta == true;
                    auxs = NULL;
                }
                else
                    auxs = auxs->sig;
            }
        } 
    }
    if (esta)
        return ERROR; 
    cambiarDatoANY(colcond,col,datocond,dato,igual,menor);
    return OK;
}

TipoRet cambiarDatoNE(ColumnaP colcond, ColumnaP col, string datocond, string dato, bool igual, bool menor){
    if (dato == "EMPTY")
        return ERROR; 
    cambiarDatoANY(colcond,col,datocond,dato,igual,menor);
    return OK;
}

TipoRet update(string nombreTabla, string condicionModificar, string columnaModificar, string valorModificar){
    TablaABB auxtab = buscarTabla(tablas, nombreTabla);
    if(nombreTabla == "" || auxtab == NULL)
        return ERROR; 
    ColumnaP auxcol = buscarColumna(auxtab->col,columnaModificar);
    if(columnaModificar == "" || auxcol == NULL)
        return ERROR;
    ListaInt auxi;
    ListaStr auxs;
    if (condicionModificar == ""){
        if(auxcol->calif == 0 && ((auxcol->li != NULL && auxcol->li->sig != NULL) || (auxcol->ls != NULL && auxcol->ls->sig != NULL)))
            return ERROR; 
        if(esInt(valorModificar)){
            if(auxcol->tipo == 0){
                int datoint = stoi(valorModificar);
                auxi = auxcol->li;
                while (auxi != NULL){
                    if(auxi->info == NULL)
                        auxi->info = new int;
                    *auxi->info = datoint;
                    auxi = auxi->sig;
                }
            }
            else{
                auxs = auxcol->ls;
                while (auxs != NULL){
                    if(auxs->info == NULL)
                        auxs->info = new string;
                    *auxs->info = valorModificar;
                    auxs = auxs->sig;
                }
            }
        }
        else{
            if(valorModificar == "EMPTY"){
                if (auxcol->calif == 1 || auxcol->calif == 0)
                    return ERROR; 
                else{
                    if (auxcol->tipo == 0){
                        auxi = auxcol->li;
                        while (auxi != NULL){
                            delete auxi->info;
                            auxi->info = NULL;
                            auxi = auxi->sig;
                        }
                    }
                    else{
                        auxs = auxcol->ls;
                        while (auxs != NULL){
                            delete auxs->info;
                            auxs->info = NULL;
                            auxs = auxs->sig;
                        }
                    }
                }
            }
            else{
                if (auxcol->tipo == 0)
                    return ERROR; 
                else{
                    auxs = auxcol->ls;
                    while(auxs != NULL){
                        if(auxs->info == NULL)
                            auxs->info = new string;
                        *auxs->info = valorModificar;
                        auxs = auxs->sig;
                    }
                }
            }
        }
    }
    else{//Si la condicion no es ""
        int ubicmenor = condicionModificar.find_first_of("<");
        int ubicmayor = condicionModificar.find_first_of(">");
        int ubicigual = condicionModificar.find_first_of("=");
        string nomcol;
        string datocond;
        ColumnaP auxcolcond;
        if (ubicmenor != NOT_FIND && ubicmayor != NOT_FIND){
            nomcol = condicionModificar.substr(0,ubicmenor);
            datocond = condicionModificar.substr(ubicmayor + 1);
            auxcolcond = buscarColumna(auxtab->col,nomcol);
            if (nomcol == "" || auxcolcond == NULL || (!esInt(valorModificar) && auxcol->tipo == 0))
                return ERROR;
            if (auxcolcond->calif == 0)
                return cambiarDatoPK(auxcolcond,auxcol,datocond,valorModificar,false,true);
            if (auxcolcond->calif == 1)
                return cambiarDatoNE(auxcolcond,auxcol,datocond,valorModificar,false,true);
            if (auxcolcond->calif == 2)
                cambiarDatoANY(auxcolcond,auxcol,datocond,valorModificar,false,true);
            return OK;
        }
        if (ubicigual != NOT_FIND){
            nomcol = condicionModificar.substr(0,ubicigual);
            datocond = condicionModificar.substr(ubicigual + 1);
            auxcolcond = buscarColumna(auxtab->col,nomcol);
            if (nomcol == "" || auxcolcond == NULL || (!esInt(valorModificar) && auxcol->tipo == 0))
                return ERROR;
            if (auxcolcond->calif == 0)
                return cambiarDatoPK(auxcolcond,auxcol,datocond,valorModificar,true,false);
            if (auxcolcond->calif == 1)
                return cambiarDatoNE(auxcolcond,auxcol,datocond,valorModificar,true,false);
            if (auxcolcond->calif == 2)
                cambiarDatoANY(auxcolcond,auxcol,datocond,valorModificar,true,false);
            return OK;
        }
        if (ubicmenor != NOT_FIND){
            nomcol = condicionModificar.substr(0,ubicmenor);
            datocond = condicionModificar.substr(ubicmenor + 1);
            auxcolcond = buscarColumna(auxtab->col,nomcol);
            if (nomcol == "" || auxcolcond == NULL || (!esInt(valorModificar) && auxcol->tipo == 0))
                return ERROR;
            if (auxcolcond->calif == 0)
                return cambiarDatoPK(auxcolcond,auxcol,datocond,valorModificar,true,true);
            if (auxcolcond->calif == 1)
                return cambiarDatoNE(auxcolcond,auxcol,datocond,valorModificar,true,true);
            if (auxcolcond->calif == 2)
                cambiarDatoANY(auxcolcond,auxcol,datocond,valorModificar,true,true);
            return OK;
        }
        if (ubicmayor != NOT_FIND){
            nomcol = condicionModificar.substr(0,ubicmayor);
            datocond = condicionModificar.substr(ubicmayor + 1);
            auxcolcond = buscarColumna(auxtab->col,nomcol);
            if (nomcol == "" || auxcolcond == NULL || (!esInt(valorModificar) && auxcol->tipo == 0))
                return ERROR;
            if (auxcolcond->calif == 0)
                return cambiarDatoPK(auxcolcond,auxcol,datocond,valorModificar,false,false);
            if (auxcolcond->calif == 1)
                return cambiarDatoNE(auxcolcond,auxcol,datocond,valorModificar,false,false);
            if (auxcolcond->calif == 2)
                cambiarDatoANY(auxcolcond,auxcol,datocond,valorModificar,false,false);
            return OK;   
        }
    } 
}

void copiarColumna(ColumnaP col1, ColumnaP col2){
    if (col1 == NULL || col2 == NULL || (col1->li == NULL && col1->ls == NULL))
        return;
    if (col1->tipo == 0){
        ListaInt auxi1, auxi2;
        auxi1 = col1->li;
        col2->li = new nodoListaInt;
        auxi2 = col2->li;
        if (auxi1->info != NULL){
            auxi2->info = new int;
            *auxi2->info = *auxi1->info;
        }
        else
            auxi2->info = NULL;
        auxi1 = auxi1->sig;
        while (auxi1 != NULL){
            auxi2->sig = new nodoListaInt;
            auxi2 = auxi2->sig;
            if (auxi1->info != NULL){
                auxi2->info = new int;
                *auxi2->info = *auxi1->info;
            }
            else
                auxi2->info = NULL;
            auxi1 = auxi1->sig;
        }
        auxi2->sig = NULL;
    }
    else{
        ListaStr auxs1, auxs2;
        auxs1 = col1->ls;
        col2->ls = new nodoListaStr;
        auxs2 = col2->ls;
        if (auxs1->info != NULL){
            auxs2->info = new string;
            *auxs2->info = *auxs1->info;
        }
        else
            auxs2->info = NULL;
        auxs1 = auxs1->sig;
        while (auxs1 != NULL){
            auxs2->sig = new nodoListaStr;
            auxs2 = auxs2->sig;
            if (auxs1->info != NULL){
                auxs2->info = new string;
                *auxs2->info = *auxs1->info;
            }
            else
                auxs2->info = NULL;
            auxs1 = auxs1->sig;
        }
        auxs2->sig = NULL;
    }
}

TipoRet select(string nombreTabla1, string columnas, string nombreTabla2){
    TablaABB tab1 = buscarTabla(tablas, nombreTabla1);
    if (nombreTabla1 == "" || tab1 == NULL)
        return ERROR;
    TablaABB tab2 = buscarTabla(tablas, nombreTabla2);
    if (nombreTabla2 == "" || tab2 != NULL)
        return ERROR;
    TipoRet X = createTable(nombreTabla2);
    tab2 = buscarTabla(tablas, nombreTabla2);
    ColumnaP col1, col2, auxcol;
    string nomcol;
    col2 = new nodoColumna;// agregamos celda dummy
    auxcol = col2;
    while (columnas.length() != 0){
        if(columnas.find_first_of(":") == NOT_FIND){
            nomcol = columnas;
            columnas.erase(0,columnas.length());
        }
        else{
            nomcol = columnas.substr(0,columnas.find_first_of(":"));
            columnas.erase(0,columnas.find_first_of(":")+1);
        }
        col1 = buscarColumna(tab1->col, nomcol);
        if (col1 == NULL)
            return ERROR;
        auxcol->sig = new nodoColumna;
        auxcol = auxcol->sig;
        auxcol->nomc = nomcol;
        auxcol->calif = col1->calif;
        auxcol->tipo = col1->tipo;
        auxcol->li = NULL;
        auxcol->ls = NULL;
        auxcol->sig = NULL;
        copiarColumna(col1,auxcol);
    }
    auxcol = col2;
    col2 = auxcol->sig;
    delete auxcol; //borramos celda dummy
    tab2->col = col2;
    return OK;
}

int verNivelInt (ListaInt inicio, ListaInt encontrar) {
    int res = 1;
    while (inicio != encontrar) {
        inicio = inicio->sig;
        res++;
    }
    return res;
}

int verNivelStr (ListaStr inicio, ListaStr encontrar) {
    int res = 1;
    while (inicio != encontrar) {
        inicio = inicio->sig;
        res++;
    }
    return res;
}

TipoRet join (string nombreTabla1, string nombreTabla2, string nombreTabla3) {
    TablaABB t1 = buscarTabla(tablas, nombreTabla1);     //ver que las tablas 1 y 2 existan
    if (t1 == NULL) {
        cout<<"No existe la tabla 1"<<endl;
        return ERROR;
    }
    TablaABB t2 = buscarTabla(tablas, nombreTabla2);
    if (t2 == NULL) {
        cout<<"No existe la tabla 2"<<endl;
        return ERROR; 
    }
    TablaABB t3 = buscarTabla(tablas, nombreTabla3);  //ver que la tabla 3 no exista
    if (t3 != NULL) {
        cout<<"Ya existe la tabla 3"<<endl;
        return ERROR;
    }
    
    ColumnaP c1 = t1->col;                         //ver que las tablas tengan pk y sea la misma
    ColumnaP c2 = t2->col;
    while (c1 != NULL && c1->calif != 0)
        c1 = c1->sig;
    if (c1 == NULL) {
        cout<<"La tabla 1 no tenia PK"<<endl;
        return ERROR;
    }
    while (c2 != NULL && c2->calif != 0)
        c2 = c2->sig;
    if (c2 == NULL) {
        cout<<"La tabla 2 no tenia PK"<<endl;
        return ERROR;
    }

    if ((c1->nomc != c2->nomc) || (c1->nomc == c2->nomc && c1->tipo != c2->tipo)) {
        cout<<"La PK de las tablas no coinciden"<<endl;
        return ERROR;
    }

    ColumnaP rec1 = t1->col;    //ver que no se repita mas de una columna
    ColumnaP rec2 = t2->col;
    bool iguales = false;

    while (rec1 != NULL && !iguales) {
        while (rec2 != NULL && rec1->nomc != rec2->nomc)
            rec2 = rec2->sig;
        if (rec2 != NULL && rec1->nomc == rec2->nomc && rec1->calif != 0)
            iguales = true;
        rec1 = rec1->sig;
        rec2 = t2->col;
    }
    if (iguales) {
        cout<<"Hay mas de una columna en comun"<<endl;
        return ERROR;
    }

    createTable(nombreTabla3);   //crear tabla
    rec1 = t1->col;


    static const char *enum_tipo[] =
        { "integers", "strings" };

    static const char *enum_calif[] =
        { "PRIMARY_KEY", "NOT_EMPTY", "ANY" };
    
    while (rec1 != NULL) {                //agregar columnas
        addCol(nombreTabla3,rec1->nomc,enum_tipo[rec1->tipo],enum_calif[rec1->calif]);
        rec1 = rec1->sig;
    }
    rec2 = t2->col;
    while (rec2 != NULL) {
        if (rec2->calif != 0)
            addCol(nombreTabla3,rec2->nomc,enum_tipo[rec2->tipo],enum_calif[rec2->calif]);
        rec2 = rec2->sig;
    }
    
    
    t3 = buscarTabla(tablas,nombreTabla3);
    
    ColumnaP c3 = t3->col;
    
    rec1 = t1->col;
    rec2 = t2->col;

    string colTupla = rec1->nomc;   //conseguir columnasTupla
    rec1 = rec1->sig;
    while (rec1 != NULL) {
        colTupla = colTupla + ":" + rec1->nomc;
        rec1 = rec1->sig;
    }
    while (rec2 != NULL) {
        if (rec2->calif != 0)
            colTupla = colTupla + ":" + rec2->nomc;
        rec2 = rec2->sig;
    }

    rec1 = t1->col;    
    rec2 = t2->col;


    if (c1->tipo == 0) { // si es int
        ListaInt di1 = c1->li;
        ListaInt di2 = c2->li;
        
        string valTupla;
        int nivel1, nivel2, niv = 1;
        ListaInt auxInt;
        ListaStr auxStr;

        int algo = 0;
        while (di1 != NULL) {
            while (di2 != NULL && *di1->info != *di2->info)
                di2 = di2->sig;
            
            if (di2 != NULL) {
                auxInt = c1->li;
                nivel1 = verNivelInt(auxInt,di1);

                
                if (rec1->tipo == 0) {            //agregar primer valor de valoresTupla
                    auxInt = rec1->li;
                    while (niv < nivel1) {
                        auxInt = auxInt->sig;
                        niv++;
                    }
                    if (auxInt->info != NULL)
                        valTupla = to_string(*auxInt->info); //falta considerar caso NULL
                    else
                        valTupla = "";
                
                }
                else {
                    auxStr = rec1->ls;
                    while (niv < nivel1) {
                        auxStr = auxStr->sig;
                        niv++;
                    }
                    if (auxStr->info != NULL)
                        valTupla = *auxStr->info;   //falta considerar caso NULL
                    else
                        valTupla = "";
                }

                niv = 1;
                rec1 = rec1->sig;
                while (rec1 != NULL) {         //agregar el resto de valores de valoresTupla
                    if (rec1->tipo == 0) {
                        auxInt = rec1->li;
                        while (niv < nivel1) {
                            auxInt = auxInt->sig;
                            niv++;
                        }
                        if (auxInt->info != NULL)
                            valTupla = valTupla + ":" + to_string(*auxInt->info);
                        else
                            valTupla = valTupla + ":";
                    }
                    else {
                        auxStr = rec1->ls;
                        while (niv < nivel1) {
                            auxStr = auxStr->sig;
                            niv++;
                        }
                        if (auxStr->info != NULL)
                            valTupla = valTupla + ":" + *auxStr->info;
                        else
                            valTupla = valTupla + ":";
                    }
                    rec1 = rec1->sig;
                    niv = 1;
                }
                auxInt = c2->li;
                nivel2 = verNivelInt(auxInt,di2);

                while (rec2 != NULL) {
                    if (rec2->calif != 0) {
                        if (rec2->tipo == 0) {
                            auxInt = rec2->li;
                            while (niv < nivel2) {
                                auxInt = auxInt->sig;
                                niv++;
                            }
                            if (auxInt->info != NULL)
                                valTupla = valTupla + ":" + to_string(*auxInt->info);
                            else
                                valTupla = valTupla + ":";
                        }
                        else {
                            auxStr = rec2->ls;
                            while (niv < nivel2) {
                                auxStr = auxStr->sig;
                                niv++;
                            }
                            if (auxStr->info != NULL)
                                valTupla = valTupla + ":" + *auxStr->info;
                            else
                                valTupla = valTupla + ":";
                        }
                    }
                    rec2 = rec2->sig;
                    niv = 1;
                }
                
                insertInto(nombreTabla3,colTupla,valTupla);
                
                rec1 = t1->col;    
                rec2 = t2->col;
            }
            di1 = di1->sig;
            di2 = c2->li;
        }
    }
    else {   //si es string
        ListaStr ds1 = c1->ls;
        ListaStr ds2 = c2->ls;
        
        string valTupla;
        int nivel1, nivel2, niv = 1;
        ListaInt auxInt;
        ListaStr auxStr;

        while (ds1 != NULL) {
            while (ds2 != NULL && *ds1->info != *ds2->info)
                ds2 = ds2->sig;
            if (ds2 != NULL) {
                auxStr = c1->ls;
                nivel1 = verNivelStr(auxStr,ds1);

                if (rec1->tipo == 0) {            //agregar primer valor de valoresTupla
                    auxInt = rec1->li;
                    while (niv < nivel1) {
                        auxInt = auxInt->sig;
                        niv++;
                    }
                    if (auxInt->info != NULL)
                        valTupla = to_string(*auxInt->info); //falta considerar caso NULL
                    else
                        valTupla = "";
                }
                else {
                    auxStr = rec1->ls;
                    while (niv < nivel1) {
                        auxStr = auxStr->sig;
                        niv++;
                    }
                    if (auxStr->info != NULL)
                        valTupla = *auxStr->info;   //falta considerar caso NULL
                    else
                        valTupla = "";
                }

                rec1 = rec1->sig;
                niv = 1;
                while (rec1 != NULL) {         //agregar el resto de valores de valoresTupla
                    
                    if (rec1->tipo == 0) {
                        auxInt = rec1->li;
                        while (niv < nivel1) {
                            auxInt = auxInt->sig;
                            niv++;
                        }
                        if (auxInt->info != NULL)
                            valTupla = valTupla + ":" + to_string(*auxInt->info);
                        else
                            valTupla = valTupla + ":";
                    }
                    else {
                        auxStr = rec1->ls;
                        while (niv < nivel1) {
                            auxStr = auxStr->sig;
                            niv++;
                        }    
                        if (auxStr->info != NULL)
                            valTupla = valTupla + ":" + *auxStr->info;
                        else
                            valTupla = valTupla + ":";
                    }
                    rec1 = rec1->sig;
                    niv = 1;
                }
                auxStr = c2->ls;
                nivel2 = verNivelStr(auxStr,ds2);
                while (rec2 != NULL) {
                    if (rec2->calif != 0) {
                        if (rec2->tipo == 0) {
                            auxInt = rec2->li;
                            while (niv < nivel2) {
                                auxInt = auxInt->sig;
                                niv++;
                            }
                            if (auxInt->info != NULL)
                                valTupla = valTupla + ":" + to_string(*auxInt->info);
                            else
                                valTupla = valTupla + ":";
                        }
                        else {
                            auxStr = rec2->ls;
                            while (niv < nivel2) {
                                auxStr = auxStr->sig;
                                niv++;
                            }
                            if (auxStr->info != NULL)
                                valTupla = valTupla + ":" + *auxStr->info;
                            else
                                valTupla = valTupla + ":";
                        }
                    }
                    rec2 = rec2->sig;
                    niv = 1;
                }
                
                insertInto(nombreTabla3,colTupla,valTupla);
                
                rec1 = t1->col;    
                rec2 = t2->col;
            }
            ds1 = ds1->sig;
            ds2 = c2->ls;
        }
    }
    return OK;
}

bool mismoEsquema(TablaABB tab1, TablaABB tab2){
    if (contarCol(tab1->col) != contarCol(tab2->col))
        return false;
    ColumnaP auxc1, auxc2;
    auxc1 = tab1->col;
    while (auxc1 != NULL){
        auxc2 = buscarColumna(tab2->col,auxc1->nomc);
        if (auxc2 == NULL)
            return false;
        if (auxc1->calif == auxc2->calif && auxc1->tipo == auxc2->tipo)
            auxc1 = auxc1->sig;
        else
            return false;
    }
    return true;
}

TipoRet unionTable(string nombreTabla1, string nombreTabla2, string nombreTabla3){
    TablaABB tab1 = buscarTabla(tablas, nombreTabla1);
    if (nombreTabla1 == ""  || tab1 == NULL)
        return ERROR;
    TablaABB tab2 = buscarTabla(tablas, nombreTabla2);
    if (nombreTabla2 == "" || tab2 == NULL)
        return ERROR;
    TablaABB tab3 = buscarTabla(tablas, nombreTabla3);
    if (tab3 != NULL)
        return ERROR;
    if (!mismoEsquema(tab1,tab2))
        return ERROR;
    TipoRet X = createTable(nombreTabla3);
    tab3 = buscarTabla(tablas, nombreTabla3);
    ColumnaP auxc1, auxc2, auxc3;
    if (tab1->col == NULL && tab2->col == NULL)
        return OK;
    auxc1 = tab1->col;
    tab3->col = new nodoColumna;
    auxc3 = tab3->col;
    auxc3->calif = auxc1->calif;
    auxc3->nomc = auxc1->nomc;
    auxc3->tipo = auxc1->tipo;
    auxc3->li = NULL;
    auxc3->ls = NULL;
    auxc3->sig = NULL;
    auxc1 = auxc1->sig;
    while (auxc1 != NULL){
        auxc3->sig = new nodoColumna;
        auxc3 = auxc3->sig;
        auxc3->calif = auxc1->calif;
        auxc3->nomc = auxc1->nomc;
        auxc3->tipo = auxc1->tipo;
        auxc3->li = NULL;
        auxc3->ls = NULL;
        auxc3->sig = NULL;
        auxc1 = auxc1->sig;
    }
    auxc1 = tab1->col;
    ListaInt auxi1, auxi2, auxi3;
    ListaStr auxs1, auxs2, auxs3;
    while (auxc1 != NULL){
        if (auxc1->tipo == 0){
            auxi1 = auxc1->li;
            auxc3 = buscarColumna(tab3->col,auxc1->nomc);
            auxc3->li = new nodoListaInt;
            auxi3 = auxc3->li;
            if (auxi1->info != NULL){
                auxi3->info = new int;
                *auxi3->info = *auxi1->info;
            }
            else
                auxi3->info = NULL;
            auxi1 = auxi1->sig;
            while (auxi1 != NULL){
                auxi3->sig = new nodoListaInt;
                auxi3 = auxi3->sig;
                if (auxi1->info != NULL){
                    auxi3->info = new int;
                    *auxi3->info = *auxi1->info;
                }
                else
                    auxi3->info = NULL;
                auxi1 = auxi1->sig;
            }
            auxc2 = buscarColumna(tab2->col,auxc1->nomc);
            auxi2 = auxc2->li;
            while (auxi2 != NULL){
                auxi3->sig = new nodoListaInt;
                auxi3 = auxi3->sig;
                if (auxi2->info != NULL){
                    auxi3->info = new int;
                    *auxi3->info = *auxi2->info;
                }
                else
                    auxi3->info = NULL;
                auxi2 = auxi2->sig;
            }
            auxi3->sig = NULL;
        }
        else{
            auxs1 = auxc1->ls;
            auxc3 = buscarColumna(tab3->col,auxc1->nomc);
            auxc3->ls = new nodoListaStr;
            auxs3 = auxc3->ls;
            if (auxs1->info != NULL){
                auxs3->info = new string;
                *auxs3->info = *auxs1->info;
            }
            else
                auxs3->info = NULL;
            auxs1 = auxs1->sig;
            while (auxs1 != NULL){
                auxs3->sig = new nodoListaStr;
                auxs3 = auxs3->sig;
                if (auxs1->info != NULL){
                    auxs3->info = new string;
                    *auxs3->info = *auxs1->info;
                }
                else
                    auxs3->info = NULL;
                auxs1 = auxs1->sig;
            }
            auxc2 = buscarColumna(tab2->col,auxc1->nomc);
            auxs2 = auxc2->ls;
            while (auxs2 != NULL){
                auxs3->sig = new nodoListaStr;
                auxs3 = auxs3->sig;
                if (auxs2->info != NULL){
                    auxs3->info = new string;
                    *auxs3->info = *auxs2->info;
                }
                else
                    auxs3->info = NULL;
                auxs2 = auxs2->sig;
            }
            auxs3->sig = NULL;
        }
        auxc1 = auxc1->sig;
    }
    return OK;
}

bool mismaTupla(TablaABB tab1, int altura, TablaABB tab2){// dice si la tupla numero "altura" de tab1 esta
    if (!mismoEsquema(tab1,tab2))                         // en tab2
        return false;
    bool igual;
    int cont = 1, contA = 1, contB = 1;
    ColumnaP auxc1, auxc2;
    ListaInt auxi1, auxi2;
    ListaStr auxs1, auxs2;
    auxc1 = tab1->col;
    auxc2 = buscarColumna(tab2->col,auxc1->nomc);
    if (auxc1->tipo == 0){// si la primera columna es de tipo integer
        auxi1 = auxc1->li;
        auxi2 = auxc2->li;
        while (cont < altura){
            auxi1 = auxi1->sig;
            cont++;
        }
        while (auxi2 != NULL){//tomo la primera columna como referencia para recorrer todas las tuplas
            if (cont > 1)
                cont = 1;
            if (contB > 1)
                contB = 1;
            if (auxc1->tipo == 0){//si la columna actual es de tipo integer
                if ((auxi1->info == NULL && auxi2->info == NULL) || (auxi1->info != NULL && auxi2->info != NULL 
                && *auxi1->info == *auxi2->info )){//si ambos datos son iguales
                    auxc1 = auxc1->sig;
                    if (auxc1 == NULL)
                        return true;
                    if (auxc1->tipo == 0){// si la nueva columna es de tipo integer
                        auxi1 = auxc1->li;
                        while (cont < altura){
                            auxi1 = auxi1->sig;
                            cont++;
                        }
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxi2 = auxc2->li;
                        while (contB < contA){
                            auxi2 = auxi2->sig;
                            contB++;
                        }
                    }
                    else{// si la nueva columna es de tipo string
                        auxs1 = auxc1->ls;
                        while (cont < altura){
                            auxs1 = auxs1->sig;
                            cont++;
                        }
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxs2 = auxc2->ls;
                        while (contB < contA){
                            auxs2 = auxs2->sig;
                            contB++;
                        }
                    }
                }
                else{// si ambos datos son distintos
                    contA++;
                    if (auxc1 != tab1->col){
                        auxc1 = tab1->col;
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxi2 = auxc2->li;
                        while (contB < contA){
                            auxi2 = auxi2->sig;
                            contB++;
                        }
                        auxi1 = auxc1->li;
                        while (cont < altura){
                            auxi1 = auxi1->sig;
                            cont++;
                        }   
                    }
                    else
                        auxi2 = auxi2->sig;
                }
            }
            else{// si la columna actual es de tipo string
                if ((auxs1->info == NULL && auxs2->info == NULL) || (auxs1->info != NULL && auxs2->info != NULL 
                && *auxs1->info == *auxs2->info )){
                    auxc1 = auxc1->sig;
                    if (auxc1 == NULL)
                        return true;
                    if (auxc1->tipo == 0){
                        auxi1 = auxc1->li;
                        while (cont < altura){
                            auxi1 = auxi1->sig;
                            cont++;
                        }
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxi2 = auxc2->li;
                        while (contB < contA){
                            auxi2 = auxi2->sig;
                            contB++;
                        }
                    }
                    else{
                        auxs1 = auxc1->ls;
                        while (cont < altura){
                            auxs1 = auxs1->sig;
                            cont++;
                        }
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxs2 = auxc2->ls;
                        while (contB < contA){
                            auxs2 = auxs2->sig;
                            contB++;
                        }
                    }
                }
                else{
                    contA++;
                    if (auxc1 != tab1->col){
                        auxc1 = tab1->col;
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxi2 = auxc2->li;
                        while (contB < contA){
                            auxi2 = auxi2->sig;
                            contB++;
                        }
                        auxi1 = auxc1->li;
                        while (cont < altura){
                            auxi1 = auxi1->sig;
                            cont++;
                        }   
                    }
                    else
                        auxi2 = auxi2->sig;
                }
            }
        }
    }
    else{// si la primera columna es de tipo string
        auxs1 = auxc1->ls;
        auxs2 = auxc2->ls;
        while (cont < altura){
            auxs1 = auxs1->sig;
            cont++;
        }
        while (auxs2 != NULL){
            if (cont > 1)
                cont = 1;
            if (contB > 1)
                contB = 1;
            if (auxc1->tipo == 0){
                if ((auxi1->info == NULL && auxi2->info == NULL) || (auxi1->info != NULL && auxi2->info != NULL 
                && *auxi1->info == *auxi2->info )){
                    auxc1 = auxc1->sig;
                    if (auxc1 == NULL)
                        return true;
                    if (auxc1->tipo == 0){
                        auxi1 = auxc1->li;
                        while (cont < altura){
                            auxi1 = auxi1->sig;
                            cont++;
                        }
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxi2 = auxc2->li;
                        while (contB < contA){
                            auxi2 = auxi2->sig;
                            contB++;
                        }
                    }
                    else{
                        auxs1 = auxc1->ls;
                        while (cont < altura){
                            auxs1 = auxs1->sig;
                            cont++;
                        }
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxs2 = auxc2->ls;
                        while (contB < contA){
                            auxs2 = auxs2->sig;
                            contB++;
                        }
                    }
                }
                else{
                    contA++;
                    if (auxc1 != tab1->col){
                        auxc1 = tab1->col;
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxs2 = auxc2->ls;
                        while (contB < contA){
                            auxs2 = auxs2->sig;
                            contB++;
                        }
                        auxs1 = auxc1->ls;
                        while (cont < altura){
                            auxs1 = auxs1->sig;
                            cont++;
                        }   
                    }
                    else
                        auxs2 = auxs2->sig;
                }
            }
            else{
                if ((auxs1->info == NULL && auxs2->info == NULL) || (auxs1->info != NULL && auxs2->info != NULL 
                && *auxs1->info == *auxs2->info )){
                    auxc1 = auxc1->sig;
                    if (auxc1 == NULL)
                        return true;
                    if (auxc1->tipo == 0){
                        auxi1 = auxc1->li;
                        while (cont < altura){
                            auxi1 = auxi1->sig;
                            cont++;
                        }
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxi2 = auxc2->li;
                        while (contB < contA){
                            auxi2 = auxi2->sig;
                            contB++;
                        }
                    }
                    else{
                        auxs1 = auxc1->ls;
                        while (cont < altura){
                            auxs1 = auxs1->sig;
                            cont++;
                        }
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxs2 = auxc2->ls;
                        while (contB < contA){
                            auxs2 = auxs2->sig;
                            contB++;
                        }
                    }
                }
                else{
                    contA++;
                    if (auxc1 != tab1->col){
                        auxc1 = tab1->col;
                        auxc2 = buscarColumna(tab2->col, auxc1->nomc);
                        auxs2 = auxc2->ls;
                        while (contB < contA){
                            auxs2 = auxs2->sig;
                            contB++;
                        }
                        auxs1 = auxc1->ls;
                        while (cont < altura){
                            auxs1 = auxs1->sig;
                            cont++;
                        }   
                    }
                    else
                        auxs2 = auxs2->sig;
                }
            }
        }
    }
    return false;
}

TipoRet intersec(string nombreTabla1, string nombreTabla2, string nombreTabla3){
    TablaABB tab1 = buscarTabla(tablas, nombreTabla1);
    if (tab1 == NULL)
        return ERROR;
    TablaABB tab2 = buscarTabla(tablas, nombreTabla2);
    if (tab2 == NULL)
        return ERROR;
    TablaABB tab3 = buscarTabla(tablas, nombreTabla3);
    if (tab3 != NULL)
        return ERROR;
    TipoRet X = createTable(nombreTabla3);
    if (X == 1)
        return ERROR;
    tab3 = buscarTabla(tablas, nombreTabla3);
    if (tab1->col == NULL || tab2->col == NULL)
        return OK;
    if (!mismoEsquema(tab1,tab2))
        return ERROR;
    ColumnaP auxc1, auxc3;
    auxc1 = tab1->col;
    tab3->col = new nodoColumna;
    auxc3 = tab3->col;
    auxc3->calif = auxc1->calif;
    auxc3->tipo = auxc1->tipo;
    auxc3->nomc = auxc1->nomc;
    auxc3->li = NULL;
    auxc3->ls = NULL;
    auxc3->sig = NULL;
    auxc1 = auxc1->sig;
    while (auxc1 != NULL){
        auxc3->sig = new nodoColumna;
        auxc3 = auxc3->sig;
        auxc3->calif = auxc1->calif;
        auxc3->tipo = auxc1->tipo;
        auxc3->nomc = auxc1->nomc;
        auxc3->li = NULL;
        auxc3->ls = NULL;
        auxc3->sig = NULL;
        auxc1 = auxc1->sig;
    }
    if ((tab1->col->li == NULL && tab1->col->ls == NULL) || (tab2->col->li == NULL && tab2->col->ls == NULL))
        return OK;
    int altura = 1;
    if(tab1->col->tipo == 0){
        ListaInt auxi = tab1->col->li;
        while (auxi != NULL){
            if(mismaTupla(tab1,altura,tab2))
                copiarTupla(tab1, altura, tab3);
            auxi = auxi->sig;
            altura++;
        }
    }
    else{
        ListaStr auxs = tab1->col->ls;
        while (auxs != NULL){
            if(mismaTupla(tab1,altura,tab2))
                copiarTupla(tab1, altura, tab3);
            auxs = auxs->sig;
            altura++;
        }
    }
    return OK;
}

TipoRet menos(string nombreTabla1, string nombreTabla2, string nombreTabla3){
    TablaABB tab1 = buscarTabla(tablas, nombreTabla1);
    if (tab1 == NULL)
        return ERROR;
    TablaABB tab2 = buscarTabla(tablas, nombreTabla2);
    if (tab2 == NULL)
        return ERROR;
    TablaABB tab3 = buscarTabla(tablas, nombreTabla3);
    if (tab3 != NULL)
        return ERROR;
    TipoRet X = createTable(nombreTabla3);
    if (X == 1)
        return ERROR;
    tab3 = buscarTabla(tablas, nombreTabla3);
    if (tab1->col == NULL || tab2->col == NULL)
        return OK;
    if (!mismoEsquema(tab1,tab2))
        return ERROR;
    ColumnaP auxc1, auxc3;
    auxc1 = tab1->col;
    tab3->col = new nodoColumna;
    auxc3 = tab3->col;
    auxc3->calif = auxc1->calif;
    auxc3->tipo = auxc1->tipo;
    auxc3->nomc = auxc1->nomc;
    auxc3->li = NULL;
    auxc3->ls = NULL;
    auxc3->sig = NULL;
    auxc1 = auxc1->sig;
    while (auxc1 != NULL){
        auxc3->sig = new nodoColumna;
        auxc3 = auxc3->sig;
        auxc3->calif = auxc1->calif;
        auxc3->tipo = auxc1->tipo;
        auxc3->nomc = auxc1->nomc;
        auxc3->li = NULL;
        auxc3->ls = NULL;
        auxc3->sig = NULL;
        auxc1 = auxc1->sig;
    }
    if ((tab1->col->li == NULL && tab1->col->ls == NULL) || (tab2->col->li == NULL && tab2->col->ls == NULL))
        return OK;
    int altura = 1;
    if(tab1->col->tipo == 0){
        ListaInt auxi = tab1->col->li;
        while (auxi != NULL){
            if(!mismaTupla(tab1,altura,tab2))
                copiarTupla(tab1, altura, tab3);
            auxi = auxi->sig;
            altura++;
        }
    }
    else{
        ListaStr auxs = tab1->col->ls;
        while (auxs != NULL){
            if(!mismaTupla(tab1,altura,tab2))
                copiarTupla(tab1, altura, tab3);
            auxs = auxs->sig;
            altura++;
        }
    }
    return OK;  
}

ListaArreglos tuplasAArreglos(TablaABB tab){
    if (tab == NULL)
        return NULL;
    if (tab->col == NULL)
        return NULL;
    if (tab->col->li == NULL && tab->col->ls == NULL)
        return NULL;
    int contcol = 1;
    ColumnaP auxc = tab->col;
    auxc = auxc->sig;
    while (auxc != NULL){
        contcol++;
        auxc = auxc->sig;
    }
    ListaInt auxi;
    ListaStr auxs;
    int conttup = 1;
    if (tab->col->tipo == 0){
        auxi = tab->col->li;
        auxi = auxi->sig;
        while (auxi != NULL){
            auxi = auxi->sig;
            conttup++;
        }
    }
    else{
        auxs = tab->col->ls;
        auxs = auxs->sig;
        while (auxs != NULL){
            auxs = auxs->sig;
            conttup++;
        } 
    }
    auxc = tab->col;
    string dato;
    ListaArreglos lis;
    lis = new nodoListaArreglos;
    lis->A = new string [contcol];
    lis->sig = NULL;
    for (int i = 0; i < contcol; i++){
        if (auxc->tipo == 0){
            auxi = auxc->li;
            if (auxi->info == NULL)
                dato = "*VACIO*";
            else
                dato = to_string(*auxi->info);
        }
        else{
            auxs = auxc->ls;
            if (auxs->info == NULL)
                dato = "*VACIO*";
            else
                dato = *auxs->info;
        }
        lis->A[i] = dato;
        auxc = auxc->sig;
    }
    if (conttup == 1)
        return lis;
    ListaArreglos auxli = lis;
    int cont;
    for (int i = 2 ; i <= conttup; i++){
        auxc = tab->col;
        auxli->sig = new nodoListaArreglos;
        auxli = auxli->sig;
        auxli->A = new string [contcol];
        auxli->sig = NULL;
        cont = 1;
        for (int j = 0; j < contcol; j++){
            if (auxc->tipo == 0){
                auxi = auxc->li;
                while (cont < i){
                    auxi = auxi->sig;
                    cont++;
                }
                if (auxi->info == NULL)
                    dato = "*VACIO*";
                else
                    dato = to_string(*auxi->info);
            }
            else{
                auxs = auxc->ls;
                while (cont < i){
                    auxs = auxs->sig;
                    cont++;
                }
                if (auxs->info == NULL)
                    dato = "*VACIO*";
                else
                    dato = *auxs->info;  
            }
            cont = 1;
            auxli->A[j] = dato;
            auxc = auxc->sig;
        }
    }
    return lis;
}

ListaArreglos ordenarTuplas(ListaInt cols, ListaArreglos tuplas){
    if (cols == NULL)
        return tuplas;
    ListaInt auxi = cols;
    ListaArreglos auxla = tuplas;
    ListaArreglos cambiar;
    bool ordenado = false;
    int dato1, dato2;
    while (!ordenado){
        ordenado = true;
        while (auxla != NULL && auxla->sig != NULL){
            while (auxi != NULL && (auxla->A[*auxi->info] == auxla->sig->A[*auxi->info]))
                auxi = auxi->sig;
            if (auxi != NULL){
                if (esInt(auxla->A[*auxi->info]) && esInt(auxla->sig->A[*auxi->info])){
                    dato1 = stoi(auxla->A[*auxi->info]);
                    dato2 = stoi(auxla->sig->A[*auxi->info]);
                    if (dato1 > dato2){
                        if(auxla == tuplas){
                            auxla = auxla->sig;
                            tuplas->sig = auxla->sig;
                            auxla->sig = tuplas;
                            tuplas = auxla;
                        }
                        else{
                            cambiar = tuplas;
                            while (cambiar->sig != auxla)
                                cambiar = cambiar->sig;
                            cambiar->sig = auxla->sig;
                            auxla->sig = auxla->sig->sig;
                            cambiar->sig->sig = auxla;
                        }
                        ordenado = false;
                    }
                }
                else{
                    if (auxla->A[*auxi->info] > auxla->sig->A[*auxi->info]){
                        if(auxla == tuplas){
                            auxla = auxla->sig;
                            tuplas->sig = auxla->sig;
                            auxla->sig = tuplas;
                            tuplas = auxla;
                        }
                        else{
                            cambiar = tuplas;
                            while (cambiar->sig != auxla)
                                cambiar = cambiar->sig;
                            cambiar->sig = auxla->sig;
                            auxla->sig = auxla->sig->sig;
                            cambiar->sig->sig = auxla;
                        }
                        ordenado = false;
                    }
                }
            }
            auxi = cols;
            auxla = auxla->sig;
        }
        auxla = tuplas;
    }
    return tuplas;
}

void imprimirTabla(ListaStr colums, ListaArreglos tuplas){
    ListaStr auxs = colums;
    int cont = 0;
    while (auxs->sig != NULL){
        auxs = auxs->sig;
        cont++;
    }
    auxs = colums;
    for (int i = 0 ; i <= cont ; i++){
        cout << *auxs->info << "        ";
        auxs = auxs->sig;
    }
    cout << endl;
    while (tuplas != NULL){
        for (int i = 0 ; i <= cont ; i++){
            cout << tuplas->A[i] << "       " ;
        }
        cout << endl;
        tuplas = tuplas->sig;
    }
}

TipoRet printdatatable (string NombreTabla, string ordenadaPor){
    TablaABB tab = buscarTabla(tablas, NombreTabla);
    if (tab == NULL)
        return ERROR;
    if (tab->col == NULL || (tab->col->li == NULL && tab->col->ls == NULL)){
        cout << "No hay tuplas en "<< NombreTabla << endl;
        return OK;
    }
    ColumnaP auxc;
    if(ordenadaPor == ""){
        auxc = tab->col;
        while (auxc != NULL && auxc->calif != 0)
            auxc = auxc->sig;
        if (auxc != NULL)
            return printdatatable(NombreTabla, auxc->nomc);
    }
    ListaArreglos tuplas = tuplasAArreglos(tab);
    auxc = tab->col;
    ListaStr colums, auxs;//colums va a ser una lista con los nombres de las columnas
    colums =  new nodoListaStr;
    colums->info = new string;
    *colums->info = auxc->nomc;
    auxs = colums;
    auxc = auxc->sig;
    while (auxc != NULL){
        auxs->sig =  new nodoListaStr;
        auxs = auxs->sig;
        auxs->info = new string;
        *auxs->info = auxc->nomc;
        auxc = auxc->sig;
    }
    auxs->sig = NULL;
    ListaInt cols; //esta lista va a tener las posiciones de las columnas de ordenadaPor
    cols = new nodoListaInt;//Le asignamos una celda dummy
    ListaInt auxi = cols;
    int cont;
    string nomcol;
    while (ordenadaPor.length() != 0){
        if(ordenadaPor.find_first_of(":") == NOT_FIND){
            nomcol = ordenadaPor;
            ordenadaPor.erase(0,ordenadaPor.length());
        }
        else{
            nomcol = ordenadaPor.substr(0,ordenadaPor.find_first_of(":"));
            ordenadaPor.erase(0,ordenadaPor.find_first_of(":")+1);
        }
        cont = 0;
        auxc = tab->col;
        while (auxc != NULL && auxc->nomc != nomcol){
            cont++;
            auxc = auxc->sig;
        }
        if (auxc == NULL)// si no encuentra la columna con ese nombre
            return ERROR;
        auxi->sig = new nodoListaInt;
        auxi = auxi->sig;
        auxi->info = new int;
        *auxi->info = cont;
    }
    auxi->sig = NULL;
    auxi = cols;
    cols = cols->sig;
    delete auxi;//borramos la celda dummy
    tuplas = ordenarTuplas(cols,tuplas);
    cout << "           " << NombreTabla << " :" << endl;
    imprimirTabla(colums,tuplas);
    return OK;
}

//************************** M E N U ***************************

void opciones () {        
    cout<<"1- Operaciones sobre la base de datos"<<endl;
    cout<<"2- Operaciones para modificar una tabla"<<endl;
    cout<<"3- Operaciones para la edicion de datos"<<endl;
    cout<<"4- Operaciones entre tablas"<<endl;
    cout<<"5- Operaciones para la impresion de informacion"<<endl;
    cout<<"6- Operaciones adicionales del sistema (no implementadas)"<<endl;
    cout<<"7- Terminar"<<endl<<endl;
    cout<<"Ingrese una opcion: ";
}

void opciones1 () {
    
    int num;
    string nombre;
    TipoRet res;
    do {
        cout<<"1- Crear tabla"<<endl;
        cout<<"2- Eliminar tabla"<<endl;
        cout<<"3- Volver"<<endl<<endl;
        cout<<"Ingrese una opcion: ";
        cin>>num;
        switch (num) {

        case (1):           
            cout<<"Ingrese el nombre de la tabla a crear: "<<endl;
            cin>>nombre;
            res = createTable(nombre);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;

        case (2):
            cout<<"Ingrese el nombre de la tabla a eliminar: "<<endl;
            cin>>nombre;
            res = dropTable(nombre);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;      
            break;
        
        case (3):
            break;
        
        default: 
            cout<<"Ingrese una opcion valida: ";
            cin>>num;
            cout<<endl;
        }
    } while (num != 3);
}

void opciones2 () {
    
    int num;
    string nombret, nombrec, tipoc, califc, nombren;
    TipoRet res;

    do {
        cout<<"1- Añadir columna"<<endl;
        cout<<"2- Eliminar columna"<<endl;
        cout<<"3- Alterar columna"<<endl;
        cout<<"4- Volver"<<endl<<endl;
        cout<<"Ingrese una opcion: ";
        cin >> num;
        switch (num) {

        case (1):
            cout<<"Ingrese el nombre de la tabla: "<<endl;
            cin>>nombret;
            cout<<"Ingrese el nombre de la columna a agregar: "<<endl;
            cin>>nombrec;
            cout<<"Ingrese el tipo de dato de la columna: "<<endl;
            cin>>tipoc;
            cout<<"Ingrese el calificador de la columna: "<<endl;
            cin>>califc;

            res = addCol(nombret,nombrec,tipoc,califc);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;

        case (2):
            cout<<"Ingrese el nombre de la tabla: "<<endl;
            cin>>nombret;
            cout<<"Ingrese el nombre de la columna a eliminar: "<<endl;
            cin>>nombrec;

            res = dropCol(nombret,nombrec);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;
        
        case (3):
            cout<<"Ingrese el nombre de la tabla: "<<endl;
            cin>>nombret;
            cout<<"Ingrese el nombre de la columna que quiere modificar: "<<endl;
            cin>>nombrec;
            cout<<"Ingrese el nuevo tipo de dato de la columna: "<<endl;
            cin>>tipoc;
            cout<<"Ingrese el nuevo calificador de la columna: "<<endl;
            cin>>califc;
            cout<<"Ingrese el nuevo nombre de la columna: "<<endl;
            cin>>nombren;

            res = alterCol(nombret,nombrec,tipoc,califc,nombren);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;
        
        case (4):
            break;
        
        default: 
            cout<<"Ingrese una opcion valida: ";
            cin>>num;
            cout<<endl;
        }
    } while (num != 4);
}

void opciones3 () {
    
    int num;
    string nombret, nombrec, valorest, condicion, valor;
    TipoRet res;
    do {
        cout<<"1- Insertar tupla"<<endl;
        cout<<"2- Eliminar tupla"<<endl;
        cout<<"3- Modificar tupla"<<endl;
        cout<<"4- Volver"<<endl<<endl;
        cout<<"Ingrese una opcion: ";
        cin >> num;
        switch (num) {

        case (1):
            cout<<"Ingrese el nombre de la tabla: "<<endl;
            cin>>nombret;
            cout<<"Ingrese el nombre de las columnas: "<<endl;
            cin>>nombrec;
            cout<<"Ingrese los valores de las columnas nombradas: "<<endl;
            cin>>valorest;
        
            res = insertInto(nombret,nombrec,valorest);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
        
            break;

        case (2):
            cout<<"Ingrese el nombre de la tabla: "<<endl;
            cin>>nombret;
            cout<<"Ingrese la condicion para eliminar la tupla: "<<endl;
            cin>>condicion;

            res = deleteTupla(nombret,condicion);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;
        
        case (3):
            cout<<"Ingrese el nombre de la tabla: "<<endl;
            cin>>nombret;
            cout<<"Ingrese el nombre de la columna que quiere modificar: "<<endl;
            cin>>nombrec;
            cout<<"Ingrese la condicion para que se modifique la columna: "<<endl;
            cin>>condicion;
            cout<<"Ingrese el nuevo valor: "<<endl;
            cin>>valor;
            
            res = update(nombret,condicion,nombrec,valor);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;
        
        case (4):
            break;
        
        default: 
            cout<<"Ingrese una opcion valida: ";
            cin>>num;
            cout<<endl;
        }
    } while (num != 4);
}  
 
void opciones4 () {
    
    int num;
    string nombre1, nombre2, condicion, nombrec, nombre3;
    TipoRet res;
    do {
        cout<<"1- Seleccion"<<endl;
        cout<<"2- Proyeccion"<<endl;
        cout<<"3- Join"<<endl;
        cout<<"4- Union"<<endl;
        cout<<"5- Interseccion"<<endl;
        cout<<"6- Diferencia"<<endl;
        cout<<"7- Volver"<<endl<<endl;
        cout<<"Ingrese una opcion: ";
        cin >> num;
        switch (num) {

        case (1):
            cout<<"Ingrese el nombre de la tabla 1: "<<endl;
            cin>>nombre1;
            cout<<"Ingrese la condicion: "<<endl;
            cin>>condicion;
            cout<<"Ingrese el nombre de la nueva tabla: "<<endl;
            cin>>nombre2;
                        
            res = selectWhere(nombre1,condicion,nombre2);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;

        case (2):
            cout<<"Ingrese el nombre de la tabla 1: "<<endl;
            cin>>nombre1;
            cout<<"Ingrese las columnas: "<<endl;
            cin>>nombrec;
            cout<<"Ingrese el nombre de la nueva tabla: "<<endl;
            cin>>nombre2;

                    
            res = select(nombre1,nombrec,nombre2);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            
            break;
        
        case (3):
            cout<<"Ingrese el nombre de la primera tabla: "<<endl;
            cin>>nombre1;
            cout<<"Ingrese el nombre de la segunda tabla: "<<endl;
            cin>>nombre2;
            cout<<"Ingrese el nombre de la nueva tabla: "<<endl;
            cin>>nombre3;
                        
            res = join(nombre1,nombre2,nombre3);
            
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            
            break; 
        
        case (4):
            cout<<"Ingrese el nombre de la primera tabla: "<<endl;
            cin>>nombre1;
            cout<<"Ingrese el nombre de la segunda tabla: "<<endl;
            cin>>nombre2;
            cout<<"Ingrese el nombre de la nueva tabla: "<<endl;
            cin>>nombre3;

               
            res = unionTable(nombre1,nombre2,nombre3);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            
            break;

        case (5):
            cout<<"Ingrese el nombre de la primera tabla: "<<endl;
            cin>>nombre1;
            cout<<"Ingrese el nombre de la segunda tabla: "<<endl;
            cin>>nombre2;
            cout<<"Ingrese el nombre de la nueva tabla: "<<endl;
            cin>>nombre3;

                      
            res = intersec(nombre1,nombre2,nombre3);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            
            break;
        
        case (6):
            cout<<"Ingrese el nombre de la primera tabla: "<<endl;
            cin>>nombre1;
            cout<<"Ingrese el nombre de la segunda tabla: "<<endl;
            cin>>nombre2;
            cout<<"Ingrese el nombre de la nueva tabla: "<<endl;
            cin>>nombre3;
                        
                       
            res = menos(nombre1,nombre2,nombre3);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            
            break;
        
        case (7):
            break;
        
        default: 
            cout<<"Ingrese una opcion valida: ";
            cin>>num;
            cout<<endl;
        }
    } while (num != 7);
}

void opciones5 () {
    
    int num;
    string nombret, orden;
    TipoRet res;
    do {
        cout<<"1- Listar tablas"<<endl;
        cout<<"2- Listar esquema"<<endl;
        cout<<"3- Listar tabla"<<endl;
        cout<<"4- Volver"<<endl<<endl;
        cout<<"Ingrese una opcion: ";
        cin >> num;
        
        switch (num) {

        case (1):
            res = printTables();
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;

        case (2):
            cout<<"Ingrese el nombre de la tabla: "<<endl;
            cin>>nombret;
            
            res = printMetadata(nombret);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;  
            break;
        
        case (3):
            cout<<"Ingrese el nombre de la tabla: "<<endl;
            cin>>nombret;
            cout<<"Ingrese el orden: "<<endl;
            cin>>orden;

            res = printdatatable(nombret,orden);
            if (res == 0)
                cout<<"OK"<<endl<<endl;
            else
                cout<<"ERROR"<<endl<<endl;
            break;
        
        case (4):
            break;
        
        default: 
            cout<<"Ingrese una opcion valida: ";
            cin>>num;
            cout<<endl;
        }
    } while (num != 4);
}

void opciones6 () {
    int num;
    TipoRet res;
    do {
        cout<<endl;
        cout<<"1- Deshacer"<<endl;
        cout<<"2- Rehacer"<<endl;
        cout<<"3- Comenzar transaccion"<<endl;
        cout<<"4- Finalizar transaccion"<<endl;
        cout<<"5- Volver"<<endl<<endl;
        cout<<"Ingrese una opcion: ";
        cin>>num;
        switch (num) {

        case (1):           
            cout<<"NO_IMPLEMENTADA"<<endl;
            break;

        case (2):
            cout<<"NO_IMPLEMENTADA"<<endl;
            break;      
        
        case (3):
            cout<<"NO_IMPLEMENTADA"<<endl;
            break;
        
        case (4):
            cout<<"NO_IMPLEMENTADA"<<endl;
            break;

        case (5):
            break;

        default: 
            cout<<"Ingrese una opcion valida: ";
            cin>>num;
            cout<<endl;
        }
    } while (num != 5);
}
//************************** M A I N ****************************

int main(){
    int num;
        do {
            opciones();
            cin >> num;
            switch (num) {

            case (1):
                opciones1();
                break;
            
            case (2):
                opciones2();
                break;
            
            case (3):
                opciones3();
                break;

            case (4):
                opciones4();
                break;
            
            case (5):
                opciones5();
                break;
            
            case (6):
                opciones6();
                break;
            
            case (7):
                cout << "Nos vemos!!" << endl;
                break;
            
            default: 
                cout<<"Ingrese una opcion valida: ";
                cin>>num;
                cout<<endl;
            }        
        } while (num != 7);

    return 0;
}