import java.util.Scanner;

import Scanner.MiniLexer;
import Scanner.Token;
import Scanner.TipoToken;

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

        String[] lexemas = frase.trim().split("\\s+");
        Token[] tokens = new Token[lexemas.length];
        for (int i = 0; i < lexemas.length; i++) {
            TipoToken tipo = MiniLexer.clasificarToken(lexemas[i]);
            tokens[i] = new Token(tipo, lexemas[i]);
        }

        System.out.println("Tokens:");
        for (Token t : tokens) System.out.println(t);

        input.close();
    }
}
