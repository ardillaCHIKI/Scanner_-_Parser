package Semantic;

import Scanner.Token;
import Scanner.TipoToken;

public class SemanticAnalyzer {

    public String[] nombres = new String[50];
    public String[] tipos = new String[50];
    public boolean[] inicializada = new boolean[50];
    public int contador = 0;

    // Registrar una declaración: int x;
    public void registrarDeclaracion(String nombre, String tipo) {
        if (buscarVariable(nombre) != -1) {
        throw new RuntimeException("Error semántico: variable '" + nombre + "' ya fue declarada");
        }
        nombres[contador] = nombre;
        tipos[contador] = tipo;
        inicializada[contador] = false;
        contador++;
    }

    // Buscar variable en la tabla de símbolos
    public int buscarVariable(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (nombres[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    // Validar asignación: x = 5;
    // REGLA 1: Comprobación de tipos
    public void validarAsignacion(String nombre, Token valor) {
        int pos = buscarVariable(nombre);

        // La variable debe estar declarada
        if (pos == -1) {
            throw new RuntimeException("Error semántico: variable '" + nombre + "' no declarada");
        }

        // Verificar compatibilidad de tipos
        String tipoVariable = tipos[pos];
        if (valor.tipo != TipoToken.LITERAL_NUMERICO) {
            throw new RuntimeException("Error semántico: tipo incompatible para la variable '" + nombre + "'"); 
        }

        inicializada[pos] = true;
    }

    // Validar uso: print(x);
    //REGLA 2: Variables inicializadas
    public void validarUsoVariable(String nombre) {
        int pos = buscarVariable(nombre);

        // La variable debe estar declarada
        if (pos == -1) {
            throw new RuntimeException("Error semántico: variable '" + nombre + "' no declarada");
        }

        // La variable debe estar inicializada
        if (!inicializada[pos]) {
            throw new RuntimeException("Error semántico: variable '" + nombre + "' no inicializada");
        }
    }

    // Valida una operación aritmética: x + y
    // REGLA 3: Operaciones válidas entre números
    public void validarOperacion(String var1, String operador, String var2) {
        // Validar primera variable
        int pos1 = buscarVariable(var1);
        if (pos1 == -1) {
            throw new RuntimeException("Error semántico: variable '" + var1 + "' no declarada");
        }
        if (!inicializada[pos1]) {
            throw new RuntimeException("Error semántico: variable '" + var1 + "' no inicializada");
        }
        
        // Validar segunda variable
        int pos2 = buscarVariable(var2);
        if (pos2 == -1) {
            throw new RuntimeException("Error semántico: variable '" + var2 + "' no declarada");
        }
        if (!inicializada[pos2]) {
            throw new RuntimeException("Error semántico: variable '" + var2 + "' no inicializada");
        }
        
        // Solo operaciones aritméticas entre números
        if (!tipos[pos1].equals("int") || !tipos[pos2].equals("int")) {
            throw new RuntimeException("Error semántico: operación aritmética solo válida entre números");
        }
    }

    public void imprimirTablaSimbolos() {
        System.out.println("\n=== TABLA DE SÍMBOLOS ===");
        System.out.println("┌──────────────┬──────────┬──────────────┐");
        System.out.println("│   Variable   │   Tipo   │ Inicializada │");
        System.out.println("├──────────────┼──────────┼──────────────┤");
        
        for (int i = 0; i < contador; i++) {
            String nombre = String.format("%-12s", nombres[i]);
            String tipo = String.format("%-8s", tipos[i]);
            String init = inicializada[i] ? "    Sí       " : "    No       ";
            System.out.println("│ " + nombre + " │ " + tipo + " │" + init + "│");
        }
        
        System.out.println("└──────────────┴──────────┴──────────────┘");
        System.out.println("Total de variables: " + contador);
    }
}
