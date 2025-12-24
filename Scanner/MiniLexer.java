package Scanner;

// import java.util.Scanner;


public class MiniLexer {
    public static TipoToken clasificarToken(String lexema) {

        if (lexema.equals("if") || lexema.equals("int")) {
            return TipoToken.PALABRA_CLAVE;
        }

        else if (lexema.equals("=") || lexema.equals("==") || lexema.equals("+")) {
            return TipoToken.OPERADOR;
        }

        else if (lexema.equals(";") || lexema.equals("(") || lexema.equals(")")) {
            return TipoToken.DELIMITADOR;
        }

        else if (lexema.matches("[0-9]+")) {
            return TipoToken.LITERAL_NUMERICO;
        }
 
        else {
            return TipoToken.IDENTIFICADOR;
        }
    }
	

	// public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Introduce instrucciones simples:");
        // String entrada = sc.nextLine();

        // String[] tokensEntrada = entrada.split("\\s+");

        // Token[] listaTokens = new Token[tokensEntrada.length];
        
        // for (int i = 0; i < tokensEntrada.length; i++) {
            // TipoToken tipo = clasificarToken(tokensEntrada[i]);
            // listaTokens[i] = new Token(tipo, tokensEntrada[i]);
        // }
        
        // System.out.println("Tokens encontrados:");
        // for (Token token : listaTokens) {
            // System.out.println(token);
        // }
        
        // sc.close();
	//}
}