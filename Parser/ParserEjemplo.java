package Parser;

import java.util.ArrayList;
import java.util.List;

public class ParserEjemplo {

    enum TipoToken {
        ID, 
        NUM, OPERADOR, 
        DELIMITADOR
    }

    static class Token {
        String lexema;
        TipoToken tipo;

        Token(String lexema, TipoToken tipo) {
            this.lexema = lexema;
            this.tipo = tipo;
        }

 
        public String toString() {
            return "(" + lexema + ", " + tipo + ")";
        }
    }


    private List<Token> tokens;
    private int pos = 0;

    public ParserEjemplo(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token actual() {
        if (pos < tokens.size()) return tokens.get(pos);
        return null;
    }

    private void consumir(String esperado) {
        Token t = actual();
        if (t == null || !t.lexema.equals(esperado)) {
            throw new RuntimeException("Error: se esperaba '" + esperado + "'");
        }
        pos++;
    }


    public void stmt() {
        Token t = actual();
        if (t != null && t.tipo == TipoToken.ID) {
            pos++;
            consumir("="); 
            Token num = actual();
            if (num != null && num.tipo == TipoToken.NUM) {
                pos++; 
            } else {
                throw new RuntimeException("Error: se esperaba un número");
            }
            consumir(";"); 
        } else {
            throw new RuntimeException("Error: sentencia inválida");
        }
    }


    public static void main(String[] args) {
        List<Token> ejemplo = new ArrayList<>();
        ejemplo.add(new Token("x", TipoToken.ID));
        ejemplo.add(new Token("=", TipoToken.OPERADOR));
        ejemplo.add(new Token("7", TipoToken.NUM));
        ejemplo.add(new Token(";", TipoToken.DELIMITADOR));

        ParserEjemplo parser = new ParserEjemplo(ejemplo);
        parser.stmt();
        System.out.println(" Sentencia 'x=7;' válida");
    }
}
