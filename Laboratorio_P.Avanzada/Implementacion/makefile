INTERFACES = ICollection/interfaces/
COLECCIONES = ICollection/collections/
CLASES = clases/.cpp/
DATATYPES = datatypes/.cpp/
OBJETOS = DtOfertaLite.o DtSeccion.o DtSucursal.o DtNuevoEstudiante.o DtOferta.o DtOfertaActiva.o DtEstudiantePlus.o DtLlamados.o DtModOferta.o Date.o DtAsignatura.o DtDatosEstudiante.o DtEmpresa.o DtEstudiante.o Seccion.o Sistema.o Sucursal.o Postulacion.o ISistema.o OfertaLaboral.o Fabrica.o Estudiante.o Empresa.o Asignatura.o CargoEfectivo.o Carrera.o ICollectible.o ICollection.o ListNode.o List.o IIterator.o IKey.o OrderedKey.o OrderedDictionaryEntry.o Integer.o ListIterator.o IDictionary.o OrderedDictionary.o String.o main.o

all: $(OBJETOS)
	g++ $(OBJETOS) -o programa

Asignatura.o: ICollectible.o
	g++ -c $(CLASES)Asignatura.cpp

CargoEfectivo.o: Date.o ICollectible.o
	g++ -c $(CLASES)CargoEfectivo.cpp

Carrera.o: ICollectible.o
	g++ -c $(CLASES)Carrera.cpp

Empresa.o: ICollectible.o IDictionary.o DtEmpresa.o OfertaLaboral.o Sucursal.o
	g++ -c $(CLASES)Empresa.cpp

Estudiante.o: ICollectible.o IDictionary.o DtEstudiante.o Date.o Carrera.o ICollection.o Asignatura.o CargoEfectivo.o Postulacion.o
	g++ -c $(CLASES)Estudiante.cpp

Fabrica.o: ISistema.o Sistema.o
	g++ -c $(CLASES)Fabrica.cpp

ISistema.o:
	g++ -c $(CLASES)ISistema.cpp

OfertaLaboral.o:
	g++ -c $(CLASES)OfertaLaboral.cpp

Postulacion.o:
	g++ -c $(CLASES)Postulacion.cpp

Seccion.o:
	g++ -c $(CLASES)Seccion.cpp

Sistema.o:
	g++ -c $(CLASES)Sistema.cpp

Sucursal.o:
	g++ -c $(CLASES)Sucursal.cpp

Date.o:
	g++ -c $(DATATYPES)Date.cpp

DtAsignatura.o:
	g++ -c $(DATATYPES)DtAsignatura.cpp

DtDatosEstudiante.o:
	g++ -c $(DATATYPES)DtDatosEstudiante.cpp

DtEmpresa.o:
	g++ -c $(DATATYPES)DtEmpresa.cpp

DtEstudiantePlus.o:
	g++ -c $(DATATYPES)DtEstudiantePlus.cpp

DtLlamados.o:
	g++ -c $(DATATYPES)DtLlamados.cpp

DtModOferta.o:
	g++ -c $(DATATYPES)DtModOferta.cpp

DtNuevoEstudiante.o:
	g++ -c $(DATATYPES)DtNuevoEstudiante.cpp

DtOferta.o:
	g++ -c $(DATATYPES)DtOferta.cpp

DtOfertaActiva.o:
	g++ -c $(DATATYPES)DtOfertaActiva.cpp

DtOfertaLite.o:
	g++ -c $(DATATYPES)DtOfertaLite.cpp

DtSeccion.o:
	g++ -c $(DATATYPES)DtSeccion.cpp

DtSucursal.o:
	g++ -c $(DATATYPES)DtSucursal.cpp

DtEstudiante.o:
	g++ -c $(DATATYPES)DtEstudiante.cpp

ICollectible.o: $(INTERFACES)ICollectible.h
	g++ -c $(INTERFACES)ICollectible.cpp 

IKey.o: $(INTERFACES)IKey.h
	g++ -c $(INTERFACES)IKey.cpp

OrderedKey.o: $(INTERFACES)OrderedKey.h $(INTERFACES)IKey.o 
	g++ -c $(INTERFACES)OrderedKey.cpp

Integer.o: ICollection/Integer.h ICollectible.o OrderedKey.o
	g++ -c ICollection/Integer.cpp

String.o: ICollection/String.h ICollectible.o OrderedKey.o
	g++ -c ICollection/String.cpp

OrderedDictionaryEntry.o: $(COLECCIONES)OrderedDictionaryEntry.h ICollectible.o OrderedKey.o
	g++ -c $(COLECCIONES)OrderedDictionaryEntry.cpp

ListIterator.o: $(COLECCIONES)ListIterator.h ListNode.o 
	g++ -c $(COLECCIONES)ListIterator.cpp

IDictionary.o: $(INTERFACES)IDictionary.h IIterator.o ICollectible.o IKey.o
	g++ -c $(INTERFACES)IDictionary.cpp

OrderedDictionary.o: $(COLECCIONES)OrderedDictionary.h IDictionary.o OrderedDictionaryEntry.o ListIterator.o
	g++ -c $(COLECCIONES)OrderedDictionary.cpp

IIterator.o: $(INTERFACES)IIterator.h $(INTERFACES)ICollectible.o
	g++ -c $(INTERFACES)IIterator.cpp 

ICollection.o: IIterator.o ICollectible.o $(INTERFACES)ICollection.h
	g++ -c $(INTERFACES)ICollection.cpp

ListNode.o: $(COLECCIONES)ListNode.h ICollectible.o
	g++ -c $(COLECCIONES)ListNode.cpp

List.o: ICollection.o ListIterator.o $(COLECCIONES)List.h
	g++ -c $(COLECCIONES)List.cpp

main.o:
	g++ -c main.cpp


clean:
	rm -f programa *.o $(INTERFACES)*.o $(COLECCIONES)*.o
run:
	make clean
	make
	clear
	./programa