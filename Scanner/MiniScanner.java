package Scanner;

public class MiniScanner {

    private String[] lexemas;
    private int indice = 0;

    public MiniScanner(String frase) {
        this.lexemas = frase.trim().split("\\s+");
    }

    public Token getNextToken() {
        if (indice >= lexemas.length) {
            return new Token(TipoToken.EOF, "");
        }

        String lexema = lexemas[indice++];
        TipoToken tipo = MiniLexer.clasificarToken(lexema);
        return new Token(tipo, lexema);
    }
}
