package Scanner;
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
	
}