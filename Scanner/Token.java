package Scanner;

public class Token {
    public TipoToken tipo;
    public String lexema;

    public Token(TipoToken tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
    }

    public String toString() {
        return "Token: <" + tipo + ", \"" + lexema + "\">";
    }
}
