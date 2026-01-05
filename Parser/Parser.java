package Parser;

import Scanner.MiniScanner; 
import Scanner.Token; 
import Scanner.TipoToken;
import Semantic.SemanticAnalyzer;

public class Parser {

    public MiniScanner scanner; 
    public Token actual;
    public SemanticAnalyzer sem = new SemanticAnalyzer();

    public Parser(MiniScanner scanner) { 
        this.scanner = scanner; 
        this.actual = scanner.getNextToken(); 
    } 
    
    private void avanzar() { 
        actual = scanner.getNextToken(); 
    } 
    
    private void consumir(String esperado) { 
        if (actual == null || !actual.lexema.equals(esperado)) { 
            throw new RuntimeException("Error: se esperaba '" + esperado + "' pero llegó '" + actual.lexema + "'"); 
        } 
        avanzar(); 
    } 

    public void parse() { 
        while (actual != null && actual.tipo != TipoToken.EOF) { 
            stmt(); 
        } 
        System.out.println("✔ Análisis sintáctico completado"); 
    }

    public void stmt() {

        // CASO 1: Declaración de variable: int x;
        if (actual.tipo == TipoToken.PALABRA_CLAVE && actual.lexema.equals("int")) {
            avanzar();
            
            if (actual.tipo != TipoToken.IDENTIFICADOR) {
                throw new RuntimeException("Error: se esperaba un identificador después de 'int'");
            }
            
            String nombreVariable = actual.lexema;
            avanzar();
            consumir(";");

            sem.registrarDeclaracion(nombreVariable, "int");
            System.out.println("Declaración válida");
        }

        // CASO 2: Asignación: x = 5;
        else if (actual.tipo == TipoToken.IDENTIFICADOR) { 
            String nombreVariable = actual.lexema;
            avanzar();

            consumir("=");

            if (actual.tipo == TipoToken.LITERAL_NUMERICO) {
                Token valorAsignado = actual;
                avanzar();

                sem.validarAsignacion(nombreVariable, valorAsignado);} 
            else {
                throw new RuntimeException("Error: se esperaba un número");
            }

            consumir(";");
            System.out.println("Sentencia válida");
        }

        // CASO 3: Uso de variable: print(x);
        else if (actual.tipo == TipoToken.IDENTIFICADOR && actual.lexema.equals("print")) {
            avanzar();
            consumir("(");

            if (actual.tipo != TipoToken.IDENTIFICADOR) {
                throw new RuntimeException("Error: se esperaba un identificador en print()");
            }

            String nombreVariable = actual.lexema;
            avanzar();

            //LLamada al analizador semántico
            sem.validarUsoVariable(nombreVariable);

            consumir(")");
            consumir(";");
            System.out.println("Print válido");
        }

        else {
            throw new RuntimeException("Error: sentencia inválida");
        }
    }

}


    //public void stmt() { 
        // IDENTIFICADOR 
        //if (actual.tipo == TipoToken.IDENTIFICADOR) { 
            //avanzar(); 
        
            // "="
            //consumir("="); 

            // NUMERO 
            //if (actual.tipo == TipoToken.LITERAL_NUMERICO) { 
                //avanzar(); 
            //} else { 
                //throw new RuntimeException("Error: se esperaba un número"); 
            //} 
            
            // ";" 
            //consumir(";"); 
            
            //System.out.println("Sentencia válida"); 
        //} else { 
            //throw new RuntimeException("Error: sentencia inválida"); 
        //} 
    //} 

