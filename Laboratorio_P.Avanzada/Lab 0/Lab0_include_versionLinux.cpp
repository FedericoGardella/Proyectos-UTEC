#include <iostream>
#include <string>
#include <cmath>

#include "clase_Paga.h"
#include "clase_Empleado.h"
#include "clase_Fijo_Jornalero.h"
#include "clase_Empresa.h"
#include "clase_Cambio.h"

using namespace std;

int devolver_entero(){    
    string valor;
    int resultado;
    bool valido;
    do{       
        cin>>valor;
        try{
            resultado = stoi(valor);
            valido = true;
        }catch(invalid_argument& ia){
            valido=false;
            system("clear");
            cout<<"Valor incorrecto, Intente nuevamente"<<endl;
        }
    }while(valido==false);
    return resultado;
}


void menuPrincipal () {
    cout << "\n	Elija una opcion por favor:" << endl << endl;
    cout << "   1--> Menu ejercicio 1" << endl;
    cout << "   2--> Menu ejercicio 2" << endl;
    cout << "   0--> Salir" << endl;

}

void menuMenu1() {
    cout<< "MENU 1"<<endl;
    cout<< "\n"<<endl;
    cout<< "   1-->Agregar paga por defecto"<<endl;
    cout<< "   2-->Agregar paga con valores"<<endl;
    cout<< "   3-->Cambiar a dolar"<<endl;
    cout<< "   4-->Cambiar a peso"<<endl;
    cout<< "   5-->Editar paga"<<endl;
    cout<< "   6-->Multiplicar paga"<<endl;
    cout<< "   7-->Sumar paga"<<endl;
    cout<< "   8-->Mostrar pagas"<<endl;
    cout<< "   0-->Salir"<<endl;
}

void menuGeneral (){
    cout<<"\n"<<endl;
    cout <<"    ***********************************************" <<endl;
    cout <<"    ***** SISTEMA LIQUIDACION DE SUELDOS BCGV *****" << endl;
    cout <<"    ***********************************************" <<endl;
    cout << "\n	Elija una opcion por favor:" << endl << endl;
    cout << "   1--> Crear Empresa" << endl;
    cout << "   2--> Contrtatar Empleado" << endl;
    cout << "   3--> Mostrar Empleados Contratados" << endl;
    cout << "   4--> Calcular total por concepto de sueldo" << endl;
    cout << "   5--> Eliminar empresa" << endl;
    cout << "   0--> Salir" << endl;
    cout << "\n";
}	

void menu_General (){
}

void evitar_repetidos(){ //Función que permite evidenciar nomnbres repetidos entre los empleados de una empresa para evitar el re-ingreso
}

long long ingresar_rut() {
    long long rut;
    do {
        cout << "Ingrese RUT de la Empresa (12 dígitos), sin puntos no guiones: " << endl;
        cin >> rut;
        if (rut < 100000000000 || rut >= 1000000000000) {
            cout << "Error: El RUT debe tener exactamente 12 dígitos. Inténtelo nuevamente." << endl;
        }
    } while (rut < 100000000000 || rut >= 1000000000000);
    return rut;
}

