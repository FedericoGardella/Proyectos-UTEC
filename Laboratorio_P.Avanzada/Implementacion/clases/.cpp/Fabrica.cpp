#include "../.h/Fabrica.h"

ISistema * Fabrica::getSistema() {
    return Sistema::getInstancia();
}
