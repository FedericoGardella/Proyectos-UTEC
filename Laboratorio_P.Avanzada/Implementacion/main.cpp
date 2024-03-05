#include <iostream>
#include <sstream>
#include "clases/.h/Fabrica.h"
#include "clases/.h/ISistema.h"


using namespace std;


void obtenerFecha(string& fechaString, int& dia, int& mes, int& anio) {
    istringstream iss(fechaString);
    char delimiter = '/';
    string token;

    getline(iss, token, delimiter);
    dia = stoi(token);

    getline(iss, token, delimiter);
    mes = stoi(token);

    getline(iss, token, delimiter);
    anio = stoi(token);
}


int main(){
    
    ISistema* sis = Fabrica::getSistema();
    string opcion;
    int op = 10;
    int op2;
    string variableString1, variableString2, variableString3, variableString4;
    float variableFloat1, variableFloat2;
    int variableInt1, variableInt2, variableInt3;
    int dia, mes, anio;
    string fechaString;
    string* arregloStrings;
    string *p1, *p2, *p4, *p5, *p6, *p7;
    Date* p3;
    string *v1, *v2, *v9, *v10;
    int *v3, *v8;
    float *v4, *v5;
    Date *v6, *v7;
    int op3 = 3;
    bool mismas;
    cout <<" ||=====================================||" << endl;
    cout <<" ||==== ¿Desea realizar la precarga? ===|| \n";
    cout <<" ||=====================================|| \n";
    cout <<" ||=1-Si ===============================|| \n";
    cout <<" ||=2-No ===============================|| \n";
    cout <<" ||=====================================|| \n";
    getline(cin, opcion);
    op3 = stoi(opcion);
    if (op3 == 1)
        sis->cargarPrecarga();
    system("clear");
    do{
        cout <<" ||======================================|| \n";
        cout <<" ||================ MENU ================|| \n";
        cout <<" ||======================================|| \n";
        cout <<" ||=1-Alta Oferta Laboral ===============|| \n";
        cout <<" ||=2-Alta Entrevista ===================|| \n";
        cout <<" ||=3-Inscripcion Oferta Laboral ========|| \n";
        cout <<" ||=4-Listar Ofertas Activas ============|| \n";
        cout <<" ||=5-Consultar datos de Estudiante =====|| \n";
        cout <<" ||=6-Asignacion de Oferta a Estudiante =|| \n";
        cout <<" ||=7-Modificar Estudiante ==============|| \n";
        cout <<" ||=8-Modificar Llamado =================|| \n";
        cout <<" ||=9-Dar de baja Llamado ===============|| \n";
        cout <<" ||=0-Salir =============================|| \n";
        cout <<" ||======================================|| \n\n\n";
        getline(cin, opcion);
        op = stoi(opcion);
        system("clear");
        if (op == 1){
            sis->listarEmpresas();
            cout <<"\n\n\tIngrese el rut de la Empresa\n\n";
            getline(cin, variableString1);
            sis->seleccionarEmpresa(variableString1);
            cout <<"\n\n\tIngrese el nombre de la Sucursal\n\n";
            getline(cin, variableString1);
            sis->seleccionarSucursal(variableString1);
            cout <<"\n\n\tIngrese el nombre de la Seccion\n\n";
            getline(cin,variableString1);
            sis->seleccionarSeccion(variableString1);
            cout <<"\n\n\tIngrese el numero de Expediente de la nueva Oferta Laboral\n\n";
            getline(cin,variableString1);
            cout <<"\n\n\tIngrese el titulo\n\n";
            getline(cin,variableString2);
            cout <<"\n\n\tIngrese la descripcion\n\n";
            getline(cin,variableString3);
            cout <<"\n\n\tIngrese la cantidad de horas semanales\n\n";
            getline(cin, opcion);
            variableInt1 = stoi(opcion);
            cout <<"\n\n\tIngrese el salario minimo\n\n";
            getline(cin, opcion);
            variableFloat1 = stof(opcion);
            cout <<"\n\n\tIngrese el salario maximo\n\n";
            getline(cin, opcion);
            variableFloat2 = stof(opcion);
            cout <<"\n\n\tIngrese la fecha de comienzo en formato dd/mm/aaaa\n\n";
            getline(cin,fechaString);
            obtenerFecha(fechaString,dia,mes,anio);
            Date fecha1 = Date(dia,mes,anio);
            cout <<"\n\n\tIngrese la fecha de finalizacion en formato dd/mm/aaaa\n\n";
            getline(cin,fechaString);
            obtenerFecha(fechaString,dia,mes,anio);
            Date fecha2 = Date(dia,mes,anio);
            cout <<"\n\n\tIngrese la cantidad de puestos\n\n";
            getline(cin, opcion);
            variableInt2 = stoi(opcion);
            cout <<"\n\n\tIngrese la cantidad de asignaturas requeridas\n\n";
            getline(cin, opcion);
            variableInt3 = stoi(opcion);
            sis->listarAsignaturas();
            cout <<"\n\n\tIngrese los codigos de asignatura separados por Enter\n\n";
            arregloStrings = new string[variableInt3];
            for (int i = 0; i < variableInt3; i++){
                getline(cin, variableString4);
                arregloStrings[i] = variableString4;
            }
            DtOfertaLite ofl(&variableString1, &variableString2, &variableString3, &variableInt1, &variableFloat1, &variableFloat2, &fecha1, &fecha2, &variableInt2);
            DtOferta of(&ofl,arregloStrings,variableInt3);
            sis->altaOferta(of);
            cout <<"\n\n\tAlta de Oferta completada, pesione Enter para volver al Menu principal\n\n";
            getchar();
            system("clear");
        }
        if (op == 2){
            sis->mostrarOfertasLaborales();
            cout <<"\n\n\tIngrese el codigo de la Oferta Laboral\n\n";
            getline(cin, variableString1);
            sis->elegirOfertaL(variableString1);
            cout <<"\n\n\tIngrese la cedula del Estudiante en 7 digitos sin puntos ni guiones\n\n";
            getline(cin, variableString1);
            cout <<"\n\n\tIngrese la fecha de la entrevista en formato dd/mm/aaaa\n\n";
            getline(cin, fechaString);
            obtenerFecha(fechaString,dia,mes,anio);
            Date fecha = Date(dia,mes,anio);
            sis->seleccionarEstudianteyFecha(variableString1,fecha);
            cout <<"\n\n\tAlta de Entrevista completada, pesione Enter para volver al Menu principal\n\n";
            getchar();
            system("clear");
        }
        if (op == 3){
            sis->listarOfertasActivas();
            cout <<"\n\n\tIngrese el codigo de la Oferta Laboral\n\n";
            getline(cin, variableString1);
            sis->elegirOferta(variableString1);
            cout <<"\n\n\tIngrese la cedula del Estudiante en 7 digitos sin puntos ni guiones\n\n";
            getline(cin, variableString1);
            if (sis->mismasAsignaturas(variableString1)){
                sis->seleccionarEstudiante(variableString1);
                cout <<"\n\n\tInscripcion a Oferta Laboral completada, pesione Enter para volver al Menu principal\n\n";
                getchar();
                system("clear");
            }
            else{
                cout <<"\n\n\tNo tiene las asignaturas requeridas\n\n";
                getchar();
                system("clear");
            }
        }
        if (op == 4){
            sis->listarOfertasActivas();
            cout <<"\n\n\tPresione Enter para volver al Menu principal\n\n";
            getchar();
            system("clear");
        }
        if (op == 5){
            sis->verEstudiante();
            cout <<"\n\n\tIngrese la cedula del Estudiante en 7 digitos sin puntos ni guiones\n\n";
            getline(cin, variableString1);
            sis->seleccEstudiante(variableString1);
            cout <<"\n\n\tPresione Enter para volver al Menu principal\n\n";
            getchar();
            system("clear");
        }
        if (op == 6){
            sis->mostrarOfertasLaborales();
            cout <<"\n\n\tIngrese el codigo de la Oferta Laboral\n\n";
            getline(cin, variableString1);
            sis->elegirOfertaL(variableString1);
            cout <<"\n\n\tIngrese la cedula del Estudiante en 7 digitos sin puntos ni guiones\n\n";
            getline(cin, variableString1);
            cout <<"\n\n\tIngrese el salario\n\n";
            getline(cin, opcion);
            variableFloat1 = stof(opcion);
            sis->seleccionaEstudiante(variableString1,variableFloat1);
            cout <<"\n\n\tAsignacion de Oferta a Estudiante completada, pesione Enter para volver al Menu principal\n\n";
            getchar();
            system("clear");
        }
        if (op == 7){
            sis->verEstudiante();
            cout <<"\n\n\tIngrese la cedula del Estudiante en 7 digitos sin puntos ni guiones\n\n";
            getline(cin, variableString1);
            sis->selecEstudiante(variableString1);
            cout <<"\n\n\tElija una de las siguientes opciones:\n\n";
            p1 = p2 = p4 = p5 = p6 = p7 = NULL;
            p3 = NULL;
            do{
                op2 = 9;
                cout <<" 1- Cambiar nombre\n";
                cout <<" 2- Cambiar telefono\n";
                cout <<" 3- Cambiar fecha de nacimiento\n";
                cout <<" 4- Agregar Asignatura\n";
                cout <<" 5- Eliminar Asignatura\n";
                cout <<" 6- Agregar Carrera\n";
                cout <<" 7- Eliminar Carrera\n";
                cout <<" 0- Terminar\n";
                getline(cin, opcion);
                op2 = stoi(opcion);
                if (op2 == 1){
                    p1 = new string;
                    cout <<" Ingrese el nuevo nombre\n\n";
                    getline(cin, variableString1);
                    *p1 = variableString1;
                }
                if (op2 == 2){
                    p2 = new string;
                    cout <<" Ingrese el nuevo telefono\n\n";
                    getline(cin, variableString1);
                    *p2 = variableString1;
                }
                if (op2 == 3){
                    cout <<" Ingrese la nueva fecha de nacimiento en formato dd/mm/aaaa\n\n";
                    getline(cin, fechaString);
                    obtenerFecha(fechaString,dia,mes,anio);
                    Date *fecha = new Date(dia,mes,anio);
                    p3 = fecha;
                }
                if (op2 == 4){
                    p4 = new string;
                    cout <<" Ingrese el codigo de la asignatura a agregar\n\n";
                    getline(cin, variableString1);
                    *p4 = variableString1;
                }
                if (op2 == 5){
                    p5 = new string;
                    cout <<" Ingrese el codigo de la asignatura a eliminar\n\n";
                    getline(cin, variableString1);
                    *p5 = variableString1;
                }
                if (op2 == 6){
                    p6 = new string;
                    cout <<" Ingrese el codigo de la carrera a agregar\n\n";
                    getline(cin, variableString1);
                    *p6 = variableString1;   
                }
                if (op2 == 7){
                    p7 = new string;
                    cout <<" Ingrese el codigo de la carrera a eliminar\n\n";
                    getline(cin, variableString1);
                    *p7 = variableString1;
                }
            }
            while (op2 != 0);
            DtEstudiante* est1 = new DtEstudiante(NULL,p1);
            DtEstudiantePlus* estu1 = new DtEstudiantePlus(p3, p2, NULL, est1);
            DtNuevoEstudiante estud(estu1, p4, p5, p6, p7);
            sis->modificarEstudiante(estud);
            delete est1;
            delete estu1;
            delete p1;
            delete p2;
            delete p4;
            delete p5;
            delete p6;
            delete p7;
            cout <<"\n\n\tModificacion de Estudiante completada, pesione Enter para volver al Menu principal\n\n";
            getchar();
            system("clear");
        }
        if (op == 8){
            sis->mostrarOfertasLaborales();
            cout <<"\n\n\tIngrese el numero de expediente de la Oferta\n\n";
            getline(cin, variableString1);
            sis->elegirOfertaLaboral(variableString1);
            cout <<"\n\n\tElija una de las siguientes opciones:\n\n";
            v1 = v2 = v9 = v10 = NULL;
            v3 = v8 = NULL;
            v4 = NULL;
            v5 = NULL;
            v6 = v7 = NULL;
            do{
                op2 = 11;
                cout <<" 1- Cambiar titulo\n";
                cout <<" 2- Cambiar descripcion\n";
                cout <<" 3- Cambiar cantidad de horas semanales\n";
                cout <<" 4- Cambiar salario minimo\n";
                cout <<" 5- Cambiar salario maximo\n";
                cout <<" 6- Cambiar fecha de inicio\n";
                cout <<" 7- Cambiar fecha de finalizacion\n";
                cout <<" 8- Cambiar cantidad de puestos\n";
                cout <<" 9- Agregar Asignatura\n";
                cout <<" 10- Eliminar Asignatura\n";
                cout <<" 0- Terminar\n";
                getline(cin, opcion);
                op2 = stoi(opcion);
                if (op2 == 1){
                    v1 = new string;
                    cout <<" Ingrese el nuevo titulo\n\n";
                    getline(cin, variableString1);
                    *v1 = variableString1;
                }
                if (op2 == 2){
                    v2 = new string;
                    cout <<" Ingrese la nueva descripcion\n\n";
                    getline(cin, variableString1);
                    *v2 = variableString1;
                }
                if (op2 == 3){
                    v3 = new int;
                    cout <<" Ingrese la nueva cantidad de horas semanales\n\n";
                    getline(cin, opcion);
                    variableInt1 = stoi(opcion);
                    *v3 = variableInt1;
                }
                if (op2 == 4){
                    v4 = new float;
                    cout <<" Ingrese el nuevo salario minimo\n\n";
                    getline(cin, opcion);
                    variableFloat1 = stof(opcion);
                    *v4 = variableFloat1;
                }
                if (op2 == 5){
                    v5 = new float;
                    cout <<" Ingrese el nuevo salario maximo\n\n";
                    getline(cin, opcion);
                    variableFloat1 = stof(opcion);
                    *v5 = variableFloat1;
                }
                if (op2 == 6){
                    cout <<" Ingrese la nueva fecha de inicio en formato dd/mm/aaaa\n\n";
                    getline(cin, fechaString);
                    obtenerFecha(fechaString,dia,mes,anio);
                    Date* fechaA = new Date(dia,mes,anio);
                    v6 = fechaA;
                }
                if (op2 == 7){
                    cout <<" Ingrese la nueva fecha de finalizacion en formato dd/mm/aaaa\n\n";
                    getline(cin, fechaString);
                    obtenerFecha(fechaString,dia,mes,anio);
                    Date* fechaB = new Date(dia,mes,anio);
                    v7 = fechaB;
                }
                if (op2 == 8){
                    v8 = new int;
                    cout <<" Ingrese la nueva cantidad de puestos\n\n";
                    getline(cin, opcion);
                    variableInt1 = stoi(opcion);
                    *v8 = variableInt1;
                }
                if (op2 == 9){
                    v9 = new string;
                    cout <<" Ingrese el codigo de la asignatura a agregar\n\n";
                    getline(cin, variableString1);
                    *v9 = variableString1;
                }
                if (op2 == 10){
                    v10 = new string;
                    cout <<" Ingrese el codigo de la asignatura a eliminar\n\n";
                    getline(cin, variableString1);
                    *v10 = variableString1;
                }
            }
            while (op2 != 0);
            DtOfertaLite* ofli = new DtOfertaLite(NULL, v1, v2, v3, v4, v5, v6, v7, v8);
            DtModOferta mod(ofli, v9, v10);
            sis->modificarOfertaLaboral(mod);
            delete ofli;
            delete v1;
            delete v2;
            delete v3;
            delete v4;
            delete v5;
            delete v8;
            delete v9;
            delete v10;
            cout <<"\n\n\tModificacion de Oferta completada, pesione Enter para volver al Menu principal\n\n";
            getchar();
            system("clear");
        }
        if (op == 9){
            sis->mostrarOfertasLaborales();
            cout <<"\n\n\tIngrese el codigo de la Oferta Laboral\n\n";
            getline(cin, variableString1);
            sis->eliminarOfertaLaboral(variableString1);
            cout <<"\n\n\tOferta Laboral eliminada, pesione Enter para volver al Menu principal\n\n";
            getchar();
            system("clear");
        }
    }
    while (op != 0);
    return 0;
}