int main() {     
	int opcion, edad, op, horas, op1, op2, op3;   
    long long rut2; 
    float monto;
    Moneda moneda;
	string nombre, condicionT, nombre_legal, nom_emp, ci;
    Paga valor_hora;
    int numEmpresas = 0;
    Empleado * em;
    Empresa** empresas = new Empresa* [MAX_EMPRESAS];
    Empleado** funcionarios = new Empleado* [MAX_EMPLEADO];
    Empresa * e;
    Paga t;
    float total_sueldos_peso = 0;
    Paga arrPaga [30];
    int numPaga = 0;
    //Precarga:
    e = new Empresa("Sabores", "SaboresSA", 123456789012);
    empresas[numEmpresas] = e;
    numEmpresas++;
    e = new Empresa("LoDeManolo", "LoDeManoloSA", 123456789010);
    empresas[numEmpresas] = e;
    numEmpresas++;
    e = empresas[1];
    valor_hora.setMonto(100);
    valor_hora.setMoneda(Moneda::us);
    em = new Fijo("Jose Perez","59346184", 50, valor_hora);
    e->contratarEmpleado(em);
    valor_hora.setMonto(3);
    valor_hora.setMoneda(Moneda::usd);
    em = new Fijo("Luis Rodriguez", "40284591", 47, valor_hora);
    e->contratarEmpleado(em);
    valor_hora.setMonto(120);
    valor_hora.setMoneda(Moneda::us);
    em = new Jornalero("Fabian Estevez", "50092384", 30, valor_hora, 30);
    e->contratarEmpleado(em);
    valor_hora.setMonto(5);
    valor_hora.setMoneda(Moneda::usd);
    em = new Jornalero("Georgina Moreira", "46294710", 27, valor_hora, 25);
    e->contratarEmpleado(em);
    const int max_empresas = 100;
    //Fin precarga
    do {
        menuPrincipal ();
        cin.sync();
        opcion = devolver_entero();
        system("clear");
        switch(opcion) {
            case 1:
                do {
                    menuMenu1();
                    cin.sync();
                    op = devolver_entero();
                    switch (op) {
                        case 1:
                            arrPaga[numPaga] = Paga();
                            numPaga++;
                            system("clear");
                            cout << "- Paga ingresada exitosamente -" << endl<< endl;                            
                            break;
                        case 2:
                            system("clear");
                            cout << "Ingrese monto: " << endl;
                            cin >> monto;
                            cout << "Ingrese moneda (1 para peso, 2 para dolar): " << endl;
                            cin >> op2;
                            if (op2 == 1) {
                                moneda = Moneda::us;
                            } else {
                                moneda = Moneda::usd;
                            }
                            arrPaga[numPaga] = Paga(monto, moneda);
                            numPaga++;
                            system("clear");
                            cout << "- Paga ingresada exitosamente -" << endl<< endl;
                            break;
                            
                        case 3:
                            system("clear");
                            cout << "Ingrese la paga que desea cambiar a dolares" << endl;
                            cout << "* Pagas existentes * " << endl<<endl;
                            for (int i = 0; i < numPaga; i++) {
                                cout << "Paga " << i << ": " << endl;
                                std::cout << arrPaga [i]<< std::endl;
                            } 
                            cin >> op2;
                            system("clear");
                            arrPaga[op2] = arrPaga[op2].a_dolar();
                            break;
                        case 4:
                            system("clear");
                            cout << "Ingrese la paga que desea cambiar a pesos" << endl;
                            cout << "* Pagas existentes *" << endl<<endl;
                            for (int i = 0; i < numPaga; i++) {
                                cout << "Paga " << i << ": " << endl;
                                std::cout << arrPaga [i]<< std::endl;
                            } 
                            cin >> op2;
                            system("clear");
                            arrPaga[op2] = arrPaga[op2].a_peso();
                            break;
                        case 5:
                            system("clear");
                            cout << "Ingrese la paga que desea editar" << endl<<endl;
                            cout << "* Pagas existentes *" << endl<<endl;
                            for (int i = 0; i < numPaga; i++) {
                                cout << "Paga " << i << ": " << endl;
                                std::cout << arrPaga [i]<< std::endl;
                            } 
                            cin >> op1;
                            if(numPaga >= op1){                                  
                                cout << "Paga encontrada" << endl;
                                cout << "Ingrese monto: " << endl;
                                cin >> monto;
                                cout << "Ingrese moneda (1 para peso, 2 para dolar): " << endl;
                                cin >> op2;
                                if (op2 == 1) {
                                    moneda = Moneda::us;
                                } else {
                                    moneda = Moneda::usd;
                                }
                                arrPaga[op1] = Paga(monto, moneda);
                                system("clear");
                                cout << "- Paga editada exitosamente -" << endl<<endl;
                                break;
                            }
                            else 
                                { 
                                system("clear");                   
                                cout << " La paga ingresada no existe " << endl<< endl;
                                }
                            break;                         
                        case 6:
                            system("clear");
                            cout << "Ingrese la paga que desea multiplicar" << endl;
                            cout << "* Pagas existentes *" << endl<<endl;
                            for (int i = 0; i < numPaga; i++) {
                                cout << "Paga " << i << ": " << endl;
                                std::cout << arrPaga [i]<< std::endl;
                            } 
                            cin >> op1;
                            cout << "Ingrese el factor por el que desea multiplicar" << endl;
                            cin >> monto;
                            arrPaga[op1] = arrPaga[op1] * monto;
                            system("clear");
                            cout << "- Paga multiplicada exitosamente - " << endl<<endl;
                            break;
                        case 7:
                            system("clear");
                            cout << "Ingrese la paga que desea sumar (y editar)" << endl;
                            cout << "* Pagas existentes *" << endl<<endl;
                            for (int i = 0; i < numPaga; i++) {
                                cout << "Paga " << i << ": " << endl;
                                std::cout << arrPaga [i]<< std::endl;
                            } 
                            cin >> op1;
                            cout << "Ingrese la paga que desea sumarle" << endl;
                            cin >> op2;
                            try{
                                arrPaga[op1] = arrPaga[op1] + arrPaga[op2];
                                cout << "- Pagas sumadas exitosamente - " << endl<<endl;
                                
                            }catch(invalid_argument& ia){
                                system("clear");                                
                                cout<<"Intenta sumar distintas Monedas, intentelo nuevamente"<<endl;
                            }                            
                            break;
                        case 8: 
                            system("clear");
                            cout << "* Pagas existentes *" << endl<<endl;
                            for (int i = 0; i < numPaga; i++) {
                                cout << "Paga " << i << ": " << endl;
                                std::cout << arrPaga [i]<< std::endl;
                            } 
                            break;
                        
                        }
                    }    
                while (op != 0);
                system("clear");
                break;
            case 2:
                do{
                    menuGeneral ();
                    cin.sync();
                    op3 = devolver_entero();
                    switch (op3){
                        case 1:{   
                            system("clear");
                            cout<< " Ingrese Nombre de la Empresa: "<<endl;
                            cin.ignore();
                            getline(cin, nombre, '\n');              
                            cout<< " Ingrese Nombre Legal de la Empresa: "<<endl;                
                            getline(cin, nombre_legal, '\n');  
                            rut2 = ingresar_rut();  
                            if(numEmpresas<max_empresas){
                                e = new Empresa(nombre, nombre_legal, rut2);
                                empresas[numEmpresas] = e;
                                numEmpresas++;
                                system("clear");
                                cout<<" Empresa agregada Exitosamente"<<endl;
                            } else{
                                system("clear");
                                cout<<" No se pueden agregar mas Empresas"<<endl;
                            }      
                            break;
                        }
                        case 2:{
                            system("clear");
                            if (numEmpresas<1){                                
                                cout<<"No hay empresas ingresadas, por tanto no se pueden contratar empleados."<<endl;
                                cout<<"Procure ingresar una empresa antes."<<endl;
                                break;
                            }
                            cout<< " Ingrese Nombre y Apellido del Empleado: "<<endl;
                            cin>>nom_emp; 
                            cin.ignore();
                            getline(cin, nom_emp, '\n');  
                            cout<< " Ingrese su Cedula de Identidad: "<<endl;                
                            getline(cin, ci, '\n');  
                            cout<< " Ingrese su Edad: "<<endl;
                            cin>>edad; 
                            cout<< " Ingrese Valor Hora: "<<endl;
                            cin>>monto;
                            cout<< " Seleccione Moneda  "<<endl;
                            cout<< "1 - USD - Dolares"<<endl;
                            cout<< "2 - US  - Pesos " <<endl;
                            cin>>op;
                            switch(op){
                                case 1:
                                    moneda = Moneda::usd;
                                    system("clear");
                                    break;
                                case 2:
                                    moneda = Moneda::us;
                                    system("clear");
                                    break;
                                default:
                                    system("clear");
                                    cout<<" Opcion Invalida, intentelo nuevamente. "<<endl;
                                    break;
                            } 
                            valor_hora.setMonto(monto);
                            valor_hora.setMoneda(moneda);
                            cout<< " Seleccione Tipo Empleado  "<<endl;
                            cout<< "1 - Fijo" <<endl;
                            cout<< "2 - Jornalero " <<endl;
                            cin>>op2;
                            switch(op2){
                                case 1:
                                    em = new Fijo(nom_emp, ci, edad, valor_hora); //Fijo* f = new Fijo(nom_emp, ci, edad, valor_hora); 
                                    system("clear");                  
                                    break;
                                case 2:
                                    system("clear");
                                    cout<< "Ingrese Cant Horas"<<endl;
                                    cin>>horas;
                                    em = new Jornalero(nom_emp,ci,edad,valor_hora,horas); //Jornalero* j= new Jornalero(nom_emp,ci,edad,valor_hora,horas)                        
                                    break;
                                default:
                                    system("clear");
                                    cout<<" Opcion Invalida, intentelo nuevamente. "<<endl;
                                    break;
                            }
                            system("clear");
                            cout<< " CONTRATAR EMPLEADO \n"<<endl;
                            cout<< " Listando Empresas Contratantes \n "<<endl; 
                            for(int i=0; i<numEmpresas; i++){
                                cout<<"EMPRESA numero: "<<i+1<<endl;
                                empresas[i]->mostrarDatosEmpresa();
                                cout<<endl;
                            }
                            cout<< " Seleccione Empresa Contratante Digitando número de Empresa"<<endl; // Aca intento linkear la empresa con el empleado                
                            cin>>opcion;               
                            //opcion=devolver_entero();  // Revisar
                            Empresa * emp = empresas[opcion-1];
                            emp->contratarEmpleado(em);
                            system("clear");
                            cout<<"Empleado Contratado Exitosamente"<<endl;          
                            break;
                        }	
                        case 3:{
                            system("clear");
                            cout<< "- MOSTRANDO EMPRESAS Y EMPLEADOS CONTRATADOS - \n"<<endl;
                            //cout<< "- Listando Empresas -\n "<<endl;
                            for(int i=0; i<numEmpresas; i++){
                                cout<<"EMPRESA numero: "<<i+1<<endl;
                                empresas[i]->mostrarDatosEmpresa();
                                cout<< "- Listando Empleados - \n "<<endl;
                                empresas[i]->mostrarDatosEmpleados();
                                cout<<"---------------------------------"<<endl; 
                                cout<<endl;
                            }
                            break;
                        }
                        case 4:{
                            system("clear");                   
                            cout<< "SELECCIONE EMPRESA A LIQUIDAR"<<endl<<endl;
                            for(int i=0; i<numEmpresas; i++){
                                cout<<"Empresa numero: "<<i+1<<endl<<endl;
                                empresas[i]->mostrarDatosEmpresa();
                                cout<<endl<<endl;
                            }
                            cout<< " Seleccione Empresas Digitando numero de Empresa"<<endl;
                            cin>>opcion;                
                            cout<< " Seleccione Moneda  "<<endl;
                            cout<< "1 - USD - Dolares"<<endl;
                            cout<< "2 - US  - Pesos " <<endl;
                            cin>>op;
                            Empresa * emp = empresas[opcion-1];
                            switch(op){
                                case 1:
                                //Esta funcion sería para devolver el liquido de sueldos de la empresa
                                        if (empresas[opcion-1]!= NULL){  
                                            Paga total_dolar = emp->total_paga_dolar();
                                            system("clear");
                                            cout << "- La paga total en sueldos en dólares es: " << total_dolar<<endl<<endl;
                                        } 
                                    break;
                                case 2:
                                        if (empresas[opcion-1]!= NULL){  
                                            Paga total_pesos = emp->total_paga_peso();
                                            system("clear");
                                            cout << "- La paga total en sueldos en pesoss es: " << total_pesos<<endl;
                                        }  
                                    break;
                                default:
                                    cout<<" Opcion Invalida, intentelo nuevamente. "<<endl;
                                    break;
                            }
                            break;
                        }
                        case 5: { //eliminar empresa
                            system("clear");
                            cout<< "Seleccione empresa a eliminar"<<endl;
                            for(int i=0; i<numEmpresas; i++){
                                cout<<"Empresa numero: "<<i+1<<endl;
                                empresas[i]->mostrarDatosEmpresa();
                                cout<<endl;
                            }
                            cin>>opcion;
                            empresas[opcion-1]->Eliminar();
                            
                            if (opcion < numEmpresas){
                                empresas[opcion-1] = empresas[numEmpresas-1];
                            }
                            numEmpresas--;
                            system("clear");
                            cout<< "- Empresa eliminada con exito - "<<endl;
                            break;
                       }
                            

                        default:
                            break;
                    }
                }
                while (op3 != 0);
                system("clear");
                break;
            default:
                break;
            }
    }      
    while (opcion != 0);    

    return 0;
}

