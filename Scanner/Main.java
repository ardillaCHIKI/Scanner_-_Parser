package Scanner;
import java.util.List;
import java.util.Scanner;

import Parser.Parser;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Introduce una frase:");
        String frase = input.nextLine();

        if (frase == null || frase.trim().isEmpty()) {
            System.out.println("Entrada vacía.");
            input.close();
            return;
        }

        MiniScanner scanner = new MiniScanner(frase);

        Parser parser = new Parser(scanner);

        System.out.println("\n--- EJECUTANDO PARSER ---");
        try { 
            parser.parse(); // Esto ejecuta stmt() y valida la frase 
        } catch (RuntimeException e) { 
            System.out.println("Error de sintaxis: " + e.getMessage()); 
        }

        System.out.println("\n--- TOKENS GENERADOS POR EL SCANNER ---"); 
        List<Token> tokens = scanner.getAllTokens(); 
        for (Token t : tokens) { 
            if (t.tipo != TipoToken.EOF) { 
                System.out.println(t); 
            } 
        } 
        
        input.close(); 
    }
    
}
