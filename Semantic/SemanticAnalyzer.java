package Semantic;

import Scanner.Token;
import Scanner.TipoToken;

public class SemanticAnalyzer {

    private String[] nombres = new String[50];
    private String[] tipos = new String[50];
    private boolean[] inicializada = new boolean[50];
    private int contador = 0;

    // Registrar una declaración: int x;
    public void registrarDeclaracion(String nombre, String tipo) {
        nombres[contador] = nombre;
        tipos[contador] = tipo;
        inicializada[contador] = false;
        contador++;
    }

    // Buscar variable en la tabla de símbolos
    public int buscarVariable(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (nombres[i].equals(nombre)) return i;
        }
        return -1;
    }

    // Validar asignación: x = 5;
    public void asignacion(String nombre, Token valor) {
        int pos = buscarVariable(nombre);
        if (pos == -1) {
            throw new RuntimeException("Error semántico: variable '" + nombre + "' no declarada");
        }

        if (valor.tipo != TipoToken.LITERAL_NUMERICO) {
            throw new RuntimeException("Error semántico: solo se pueden asignar números a variables int");
        }

        inicializada[pos] = true;
    }

    // Validar uso: print(x);
    public void usoVariable(String nombre) {
        int pos = buscarVariable(nombre);
        if (pos == -1) {
            throw new RuntimeException("Error semántico: variable '" + nombre + "' no declarada");
        }
        if (!inicializada[pos]) {
            throw new RuntimeException("Error semántico: variable '" + nombre + "' no inicializada");
        }
    }
}
