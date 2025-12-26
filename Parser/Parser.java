package Parser;

import Scanner.MiniScanner; 
import Scanner.Token; 
import Scanner.TipoToken;
import Semantic.SemanticAnalyzer;

public class Parser {

    private MiniScanner scanner; 
    private Token actual;
    private SemanticAnalyzer sem = new SemanticAnalyzer();

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
        System.out.println("✔ Análisis sintáctico y semántico completado"); 
    }

    public void stmt() { 
        // IDENTIFICADOR 
        if (actual.tipo == TipoToken.IDENTIFICADOR) { 
            avanzar(); 
        
            // "="
            consumir("="); 

            // NUMERO 
            if (actual.tipo == TipoToken.LITERAL_NUMERICO) { 
                avanzar(); 
            } else { 
                throw new RuntimeException("Error: se esperaba un número"); 
            } 
            
            // ";" 
            consumir(";"); 
            
            System.out.println("Sentencia válida"); 
        } else { 
            throw new RuntimeException("Error: sentencia inválida"); 
        } 
    } 
}
